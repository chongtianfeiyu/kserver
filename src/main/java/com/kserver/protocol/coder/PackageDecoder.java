package com.kserver.protocol.coder;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import com.kserver.protocol.BodyMessage;
import com.kserver.protocol.dataType.ProtocolByte;
import com.kserver.protocol.dataType.ProtocolObj;
import com.kserver.util.ResourceBuffer;
import com.kserver.util.SysConstant;

/**
 * 包解码器
 * 
 * 【包头<byte>+包体长度<int>+包体】
 * 
 * @author ksfzhaohui
 * 
 */
public abstract class PackageDecoder implements MessageDecoder {

	private static Logger logger = Logger.getLogger(PackageDecoder.class
			.getName());

	/**
	 * 在解析包体之前确保接受的包是完整的
	 * 
	 * 【通过判断接受到的包体的长度来判断】 【in.remaining() < packageLen】
	 */
	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		if (in.remaining() < 5) {
			return MessageDecoderResult.NEED_DATA;
		}
		byte tag = in.get();
		int packageLen = in.getInt();

		if (tag != SysConstant.Protocol.PACKAGE_TAG) {// 包头标志位错误
			return MessageDecoderResult.NOT_OK;
		}
		if (in.remaining() < packageLen) {// 判断包体的长度
			return MessageDecoderResult.NEED_DATA;
		}
		return MessageDecoderResult.OK;
	}

	/**
	 * 包体:协议的数量+{长度+协议号+协议体}*
	 */
	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		/***** 【这里因为decodable解析完对IoBuffer进行了flip方法,需要重新读取】 ******/
		in.get();
		in.getInt();

		/**** 【对包体进行解析】 *****/
		int protoNum = in.get();
		List<ProtocolByte> protoByteList = new ArrayList<ProtocolByte>();
		ProtocolByte protocolByte = null;
		byte[] protoByte = null;
		int protocolID = 0;

		/**** 【封装每条协议为一个ProtocolByte对象】 *****/
		for (int i = 0; i < protoNum; i++) {
			int protoByteLen = in.getInt();
			protocolID = in.getInt();

			protoByte = new byte[protoByteLen - 4];
			in.get(protoByte);

			protocolByte = new ProtocolByte(protocolID, protoByte);
			protoByteList.add(protocolByte);
		}
		out.write(getBodyMessage(protoByteList));
		return MessageDecoderResult.OK;
	}

	/**
	 * 获取协议数据包对象
	 * 
	 * @param protoByteList
	 *            字节数组协议列表
	 * @return
	 */
	protected BodyMessage getBodyMessage(List<ProtocolByte> protoByteList) {
		return new BodyMessage(getProtocolMessageList(protoByteList));
	}

	/**
	 * 将字节数组转换成业务对象
	 * 
	 * @param protoByteList
	 *            字节数组协议列表
	 * @return
	 */
	private List<ProtocolObj> getProtocolMessageList(
			List<ProtocolByte> protoByteList) {
		List<ProtocolObj> protocolObjList = new ArrayList<ProtocolObj>();
		try {
			for (ProtocolByte protocolByte : protoByteList) {
				Class<?> clazz = getClazz(SysConstant.PROTOCOL_OBJ_PREFIX
						+ protocolByte.getProtocolID());
				protocolObjList.add(getProtocolObj(clazz, protocolByte));
			}
		} catch (Exception e) {
			logger.error("protoBuf解码异常", e);
		}
		return protocolObjList;
	}

	/**
	 * 获取协议对象
	 * 
	 * @param clazz
	 *            指定的协议对象类型
	 * @param rotocolByte
	 *            协议二进制数据
	 * @return
	 */
	protected abstract ProtocolObj getProtocolObj(Class<?> clazz,
			ProtocolByte protocolByte) throws Exception;

	private Class<?> getClazz(String protocolObj) throws ClassNotFoundException {
		return Class.forName(ResourceBuffer.getInstance().getCtx().getAliases(protocolObj)[0]);
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {

	}

}
