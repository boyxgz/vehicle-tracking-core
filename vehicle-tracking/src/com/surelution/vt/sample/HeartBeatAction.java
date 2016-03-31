package com.surelution.vt.sample;

import com.surelution.vt.core.BaseAction;

/**
 * 
 * @author <a href="mailto:guangzong.syu@gmail.com">guagnzong</a>
 *
 */
public class HeartBeatAction extends BaseAction {

	@Override
	public int[] execute() {
		System.out.print("心跳，来自：");
		System.out.println(getMessage().getDeviceId());
		System.out.println(getMessage());
		return null;
	}

	@Override
	public boolean accept() {
		return getMessage().getCmdType() == 0x10 && getMessage().getCmdId() == 0x88;
	}

}