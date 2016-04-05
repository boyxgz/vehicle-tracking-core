package com.surelution.vt.core;

public class BcdCode {

	public static char[] convertToChars(int b) {
		char temp[] = new char[2], val;
		val = (char) (((b & 0xf0) >> 4) & 0x0f);
		temp[0] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		val = (char) (b & 0x0f);
		temp[1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		return temp;
	}
	
	public static String convertToString(int b) {
		char temp[] = convertToChars(b);
		return new String(temp);
	}
	
	public static int convertToInteger(int b) {
		return Integer.parseInt(convertToString(b));
	}
}
