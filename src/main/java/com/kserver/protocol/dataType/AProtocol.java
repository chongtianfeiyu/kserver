package com.kserver.protocol.dataType;

public abstract class AProtocol {

	/** 协议号 **/
	private int protocolID;

	public AProtocol(int protocolID) {
		this.protocolID = protocolID;
	}

	public int getProtocolID() {
		return protocolID;
	}

	public void setProtocolID(int protocolID) {
		this.protocolID = protocolID;
	}

}
