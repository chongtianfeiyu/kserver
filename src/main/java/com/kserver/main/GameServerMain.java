package com.kserver.main;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.kserver.gameService.GameHandler;
import com.kserver.protocol.coder.PackageCodeFactory;
import com.kserver.util.ServerInfoData;

public class GameServerMain {

	private static Logger logger = Logger.getLogger(GameServerMain.class
			.getName());

	private static ServerInfoData serverinfo;

	public static void main(String[] args) throws IOException {
		serverinfo = ServerInfoData.getInstance(); // 加载配置文件
		if (!serverinfo.ReadServerInfo()) {
			logger.info("启动失败");
			return;
		}
		init(); // 初始化
	}

	private static void init() throws IOException {
		SocketAcceptor httpAcceptor = new NioSocketAcceptor();
		httpAcceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new PackageCodeFactory()));
		httpAcceptor.setHandler(GameHandler.getInstance());
		httpAcceptor.bind(new InetSocketAddress(serverinfo.getMainPort()));
		logger.info("监听主程序端口:" + serverinfo.getMainPort());
	}

}
