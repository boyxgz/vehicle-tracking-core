package com.surelution.vt.core;

public class MessageFactory {

	public static Message create(int[] rawContent) {
		if(rawContent[7] == 0x10 && rawContent[8] == 0x88) {
			return new HeartbeatMessage(rawContent);
		}
		return new Message(rawContent);
	}
}
