package com.kserver.memory;

import org.springframework.dao.DataAccessException;

import com.kserver.main.GameServerData;
import com.kserver.memory.syncData.SyncEvent;
import com.kserver.util.KException;

/**
 * 内存维护对象
 * 
 * @author ksfzhaohui
 * 
 */
public abstract class Active {

	/************************* 【以下关联内存 】 *************************/
	/** 添加内存对象 **/
	protected abstract void put(Object obj) throws KException;

	/** 获取内存对象 **/
	public abstract Object get(int id) throws KException;

	/** 获取所有对象 **/
	public abstract Object getList() throws KException;

	/** 移除内存对象 **/
	protected abstract Object remove(int id);

	/************************* 【以下关联仅关联内存(记录操作进队列定期执行) 】 *************************/
	/** 添加对象 **/
	public abstract void add(Object obj) throws KException;

	/** 删除对象 **/
	public abstract void delete(Object obj) throws KException;

	/** 更新对象 **/
	public abstract void update(Object obj) throws KException;

	/************************* 【定时同步操作仅关联数据库 】 *************************/
	public abstract void syncAdd(Object value) throws DataAccessException;

	public abstract void syncUpdate(Object value) throws DataAccessException;

	public abstract void syncDelete(Object value) throws DataAccessException;

	/**
	 * 添加数据库同步操作
	 * 
	 * @param syncEvent
	 *            数据库同步对象
	 */
	protected void addSyncEvent(SyncEvent syncEvent) {
		GameServerData.getGameServerData().getSyncDB().put(syncEvent);
	}

}
