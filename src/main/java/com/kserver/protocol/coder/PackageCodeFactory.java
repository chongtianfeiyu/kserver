package com.kserver.protocol.coder;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.springframework.context.ApplicationContext;

import com.kserver.protocol.BodyMessage;
import com.kserver.util.ResourceBuffer;

public class PackageCodeFactory extends DemuxingProtocolCodecFactory {

	/** spring资源对象 **/
	private ApplicationContext ctx = ResourceBuffer.getInstance().getCtx();

	public PackageCodeFactory() {
		addMessageEncoder(BodyMessage.class,
				(PackageEncoder) ctx.getBean("packageEncoder"));
		addMessageDecoder((PackageDecoder) ctx.getBean("packageDecoder"));
	}
}
