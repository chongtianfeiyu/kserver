package com.kserver.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 加载配置信息
 * 
 * @author ksfzhaohui
 * 
 */
public class ServerInfoData {

	private static Logger logger = Logger.getLogger(ServerInfoData.class
			.getName());

	private static final String INFO_NAME[] = new String[] { "Main_PORT" };

	private final String INFO_VALUE[] = new String[INFO_NAME.length];

	private static final int SI_Main_PORT = 0;

	private static ServerInfoData serverInfoData = new ServerInfoData();

	public static final ServerInfoData getInstance() {
		return serverInfoData;
	}

	private ServerInfoData() {
	}

	/**
	 * 读取配置文件信息
	 * 
	 * @return
	 */
	public final boolean ReadServerInfo() {
		Properties props = new Properties();
		InputStream in = null;

		try {
			in = new FileInputStream(new File("ServerInfo.dat"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			if (in == null)
				return false;
			props.load(in);

			// 加载相应的参数信息
			for (int i = 0; i < INFO_VALUE.length; i++) {
				INFO_VALUE[i] = new String(props.getProperty(
						"[" + INFO_NAME[i] + "]").getBytes("ISO-8859-1"),
						"UTF-8");
				logger.info("i = [" + INFO_NAME[i] + "] : " + INFO_VALUE[i]);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取主程序端口
	 * 
	 * @return
	 */
	public int getMainPort() {
		return Integer.valueOf(INFO_VALUE[SI_Main_PORT]);
	}
}
