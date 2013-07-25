package com.kserver.cmd;

import java.util.List;

import com.kserver.gameService.ACommand;
import com.kserver.gameService.sProtocol.SProtocol1;
import com.kserver.protocol.dataType.AProtocol;

public class Command1 extends ACommand {

	@Override
	public List<AProtocol> doCommand(Object message) {
		System.out.println("【接受到的数据:】\n" + message);
		return addProtocol(new SProtocol1().getProtocol());
	}

}