package com.kserver;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.junit.Test;

import com.kserver.type.JsonType;

public class ProtocolTest {

	@Test
	public void sendMessage() throws Exception {
		SocketChannel client = SocketChannel.open(new InetSocketAddress(
				"localhost", 9011));
		ByteBuffer packageBuff = new JsonType().getPackage();
		client.write(packageBuff);
		client.close();
	}

}
