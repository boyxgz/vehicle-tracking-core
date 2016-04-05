package com.surelution.vt.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CompositeUploadingMessage extends Message {
	
	private int[] content;

	public CompositeUploadingMessage(int[] rawContent) {
		super(rawContent);
		content = getContent();
	}
	
	public int getTripId() {
		return content[1] * 256 + content[2];
	}

	public Date getDate() {
		int year = 2000 + BcdCode.convertToInteger(content[5]);
		int month = BcdCode.convertToInteger(content[4]);
		int day = BcdCode.convertToInteger(content[3]);
		int hour = BcdCode.convertToInteger(content[6]);
		int minute = BcdCode.convertToInteger(content[7]);
		int sec = BcdCode.convertToInteger(content[8]);
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, hour, minute, sec);
		return c.getTime();
	}
	
	public double getLatitude() {
		String temp = BcdCode.convertToString(content[9]) 
				+ BcdCode.convertToString(content[10])
				+ "."
				+ BcdCode.convertToString(content[11])
				+ BcdCode.convertToString(content[12]);
		return Double.parseDouble(temp);
	}
	
	public Double getLongitude() {
		StringBuffer temp = new StringBuffer();
		temp.append(BcdCode.convertToString(content[13])); 
		temp.append(BcdCode.convertToString(content[14]));
		temp.append(BcdCode.convertToString(content[15]));
		temp.append(BcdCode.convertToString(content[16]));
		temp.append(BcdCode.convertToString(content[17]));
		return Double.parseDouble(temp.substring(0, temp.length() - 1)) / 100000;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append(",trip id:");
		sb.append(getTripId());
		sb.append(", date:");
		sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getDate()));
		sb.append(",Latitude:");
		sb.append(getLatitude());
		sb.append(",Longitude:");
		sb.append(getLongitude());
		return sb.toString();
	}

}
