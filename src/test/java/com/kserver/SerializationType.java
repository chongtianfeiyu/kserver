package com.kserver;

import java.nio.ByteBuffer;

import com.kserver.util.SysConstant;

public abstract class SerializationType {

	public ByteBuffer getPackage() throws Exception {
		ByteBuffer buff = ByteBuffer.allocate(1024);
		buff.put(SysConstant.Protocol.PACKAGE_TAG);
		byte[] protocolDate = getProtocolDate();
		buff.putInt(protocolDate.length + 1);
		buff.put((byte) 1);// 一条协议
		buff.put(protocolDate);
		return ByteBuffer.wrap(getBufArray(buff));
	}

	private byte[] getProtocolDate() throws Exception {
		ByteBuffer buff = ByteBuffer.allocate(1024);
		byte[] result = getObjByte();
		buff.putInt(result.length + 4);
		buff.putInt(1);// 协议号
		buff.put(result);

		return getBufArray(buff);
	}

	protected abstract byte[] getObjByte() throws Exception;

	/**
	 * 获取字节数组
	 * 
	 * @param buf
	 * @return
	 */
	private byte[] getBufArray(ByteBuffer buf) {
		buf.flip();
		int requestLen = buf.limit();
		byte[] bodyBuf = new byte[requestLen];
		buf.get(bodyBuf);
		return bodyBuf;
	}

}
