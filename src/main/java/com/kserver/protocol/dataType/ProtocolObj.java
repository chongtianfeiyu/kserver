package com.kserver.protocol.dataType;

public class ProtocolObj extends AProtocol {
	
	/** 协议参数对象 **/
	private Object message;

	public ProtocolObj(int protocolID, Object message) {
		super(protocolID);
		this.message = message;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
}
