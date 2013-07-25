package com.kserver.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 加载spring配置文件
 * 
 * @author ksfzhaohui
 * 
 */
public class ResourceBuffer {
	private static ResourceBuffer _instance = new ResourceBuffer();
	private ApplicationContext ctx = null;

	private ResourceBuffer() {
		String[] cfigPath = { "applicationContext.xml" };
		ctx = new ClassPathXmlApplicationContext(cfigPath);
	}

	public static ResourceBuffer getInstance() {
		return _instance;
	}

	public ApplicationContext getCtx() {
		return ctx;
	}
}
