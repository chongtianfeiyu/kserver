package com.kserver.protocol;

import java.util.List;

import com.kserver.protocol.dataType.AProtocol;

public class BodyMessage {

	private List<? extends AProtocol> protocolList;

	public BodyMessage(List<? extends AProtocol> protocolList) {
		this.protocolList = protocolList;
	}

	public List<? extends AProtocol> getProtocolList() {
		return protocolList;
	}

}
