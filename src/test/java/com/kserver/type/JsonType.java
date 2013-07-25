package com.kserver.type;

import org.codehaus.jackson.map.ObjectMapper;

import com.kserver.SerializationType;
import com.kserver.protocol.coder.decoder.JsonPackageDecoder;
import com.kserver.protocolObj.Person;

public class JsonType extends SerializationType {

	@Override
	protected byte[] getObjByte() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(new Person(25, "赵辉")).getBytes(
				JsonPackageDecoder.DEFAULT_CHARSET);
	}
}
