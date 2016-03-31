package com.surelution.vt.sample;

import com.surelution.vt.core.BaseAction;
import com.surelution.vt.core.HeartbeatMessage;

/**
 * 
 * @author <a href="mailto:guangzong.syu@gmail.com">guagnzong</a>
 *
 */
public class HeartBeatAction extends BaseAction {

	@Override
	public int[] execute() {
		System.out.println(getMessage().getDeviceId());
		System.out.println(getMessage());
		return null;
	}

	@Override
	public boolean accept() {
		return getMessage() instanceof HeartbeatMessage;
	}

}
