package com.surelution.vt;

import java.util.Arrays;

/**
 * 所有的上行消息处理类都要继承此类<br/>
 * @author <a href="mailto:guangzong.syu@gmail.com">guagnzong</a>
 *
 */
abstract public class BaseAction {
	
	private int[] inputNums;

	/**
	 * 
	 * @return 上行的内容本身
	 */
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

	/**
	 * 处理上行消息
	 * @return 处理后下行给设备的二进制数据，如果不返回，则return null
	 */
	abstract public int[] execute();

	/**
	 * 判断是否有此类处理这个上行消息，如果true，则由此类处理本上行消息
	 * @return 是否由此类处理本上行消息
	 */
	abstract public boolean accept();

	/**
	 * @return 产品ID号
	 */
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

	/**
	 * 
	<table style="border-collapse:collapse;">
		<th>
			<tr>
				<td>命令</td>
				<td>命令类型<br/>(命令字的第一个字节)</td>
				<td>注解</td>
			</tr>
		</th>
		<tbody>
			<tr>
				<td>通用命令</td>
				<td>0x00</td>
				<td>该类型命令适应于所有的产品,包括查询产品软件版本、制造商、产品ID、设备自检重启,设备报警等</td>
			</tr>
			<tr>
				<td>GSM 命令</td>
				<td>0x10</td>
				<td>该类型命令主要用于设置GSM模块相关,包括设置IP地址、端口、是否启用基站定位等</td>
			</tr>
			<tr>
				<td>GPS命令</td>
				<td>0x20</td>
				<td>该类型命令主要用于GPS相关功能,包括GPS数据定时上传、上传的间隔、上传的方式等</td>
			</tr>
			<tr>
				<td>OBD命令</td>
				<td>0x30</td>
				<td>该类型命令主要用于OBD相关功能,包括OBD数据的上传、行程报告上传、上传的间隔、方式等</td>
			</tr>
			<tr>
				<td>升级命令</td>
				<td>0x40</td>
				<td>该类型命令主要用于设备进行IAP升级,必须外扩flash的产品才支持该功能</td>
			</tr>
			<tr>
				<td>外设命令</td>
				<td>0x50</td>
				<td>该类型命令主要用于以后产品加载各种外设准备,外设不同,该命令类型也相应不同,分别为0x50,0x51,0x52......</td>
			</tr>
		</tbody>
	</table>
	 * @return 命令类型
	 */
	protected int getCmdType() {
		return inputNums[7];
	}

	/**
	 * 
	 * @return 命令编号
	 */
	protected int getCmdId() {
		return inputNums[8];
	}

	/**
	 * TODO 去除转移
	 * @return 去除格式内容，如头、尾等，去除转移符后的净内容
	 */
	protected int[] getContent() {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	protected int getContentLength() {
		return inputNums[9] * 256 + inputNums[10];
	}

	/**
	 * 
	 * @return 校验和
	 */
	protected int getCheckSum() {
		return inputNums[inputNums.length - 2];
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
		sb.append(getContentLength());
		sb.append(",content:");
		for(int i : inputNums) {
			sb.append(Integer.toHexString(i));
			sb.append(",");
		}
		return sb.toString();
	}
}
