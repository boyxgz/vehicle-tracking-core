package com.surelution.vt;

import java.util.Arrays;

abstract public class BaseAction {
	
	private int[] inputNums;

	public BaseAction() {
		
	}
	
	protected int[] getInputNums() {
		return inputNums;
	}

	/**
	 * copy the original nums to local
	 * @param nums
	 */
	protected void feed(int[] nums) {
		inputNums = Arrays.copyOf(nums, nums.length);
	}

	abstract public int[] execute();
	
	abstract public boolean accept();
	
	protected String getDeviceId() {
		byte[] bytes = { 
				(byte) inputNums[1], 
				(byte) inputNums[2],
				(byte) inputNums[3], 
				(byte) inputNums[4], 
				(byte) inputNums[5],
				(byte) inputNums[6]
			};
		char temp[] = new char[bytes.length * 2], val;
		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}
	
	protected int getCmdType() {
		return inputNums[7];
	}
	
	protected int getCmdId() {
		return inputNums[8];
	}
	
	protected int[] getCmd() {
		return null;
	}
	
	protected int getCmdLength() {
		return inputNums[9] * 256 + inputNums[10];
	}
	
	protected int getSignSum() {
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("device id:");
		sb.append(getDeviceId());
		sb.append(", cmd type:");
		sb.append(Integer.toHexString(getCmdType()));
		sb.append(",cmd id:");
		sb.append(Integer.toHexString(getCmdId()));
		sb.append(",cmd length:");
		sb.append(getCmdLength());
		sb.append(",content:");
		for(int i : inputNums) {
			sb.append(Integer.toHexString(i));
			sb.append(",");
		}
		return sb.toString();
	}
}
