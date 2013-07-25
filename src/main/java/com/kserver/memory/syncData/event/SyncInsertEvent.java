package com.kserver.memory.syncData.event;

import com.kserver.memory.Active;
import com.kserver.memory.syncData.SyncEvent;
import com.kserver.util.SysConstant;

/**
 * 插入同步
 * 
 * @author ksfzhaohui
 * 
 */
public class SyncInsertEvent extends SyncEvent {

	public SyncInsertEvent(Active active, Object value) {
		super(SysConstant.Opt.INSERT, active, value);
	}

	@Override
	public void syncObj() {
		getActive().syncAdd(getValue());
	}

}
