package com.surelution.vt;

import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception{
		try(Socket s = new Socket("116.226.201.243", 12345)) {
			OutputStream os = s.getOutputStream();
			int[] bs = {0x28,0x30,0x14,0x04,0x14,0x00,0x01,0x20,0x84,0x00,0x34,0x01,0x00,0x03,0x28,0x04,0x14,0x09,0x00,0x04,0x22,0x43,0x88,0x16,0x11,0x34,0x91,0x23,0x97,0x00,0x60,0x09,0x17,0x94,0xD0,0x00,0x00,0x01,0x00,0x00,0x00,0xA5,0x43,0x88,0x16,0x11,0x34,0x91,0x23,0x97,0x00,0x60,0x09,0x17,0x94,0xD0,0x00,0x00,0x01,0x00,0x00,0x00,0xA5,0xDF,0x29};
			for(int i : bs) {
				os.write(i);
			}
			os.flush();
		}
	}
}
