package com.surelution.vt.core;

public class MessageFactory {

	public static Message create(int[] rawContent) {
		if(rawContent[7] == 0x10 && rawContent[8] == 0x88) {
			return new HeartbeatMessage(rawContent);
		} else if(rawContent[7] == 0x20 && (rawContent[8] == 0x04 || rawContent[8] == 0x84)) {
			return new CompositeUploadingMessage(rawContent);
		}
		return new Message(rawContent);
	}
	
	public static void main(String[] args) {
		int[] content = {0x28,0x88,0x88,0x88,0x88,0x88,0x88,0x20,0x84,0x0,0x34,0x1,0x0,0x1,0x1,0x1,0x10,0x0,0x0,0x1,0x12,0x34,0x56,0x78,0x12,0x34,0x56,0x78,0x91,0x0,0x0,0x0,0x12,0x0,0x0,0x1,0x1b,0x0,0x10,0x1,0x0,0x20,0x7d,0x12,0xf,0x5c,0x20,0x20,0x7a,0x78,0x0,0x74,0x20,0x20,0x20,0x20,0xff,0x20,0x0,0xe,0x0,0x10,0xc1,0xf4,0x29};
//		int[] content = {0x28,0x88,0x88,0x88,0x88,0x88,0x88,0x10,0x88,0x0,0x0,0x98,0x29};
		Message msg = create(content);
		System.out.println(msg);
	}
}
