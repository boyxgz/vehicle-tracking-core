package com.surelution.vt.core;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Connection implements Runnable {
	
	private static Map<String, Connection> connections = new HashMap<>();

	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private String deviceId;
	private Date connectedAt;
	private Date lastMessageAt;

	public Connection(Socket socket) {
		this.socket = socket;
		try{
			is = this.socket.getInputStream();
			os = this.socket.getOutputStream();
			connectedAt = new Date();
		}catch(Exception e) {
			
		}
	}

	@Override
	public void run() {
		try{
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

						ActionChain chain = ActionChain.getInstance(getClass().getClassLoader());
						Message msg = MessageFactory.create(nums);
						if(deviceId == null) {
							deviceId = msg.getDeviceId();
							Connection oldOne = connections.get(deviceId);
							if(oldOne != this) {
								if(oldOne != null) {
									oldOne.close();//已经存在一个同device id的连接，则把前连接删除
								}
								connections.put(deviceId, this);
							}
						}
						int[] resp = chain.process(msg);
						lastMessageAt = new Date();
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
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDeviceId() {
		return deviceId;
	}

	public Date getConnectedAt() {
		return connectedAt;
	}

	public Date getLastMessageAt() {
		return lastMessageAt;
	}

	/**
	 * 主动下行内容
	 * @param raw content
	 */
	public void send(int[] content) {
		try{
			for(int c : content) {
				os.write((byte)c);
			}
		}catch(Exception e) {
			
		}
	}

	/**
	 * 主动下行内容
	 * @param msg
	 * 
	 */
	public void send(Message msg) {
		//TODO 下行的消息？
	}

	public static Connection findByName(String deviceId) {
		return connections.get(deviceId);
	}
}
