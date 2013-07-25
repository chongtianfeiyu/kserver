package com.kserver.memory.syncData;

public interface SyncDB {

	/**
	 * 将数据库同步操作放入同步器中
	 * 
	 * @param syncEvent
	 *            数据库同步对象
	 */
	public void put(SyncEvent syncEvent);

	/**
	 * 定时同步
	 * 
	 * @throws Exception
	 */
	public void run() throws Exception;

}
