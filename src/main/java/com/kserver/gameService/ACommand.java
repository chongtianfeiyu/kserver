package com.kserver.gameService;

import java.util.ArrayList;
import java.util.List;

import com.kserver.protocol.dataType.AProtocol;

public abstract class ACommand {

	/**
	 * 协议处理
	 * 
	 * @param message
	 *            传递的消息对象
	 * @return
	 */
	public abstract List<AProtocol> doCommand(Object message);

	/**
	 * 添加协议对象
	 * 
	 * @param protocol
	 *            协议对象
	 * @return
	 */
	protected List<AProtocol> addProtocol(AProtocol protocol) {
		List<AProtocol> protocolList = new ArrayList<AProtocol>();
		protocolList.add(protocol);
		return protocolList;
	}

	/**
	 * 添加协议对象
	 * 
	 * @param protocolList
	 *            已经存在的协议列表
	 * @param protocol
	 *            协议对象
	 * @return
	 */
	protected List<AProtocol> addProtocolObj(List<AProtocol> protocolList,
			AProtocol protocol) {
		protocolList.add(protocol);
		return protocolList;
	}
}
