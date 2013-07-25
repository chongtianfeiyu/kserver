package com.kserver.protocol.coder.decoder;

import java.lang.reflect.Method;

import com.kserver.protocol.coder.PackageDecoder;
import com.kserver.protocol.dataType.ProtocolByte;
import com.kserver.protocol.dataType.ProtocolObj;

/**
 * 基于protobuf的解码
 * 
 * @author ksfzhaohui
 * 
 */
public class ProtobufPackageDecoder extends PackageDecoder {

	/** protobuf用于解析二进制的方法 **/
	private static final String PROTOBUF_PARSE_FROM = "parseFrom";

	@Override
	protected ProtocolObj getProtocolObj(Class<?> clazz,
			ProtocolByte protocolByte) throws Exception {
		Method parseFrom = clazz.getMethod(PROTOBUF_PARSE_FROM, byte[].class);
		return new ProtocolObj(protocolByte.getProtocolID(), parseFrom.invoke(
				null, protocolByte.getProtocolData()));
	}

}
