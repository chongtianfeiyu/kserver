package com.kserver.gameService.sProtocol;

import com.kserver.gameService.ASProtocol;
import com.kserver.protocol.dataType.AProtocol;
import com.kserver.protocol.dataType.ProtocolByte;

public class SProtocol1 extends ASProtocol {

	public SProtocol1() {
		super(1);
	}

	@Override
	protected void handle() {

	}

	@Override
	protected AProtocol getData() {
		return new ProtocolByte(getProtocolNum(), new byte[1]);
	}

}
