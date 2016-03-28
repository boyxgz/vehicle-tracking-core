package com.surelution.vt;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main2 {

	public static void main(String[] args) throws Exception {
		ArrayList<Integer> buffer = new ArrayList<>();
		ServerSocket ss = new ServerSocket(12345);
		Socket s = ss.accept();
		InputStream is = s.getInputStream();
		int i = is.read();
		while(i >= 0) {
			buffer.add(i);
			if(i == 0x29) {
				int[] nums = new int[buffer.size()];
				for(int index = 0; index < buffer.size(); index++) {
					nums[index] = buffer.get(index);
				}
				pop(nums);
			}
			i = is.read();
		}
	}

	private static void pop(int[] nums) {
		for(int i : nums) {
			System.out.print(i);
			System.out.print(",");
		}
		System.out.println();
	}
}
