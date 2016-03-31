package com.surelution.vt;


/**
 * 所有的上行消息处理类都要继承此类<br/>
 * @author <a href="mailto:guangzong.syu@gmail.com">guagnzong</a>
 *
 */
abstract public class BaseAction {

	private Message message;

	protected Message getMessage() {
		return message;
	}

	/**
	 * copy the original nums to local
	 * @param nums
	 */
	protected void feed(Message message) {
		this.message = message;
	}

	/**
	 * 处理上行消息
	 * @return 处理后下行给设备的二进制数据，如果不返回，则return null
	 */
	abstract public int[] execute();

	/**
	 * 判断是否有此类处理这个上行消息，如果true，则由此类处理本上行消息
	 * @return 是否由此类处理本上行消息
	 */
	abstract public boolean accept();
}
