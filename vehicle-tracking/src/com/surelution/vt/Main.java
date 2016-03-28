package com.surelution.vt;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		try(ServerSocket ss = new ServerSocket(12345)) {

			while(true) {
				final Socket s = ss.accept();
				Thread t = new Thread(new Runnable() {


					@Override
					public void run() {
						try{
							InputStream is = s.getInputStream();
							OutputStream os = s.getOutputStream();

							List<Integer> buffer = Collections.synchronizedList(new ArrayList<Integer>());
							synchronized (buffer) {
								int i = is.read();
								while(i >= 0) {
									buffer.add(i);
									if(i == 0x29) {
										int[] nums = new int[buffer.size()];
										for(int index = 0; index < buffer.size(); index++) {
											nums[index] = buffer.get(index);
										}
										buffer.clear();

										ActionChain chain = ActionChain.getInstance(Main.class.getClassLoader());
										int[] resp = chain.process(nums);
										if(resp != null) {
											for(int c : resp) {
												os.write(c);
											}
											os.flush();
										}
									}
									i = is.read();
								}
							}
						
						}catch(Exception e) {
							
						}
					}
				});
				t.start();
			}
		
		}
	}
}
