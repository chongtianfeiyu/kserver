package com.kserver.protocol.coder.decoder;

import org.codehaus.jackson.map.ObjectMapper;

import com.kserver.protocol.coder.PackageDecoder;
import com.kserver.protocol.dataType.ProtocolByte;
import com.kserver.protocol.dataType.ProtocolObj;

/**
 * 基于json的解码器
 * 
 * @author ksfzhaohui
 * 
 */
public class JsonPackageDecoder extends PackageDecoder {

	/** 默认的编码方式 **/
	public static final String DEFAULT_CHARSET = "UTF-8";

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	protected ProtocolObj getProtocolObj(Class<?> clazz,
			ProtocolByte protocolByte) throws Exception {
		String protocolStr = new String(protocolByte.getProtocolData(),DEFAULT_CHARSET);
		return new ProtocolObj(protocolByte.getProtocolID(), mapper.readValue(
				protocolStr, clazz));
	}
}
