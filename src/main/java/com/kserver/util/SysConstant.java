package com.kserver.util;

public class SysConstant {

	/** 协议对象前缀 **/
	public static final String PROTOCOL_OBJ_PREFIX = "protocolObj";
	/** 命令前缀 **/
	public static final String COMMAND_PREFIX = "command";

	/**
	 * 【协议常量】
	 * 
	 * @author ksfzhaohui
	 * 
	 */
	public interface Protocol {
		/** 包头标志 **/
		public static final byte PACKAGE_TAG = 0x01;
		/** 结尾服标志 (EOF) **/
		public static final byte E_END = 'E';
		public static final byte O_END = 'O';
		public static final byte F_END = 'F';
	}

	/**
	 * 【定时更新内存数据到数据库的操作类型】
	 * 
	 * @author ksfzhaohui
	 * 
	 */
	public interface Opt {
		/** 插入操作* */
		int INSERT = 1;
		/** 更新操作* */
		int UPDATE = 2;
		/** 删除操作* */
		int DELETE = 3;
	}

	/**
	 * 【序列化类型】
	 * 
	 * @author ksfzhaohui
	 * 
	 */
	public interface SerializerType {
		String PROTOBUF = "protobuf";
		String JSON = "json";
		String BINARY = "binary";
	}

}
