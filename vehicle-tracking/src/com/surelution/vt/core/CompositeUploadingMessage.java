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
	
	/**
	 * @return 行程ID
	 */
	public int getTripId() {
		return content[1] * 256 + content[2];
	}

	/**
	 * @return 时间
	 */
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
	
	/**
	 * 
	 * @return 纬度
	 */
	public double getLatitude() {
		String temp = BcdCode.convertToString(content[9]) 
				+ BcdCode.convertToString(content[10])
				+ "."
				+ BcdCode.convertToString(content[11])
				+ BcdCode.convertToString(content[12]);
		return Double.parseDouble(temp);
	}
	
	/**
	 * 
	 * @return 经度
	 */
	public Double getLongitude() {
		StringBuffer temp = new StringBuffer();
		temp.append(BcdCode.convertToString(content[13])); 
		temp.append(BcdCode.convertToString(content[14]));
		temp.append(BcdCode.convertToString(content[15]));
		temp.append(BcdCode.convertToString(content[16]));
		temp.append(BcdCode.convertToString(content[17]));
		return Double.parseDouble(temp.substring(0, temp.length() - 1)) / 100000;
	}
	/**
	 * 
	 * @return 车速
	 */
	public Double getSpeed() {
		return (double)content[18];
	}
	
	/**
	 * 
	 * @return 方向
	 */
	public Double getOrientation(){
		return (double)content[19];
	}
	
	/**
	 * 
	 * @return 卫星个数
	 */
	public Integer getGPSNumber(){
		return (int)content[20];
	}
	
	/**
	 * @return GSM信号强度
	 */
	public Integer getGsmIntensity(){
		return (int)content[21];
	}
	
	/**
	 * 
	 * @return 里程数
	 */
	public Double getMileage(){
		return (double)content[22] * 1000000 + content[23]*10000 + content[24]*100 + content[25];
	}
	
	/**
	 * @return 设备状态
	 */
	public DeviceStatus getDeviceState(){
		DeviceStatus ds = new DeviceStatus(new int[]{content[26],content[27],content[28],content[29]});
		return ds;
	}
	
	/**
	 * @return 负荷计算值  计算方式：byte*100/255 单位%
	 */
	public Double getLoad(){
		return (double)(content[30] / 255);
	}
	
	/**
	 * @return 冷却液温度	计算方式 byte - 40
	 */
	public Double getCoolantTemperature(){
		return (double)content[31] - 40;
	}
	
	/**
	 * @return 发动机转速度	计算方式(byte1*256+byte2)/4	单位RPM
	 */
	public Double getRotateSpeed(){
		return (double)(content[32]*256+content[33])/4.0;
	}
	
	/**
	 * 
	 * @return OBD车速
	 */
	public Double getOBDSpeed(){
		return (double)content[34];
	}
	
	/**
	 * @return 点火提前角	计算方式：byte/2-64		单位°
	 */
	public Integer getAngle(){
		return content[35] / 2 -64;
	}
	
	/**
	 * @return 进气歧管绝对压力	单位kpa
	 */
	public Integer getKpa(){
		return content[36];
	}
	
	/**
	 * 
	 * @return 控制模块的电压	计算方式：byte/10	单位V
	 */
	public Integer getVoltage(){
		return content[37]/10;
	}
	
	/**
	 * @return 进气温度	计算方式：byte-40
	 */
	public Double getInletTemperature(){
		return (double)content[38] - 40;
	}
	
	/**
	 * @return 空气流量	计算方式：(byte1*256+byte2)/100	单位g/s	
	 */
	public Double getAirFlow(){
		return (double)(content[39] * 256 + content[40]) / 100;
	}
	
	/**
	 * @return 节气门相对位置	计算公式：byte*100/255	单位%
	 * 			但是此将存为小数形式
	 */
	public Double getRelativeLocation(){
		return (double)content[41] / 255.0;
	}
	
	/**
	 * @return 长期流量修正	计算公式：(byte-128)*100/128	单位%
	 * 			但此处将存储为小数形式
	 */
	public Double getAmendFuelOil(){
		return (double)(content[42] - 128)/128.0; 
	}
	
	/**
	 * @return 空燃比	计算公式：(byte1*256+byte2)*0.0000305
	 */
	public Double getRatio(){
		return (double)(content[43] * 265 + content[44]) * 0.0000305;
	}
	
	/**
	 * @return 节气们绝对位置	计算公式：byte*100/255	单位%
	 * 			但此处将存储为小数形式
	 */
	public Double getAbsoluteLocation(){
		return (double)content[45] / 255;
	}
	
	/**
	 * @return 燃油压力	计算公式：byte*3	单位kpa
	 */
	public Double getFuelOilKpa(){
		return (double)content[46] * 3;
	}
	
	/**
	 * @return 瞬时耗油1,	(byte1*256 + byte2)/10	单位l/h
	 */
	public Double getInstantaneousFuelHour(){
		return (double)(content[47]*256 + content[48])/10;
	}
	
	/**
	 * @return 瞬时耗油2	(byte1*256 + byte2)/10	单位l/100km
	 */
	public Double getInstantaneousFuelKM(){
		return (double)(content[49]*256 + content[50])/10;
	}
	
	/**
	 * @return  流水号
	 */
	public Integer getNumber(){
		return (int)content[51];
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("请求/上传 GPS + OBD 混合信息：");
		sb.append(super.toString());
		sb.append("\n trip id: ");
		sb.append(getTripId());
		sb.append("\n date: ");
		sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getDate()));
		sb.append("\n Latitude: ");
		sb.append(getLatitude());
		sb.append("\n Longitude: ");
		sb.append(getLongitude());
		sb.append("\n spped: ");
		sb.append(getSpeed());
		sb.append("\n orientation: ");
		sb.append(getOrientation());
		sb.append("\n GPSNumber: ");
		sb.append(getGPSNumber());
		sb.append("\n GsmIntensity:");
		sb.append(getGsmIntensity());
		sb.append("\n Mileage: ");
		sb.append(getMileage());
		sb.append("\n DeviceState: ");
		sb.append(getDeviceState());
		sb.append("\n Load: ");
		sb.append(getLoad());
		sb.append("\n CoolantTemperature;");
		sb.append(getCoolantTemperature());
		sb.append("\n RotateSpeed: ");
		sb.append(getRotateSpeed());
		sb.append("\n OBDSpeed: ");
		sb.append(getOBDSpeed());
		sb.append("\n Angle: ");
		sb.append(getAngle());
		sb.append("\n Kpa: ");
		sb.append(getKpa());
		sb.append("\n Voltage: ");
		sb.append(getVoltage());
		sb.append("\n InletTemperature: ");
		sb.append(getInletTemperature());
		sb.append("\n AirFlow: ");
		sb.append(getAirFlow());
		sb.append("\n RelativeLocation: ");
		sb.append(getRelativeLocation());
		sb.append("\n AmendFuelOil: ");
		sb.append(getAmendFuelOil());
		sb.append("\n AbsoluteLocation: ");
		sb.append(getAbsoluteLocation());
		sb.append("\n FuelOilKpa: ");
		sb.append(getFuelOilKpa());
		sb.append("\n InstantaneousFuelHour: ");
		sb.append(getInstantaneousFuelHour());
		sb.append("\n InstantaneousFuelKM: ");
		sb.append(getInstantaneousFuelKM());
		sb.append("\n Number: ");
		sb.append(getNumber());
		return sb.toString();
	}

}
