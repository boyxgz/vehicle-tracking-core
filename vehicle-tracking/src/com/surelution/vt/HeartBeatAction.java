package com.surelution.vt;

/**
 * 
 * @author <a href="mailto:guangzong.syu@gmail.com">guagnzong</a>
 *
 */
public class HeartBeatAction extends BaseAction {

	@Override
	public int[] execute() {
		System.out.print("心跳，来自：");
		System.out.println(getDeviceId());
		return null;
	}

	@Override
	public boolean accept() {
		return getCmdType() == 0x10 && getCmdId() == 0x88;
	}

}
