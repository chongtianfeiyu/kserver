package com.kserver.gameService;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.springframework.context.ApplicationContext;

import com.kserver.protocol.BodyMessage;
import com.kserver.protocol.dataType.AProtocol;
import com.kserver.protocol.dataType.ProtocolByte;
import com.kserver.protocol.dataType.ProtocolObj;
import com.kserver.util.SysConstant;
import com.kserver.util.ResourceBuffer;

public class GameHandler extends IoHandlerAdapter {

	private static final GameHandler gameHandler = new GameHandler();
	private static Logger logger = Logger
			.getLogger(GameHandler.class.getName());

	/** 最大的缓冲区大小 **/
	private static final int MAX_BUFFER_SIZE = 2 * 1024;
	/** session最大允许空闲时间(单位:s) **/
	private static final int IDLE_TIME = 90;
	/** spring资源对象 **/
	private ApplicationContext ctx = ResourceBuffer.getInstance().getCtx();

	private GameHandler() {
	}

	public static GameHandler getInstance() {
		return gameHandler;
	}

	/**
	 * 创建Session时触发(每个session只会出现一次)
	 */
	@Override
	public void sessionCreated(IoSession session) {
		IoSessionConfig cfg = session.getConfig();
		if (cfg instanceof SocketSessionConfig) {
			SocketSessionConfig scfg = (SocketSessionConfig) cfg;
			scfg.setReceiveBufferSize(MAX_BUFFER_SIZE);
		}
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, IDLE_TIME);
	}

	/**
	 * session连接时触发(注：创建的session可能会打开多次连接)
	 */
	@Override
	public void sessionOpened(IoSession session) {
	}

	/**
	 * 关闭session
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
	}

	/**
	 * session超过最大允许空闲时间时触发
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		session.close(true);
	}

	/**
	 * 出现任何异常
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.error("接受协议异常", cause);
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		List<AProtocol> returnList = new ArrayList<AProtocol>();
		if (message instanceof BodyMessage) {
			BodyMessage bodyMessage = (BodyMessage) message;
			List<? extends AProtocol> protocolList = (List<? extends AProtocol>) bodyMessage
					.getProtocolList();

			ACommand command = null;
			List<AProtocol> list = null;
			for (AProtocol aProtocol : protocolList) {
				try {
					command = (ACommand) ctx.getBean(SysConstant.COMMAND_PREFIX
							+ aProtocol.getProtocolID());
					if (aProtocol instanceof ProtocolObj) {
						ProtocolObj protocolObj = (ProtocolObj) aProtocol;
						list = command.doCommand(protocolObj.getMessage());
					} else if (aProtocol instanceof ProtocolByte) {
						ProtocolByte protocolByte = (ProtocolByte) aProtocol;
						list = command
								.doCommand(protocolByte.getProtocolData());
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					continue;
				}
				if (list != null) {
					returnList.addAll(list);
				}
			}

			session.write(new BodyMessage(returnList));
		}
	}

}
