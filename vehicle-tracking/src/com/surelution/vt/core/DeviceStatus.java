/**
 * 
 */
package com.surelution.vt.core;

/**
 * @author <a href="mailto:guangzong.syu@gmail.com">guagnzong</a>
 *
 */
public class DeviceStatus {

	private int[] content;

	public DeviceStatus(int[] content) {
		this.content = content;
	}
	
	public boolean isLongTermIdle() {
		return (content[0] & 1) == 1;
	}
	
	/**
	 * 
	 * @return 续航里程是否大于50km
	 */
	public boolean isContinuousVoyage(){
		return (content[1] & 1) == 0 ;
	}
	
	/**
	 * @return 发动机是否故障
	 */
	public boolean isEngineFailure(){
		return (content[1] & 2) == 0;
	}
	
	/**
	 * @return 冷却液温度是否过低，正数为过高，负数为过低，0为正常
	 */
	public int isCoolantTemperature(){
		if((content[1] & 4) != 0){
			//过低
			return -1;
		}
		if((content[3] & 8) != 0){
			return 1;
		}
		return 0;
	}
	
	/**
	 * @return 该车是否支持
	 */
	public boolean isSupport(){
		return (content[1] & 8) == 0;
	}
	
	/**
	 * @return 拖吊状态
	 */
	public boolean isTowed(){
		return (content[1] & 64) == 0;
	}
	
	/**
	 * @return ACC状态,true表关，false表开
	 */
	public boolean isAccOff(){
		return (content[2] & 1) == 0;
	}
	
	/**
	 * @return 左前门状态，true表关，false表开
	 */
	public boolean isLeftFrontDoorClosed(){
		return (content[2] & 2) == 0;
	}
	
	/**
	 * @return 右前门，true表关，false表开
	 */
	public boolean isRightFrontDoorClosed(){
		return (content[2] & 4) == 0;
	}
	
	/**
	 * @return 左后门，true表关，false表开
	 */
	public boolean isLeftBackDoorClosed(){
		return (content[2] & 8) == 0;
	}
	
	/**
	 * @return 右后门，true表关，false表开
	 */
	public boolean isRightBackDoorClosed(){
		return (content[2] & 16) == 0;
	}
	
	/**
	 * @return 尾箱状态 true表关，false表开
	 */
	public boolean isTrunkLidClosed(){
		return (content[2] & 32) == 0;
	}
	
	/**
	 * @return 中控锁状态 true表示上锁，false表是没上锁
	 */
	public boolean isControlLocked(){
		return (content[2] & 64) != 0; 
	}
	
	/**
	 * @return 电瓶电压，true表示正常，false表示电压过低。
	 */
	public boolean isVoltage(){
		return (content[2] & 128) == 0;
	}
	
	/**
	 * @return gsp模块是否正常， true为正常。false为异常
	 */
	public boolean isGPSStatus(){
		return (content[3] & 1) == 0;
	}

	/**
	 * @return 是否超速 true表正常，false表超速
	 */
	public boolean isSpeedLimit(){
		return (content[3] & 2) == 0;
	}
	
	/**
	 * 
	 * @return 是否疲劳驾驶	true为正常，false为疲劳驾驶
	 */
	public boolean isFaigue(){
		return (content[3] & 4) == 0;
	}
	
	/**
	 * 
	 * @return 充电电路是否正常，true为正常，false为异常
	 */
	public boolean isCircuit(){
		return (content[3] & 8) == 0;
	}
	
	/**
	 * 
	 * @return 是否需要保养了，true为正常，false为需要保养了。
	 */
	public boolean isMaintenance(){
		return (content[3] & 32) == 0;
	}
	
	/**
	 * @return 节气门清理，true为正常，false为需要清理
	 */
	public boolean isCleanThrottle(){
		return (content[3] & 64) == 0;
		
	}
	
	/**
	 * @return 插拔状态	true为正常，false为拔出
	 */
	public boolean isPluggedIn(){
		return (content[3] & 128) == 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("LongTermIdle:");
		sb.append(isLongTermIdle());
		sb.append("\nEngineFailure ");
		sb.append(isEngineFailure());
		sb.append("\nCoolantTemperature ");
		sb.append(isCoolantTemperature());
		sb.append("\nSupport:");
		sb.append(isSupport());
		sb.append("\nTowed:");
		sb.append(isTowed());
		sb.append("\nAccOff:");
		sb.append(isAccOff());
		sb.append("\nLeftFrontDoorClosed:");
		sb.append(isLeftFrontDoorClosed());
		sb.append("\nRightFrontDor ");
		sb.append(isRightFrontDoorClosed());
		sb.append("\nLeftBackDoor ");
		sb.append(isLeftBackDoorClosed());
		sb.append("\nRightBackDoor ");
		sb.append(isRightBackDoorClosed());
		sb.append("\nTrunk ");
		sb.append(isTrunkLidClosed());
		sb.append("\nControlLock ");
		sb.append(isControlLocked());
		sb.append("\nVoltage ");
		sb.append(isVoltage());
		sb.append("\nGPSStatus ");
		sb.append(isGPSStatus());
		sb.append("\nSpeedLimit ");
		sb.append(isSpeedLimit());
		sb.append("\nFaigue ");
		sb.append(isFaigue());
		sb.append("\nCircuit ");
		sb.append(isCircuit());
		sb.append("\nMaintenance ");
		sb.append(isMaintenance());
		sb.append("\nCleanThrottle ");
		sb.append(isCleanThrottle());
		sb.append("\nPlugStatu ");
		sb.append(isPluggedIn());
		return sb.toString();
	}
}
