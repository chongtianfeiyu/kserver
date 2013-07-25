package com.kserver.memory.syncData.syncDB;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.kserver.memory.syncData.SyncDB;
import com.kserver.memory.syncData.SyncEvent;

public class SyncQueue implements SyncDB {

	private static Logger logger = Logger.getLogger(SyncQueue.class);

	private static SyncQueue syncQueue = new SyncQueue();

	/** 每一次定时同步最大同步的对象数 **/
	private static final int MAX_SYNC_DB_NUM = 1000;

	/** 数据库同步队列 **/
	private static ConcurrentLinkedQueue<SyncEvent> concurrentLinkedQueue = new ConcurrentLinkedQueue<SyncEvent>();

	private SyncQueue() {

	}

	public static SyncQueue getInstance() {
		return syncQueue;
	}

	/**
	 * 将数据库同步操作放入队列中
	 * 
	 * @param syncEvent
	 *            数据库同步对象
	 */
	@Override
	public void put(SyncEvent syncEvent) {
		concurrentLinkedQueue.offer(syncEvent);
	}

	/**
	 * 定时同步
	 * 
	 * @throws Exception
	 */
	@Override
	public void run() throws Exception {
		SyncEvent syncEvent = null;
		int syncNum = 0;
		while (true) {
			try {
				syncEvent = concurrentLinkedQueue.poll();
				if (syncEvent == null) {
					return;
				}
				syncEvent.syncObj();
			} catch (Exception e) {
				logger.error("<*********本次同步出现异常:" + syncEvent + "********>", e);
			} finally {
				syncNum++;
				logger.info("<*********本次执行同步操作的次数:" + syncNum + ";内存剩余的对象数:"
						+ concurrentLinkedQueue.size() + ";同步对象信息:" + syncEvent
						+ "********>");
			}

			if (syncNum >= MAX_SYNC_DB_NUM) {
				return;
			}
		}
	}
}
