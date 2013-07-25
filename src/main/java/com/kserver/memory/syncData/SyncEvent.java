package com.kserver.memory.syncData;

import com.kserver.memory.Active;

public abstract class SyncEvent {

	/** 操作类型 **/
	private int opt;
	/** 当前的内存对象 **/
	private Active active;
	/** 要操作的值 **/
	private Object value;

	public SyncEvent(int opt, Active active, Object value) {
		this.opt = opt;
		this.active = active;
		this.value = value;
	}

	/** 同步对象 **/
	public abstract void syncObj();

	public int getOpt() {
		return opt;
	}

	public void setOpt(int opt) {
		this.opt = opt;
	}

	public Active getActive() {
		return active;
	}

	public void setActive(Active active) {
		this.active = active;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return getActiveName() + "_" + opt + ":" + value.toString()+"";
	}

	private String getActiveName() {
		String names[] = getActive().getClass().getName().split("\\.");
		return names[names.length - 1];
	}
}
