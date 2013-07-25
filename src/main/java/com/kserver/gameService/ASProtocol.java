package com.kserver.gameService;

import org.springframework.context.ApplicationContext;

import com.kserver.protocol.dataType.AProtocol;
import com.kserver.util.ResourceBuffer;

public abstract class ASProtocol {

	/** 协议号 **/
	private int protocolNum;
	/** 是否需要协议事件处理 **/
	private boolean isHandleEvent;

	protected ApplicationContext ctx = ResourceBuffer.getInstance().getCtx();

	public ASProtocol(int protocolNum) {
		this.protocolNum = protocolNum;
		this.isHandleEvent = true;
	}

	public ASProtocol(int protocolNum, boolean isHandleEvent) {
		this.protocolNum = protocolNum;
		this.isHandleEvent = isHandleEvent;
	}

	/** 协议事件处理 **/
	protected abstract void handle();

	/** 返回事件 **/
	protected abstract AProtocol getData();

	public AProtocol getProtocol() {
		if (isHandleEvent) {
			handle();
		}
		return getData();
	}

	public int getProtocolNum() {
		return protocolNum;
	}

	public void setProtocolNum(int protocolNum) {
		this.protocolNum = protocolNum;
	}

	public boolean isHandleEvent() {
		return isHandleEvent;
	}

	public void setHandleEvent(boolean isHandleEvent) {
		this.isHandleEvent = isHandleEvent;
	}
}
