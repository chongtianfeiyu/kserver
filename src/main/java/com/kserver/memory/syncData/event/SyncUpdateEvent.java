package com.kserver.memory.syncData.event;

import com.kserver.memory.Active;
import com.kserver.memory.syncData.SyncEvent;
import com.kserver.util.SysConstant;

/**
 * 更新同步
 * 
 * @author ksfzhaohui
 * 
 */
public class SyncUpdateEvent extends SyncEvent {

	public SyncUpdateEvent(Active active, Object value) {
		super(SysConstant.Opt.UPDATE, active, value);
	}

	@Override
	public void syncObj() {
		getActive().syncUpdate(getValue());

	}

}
