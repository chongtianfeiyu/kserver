package com.kserver.protocol.coder.decoder;

import java.util.List;

import com.kserver.protocol.BodyMessage;
import com.kserver.protocol.coder.PackageDecoder;
import com.kserver.protocol.dataType.ProtocolByte;
import com.kserver.protocol.dataType.ProtocolObj;

/**
 * 基于二进制解码器[无需对二进制数据进行映射]
 * 
 * @author ksfzhaohui
 * 
 */
public class BinaryPackageDecoder extends PackageDecoder {

	@Override
	protected BodyMessage getBodyMessage(List<ProtocolByte> protoByteList) {
		return new BodyMessage(protoByteList);
	}

	@Override
	protected ProtocolObj getProtocolObj(Class<?> clazz,
			ProtocolByte protocolByte) throws Exception {
		return null;
	}

}
