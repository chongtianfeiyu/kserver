package com.kserver.memory.syncData.event;

import com.kserver.memory.Active;
import com.kserver.memory.syncData.SyncEvent;
import com.kserver.util.SysConstant;

/**
 * 删除同步
 * 
 * @author ksfzhaohui
 * 
 */
public class SyncDeleteEvent extends SyncEvent {

	public SyncDeleteEvent(Active active, Object value) {
		super(SysConstant.Opt.DELETE, active, value);
	}

	@Override
	public void syncObj() {
		getActive().syncDelete(getValue());
	}

}
