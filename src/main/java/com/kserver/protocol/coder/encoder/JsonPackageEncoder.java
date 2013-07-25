package com.kserver.protocol.coder.encoder;

import org.codehaus.jackson.map.ObjectMapper;

import com.kserver.protocol.coder.PackageEncoder;
import com.kserver.protocol.coder.decoder.JsonPackageDecoder;
import com.kserver.protocol.dataType.ProtocolObj;

/**
 * 基于json的编码
 * 
 * @author ksfzhaohui
 * 
 */
public class JsonPackageEncoder extends PackageEncoder {

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	protected byte[] getProtocolByte(ProtocolObj protocolObj) throws Exception {
		return mapper.writeValueAsString(protocolObj.getMessage()).getBytes(
				JsonPackageDecoder.DEFAULT_CHARSET);
	}

}
