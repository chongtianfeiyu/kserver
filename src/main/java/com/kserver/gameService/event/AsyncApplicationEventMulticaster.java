package com.kserver.gameService.event;

import java.util.Iterator;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.AbstractApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * 异步事件处理器
 * 
 * @author ksfzhaohui
 * 
 */
public class AsyncApplicationEventMulticaster extends
		AbstractApplicationEventMulticaster {

	private TaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = (taskExecutor != null ? taskExecutor
				: new SimpleAsyncTaskExecutor());
	}

	protected TaskExecutor getTaskExecutor() {
		return this.taskExecutor;
	}

	@SuppressWarnings("unchecked")
	public void multicastEvent(final ApplicationEvent event) {
		for (Iterator<ApplicationListener> it = getApplicationListeners()
				.iterator(); it.hasNext();) {
			final ApplicationListener listener = it.next();
			getTaskExecutor().execute(new Runnable() {
				public void run() {
					listener.onApplicationEvent(event);
				}
			});
		}
	}
}
