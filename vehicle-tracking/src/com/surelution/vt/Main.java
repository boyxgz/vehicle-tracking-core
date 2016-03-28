package com.surelution.vt;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author <a href="mailto:guangzong.syu@gmail.com">guagnzong</a>
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		int port = 12345;
		if(args != null && args.length > 0) {
			String p = args[0];
			try{
				port = Integer.parseInt(p);
			} catch (NumberFormatException e) {
				
			}
		}
		try(ServerSocket ss = new ServerSocket(port)) {

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
