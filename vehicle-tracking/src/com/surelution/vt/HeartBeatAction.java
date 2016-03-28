package com.surelution.vt;

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
