package com.surelution.vt.core;

public class HeartbeatMessage extends Message {

	public HeartbeatMessage(int[] rawContent) {
		super(rawContent);
	}

	@Override
	public String toString() {
		return "心跳包：" + super.toString();
	}
}
