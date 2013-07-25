package com.kserver.protocol.dataType;

public class ProtocolByte extends AProtocol {

	/** 每条协议包含的二进制数据内容 **/
	private byte[] protocolData;

	public ProtocolByte(int protocolID, byte[] protocolData) {
		super(protocolID);
		this.protocolData = protocolData;
	}

	public byte[] getProtocolData() {
		return protocolData;
	}

	public void setProtocolData(byte[] protocolData) {
		this.protocolData = protocolData;
	}
}
