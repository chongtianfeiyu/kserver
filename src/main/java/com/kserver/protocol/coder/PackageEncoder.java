package com.kserver.protocol.coder;

import java.io.IOException;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.kserver.protocol.BodyMessage;
import com.kserver.protocol.dataType.AProtocol;
import com.kserver.protocol.dataType.ProtocolByte;
import com.kserver.protocol.dataType.ProtocolObj;
import com.kserver.util.SysConstant;

/**
 * 包编码器
 * 
 * 【包头<byte>+包体长度<int>+包体】
 * 
 * @author ksfzhaohui
 * 
 */
public abstract class PackageEncoder implements MessageEncoder<BodyMessage> {

	@Override
	public void encode(IoSession session, BodyMessage message,
			ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = IoBuffer.allocate(1024).setAutoExpand(true);
		buf.put(SysConstant.Protocol.PACKAGE_TAG);
		byte bodyMessage[] = getBodyMessage(message);
		buf.putInt(bodyMessage.length);
		buf.put(bodyMessage);

		buf.flip();
		out.write(buf);
	}

	/**
	 * 获取包体对象
	 * 
	 * 【包体:协议的数量+{长度+协议号+协议体}*】
	 * 
	 * @param message
	 *            协议对象包
	 * @return
	 * @throws IOException
	 */
	private byte[] getBodyMessage(BodyMessage message) throws Exception {
		IoBuffer buf = IoBuffer.allocate(1024).setAutoExpand(true);
		byte[] protocolByte = null;
		List<? extends AProtocol> protocolList = message.getProtocolList();
		buf.put((byte) protocolList.size());
		for (AProtocol protocol : protocolList) {
			protocolByte = getProtocol(protocol);
			buf.putInt(protocolByte.length);
			buf.put(protocolByte);
		}
		return getBufArray(buf);
	}

	/**
	 * 获取每条协议的二进制数据
	 * 
	 * @param protocol
	 *            协议对象
	 * @return
	 * @throws IOException
	 */
	private byte[] getProtocol(AProtocol protocol) throws Exception {
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		buf.putInt(protocol.getProtocolID());
		if (protocol instanceof ProtocolObj) {
			buf.put(getProtocolByte((ProtocolObj) protocol));
		} else {
			ProtocolByte protocolByte = (ProtocolByte) protocol;
			buf.put(protocolByte.getProtocolData());
		}
		return getBufArray(buf);
	}

	/**
	 * 获取字节数组
	 * 
	 * @param buf
	 * @return
	 */
	private byte[] getBufArray(IoBuffer buf) {
		buf.flip();
		int requestLen = buf.limit();
		byte[] bodyBuf = new byte[requestLen];
		buf.get(bodyBuf);
		return bodyBuf;
	}

	/**
	 * 获取协议对象的二进制数据内容
	 * 
	 * @param protocolObj
	 *            协议对象
	 * @return
	 */
	protected abstract byte[] getProtocolByte(ProtocolObj protocolObj)
			throws Exception;

}
