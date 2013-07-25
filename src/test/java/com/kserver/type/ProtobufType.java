package com.kserver.type;

import com.kserver.SerializationType;
import com.kserver.protocolObj.GoodsPicInfo;

public class ProtobufType extends SerializationType {

	@Override
	protected byte[] getObjByte() {
		GoodsPicInfo.PicInfo.Builder builder = GoodsPicInfo.PicInfo
				.newBuilder();
		builder.setGoodID(100);
		builder.setGuid("11111-22222-3333-444");
		builder.setOrder(0);
		builder.setType("ITEM");
		builder.setID(10);
		builder.setUrl("http://xxx.jpg");
		GoodsPicInfo.PicInfo info = builder.build();

		return info.toByteArray();
	}

}
