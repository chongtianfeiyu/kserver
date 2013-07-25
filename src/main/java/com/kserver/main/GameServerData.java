package com.kserver.main;

import org.springframework.context.ApplicationContext;

import com.kserver.memory.syncData.SyncDB;
import com.kserver.util.ResourceBuffer;

/**
 * 公共信息处理类(zhaohui)
 * 
 * @author ksfzhaohui
 * 
 */
public class GameServerData {
	private static GameServerData instance = new GameServerData();
	/** spring资源对象 **/
	private ApplicationContext context;
	/** 数据库同步器 **/
	private SyncDB syncDB;

	public static GameServerData getGameServerData() {
		return instance;
	}

	private GameServerData() {
		context = ResourceBuffer.getInstance().getCtx();
		syncDB = (SyncDB) context.getBean("syncDB");
	}

	public SyncDB getSyncDB() {
		return syncDB;
	}
}
