

import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 * @author <a href="mailto:guangzong.syu@gmail.com">guagnzong</a>
 *
 */
public class Client {

	public static void main(String[] args) throws Exception{
		try(Socket s = new Socket("116.226.201.243", 12345)) {
			OutputStream os = s.getOutputStream();
//			int[] bs = {0x28,0x88,0x88,0x88,0x88,0x88,0x88,0x20,0x84,0x0,0x34,0x1,0x0,0x1,0x1,0x1,0x10,0x0,0x0,0x1,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x6,0x0,0x0,0x0,0x12,0x0,0x0,0x1,0x1b,0x0,0x10,0x1,0x0,0x20,0x7d,0x12,0xf,0x5c,0x20,0x20,0x7a,0x78,0x0,0x74,0x20,0x20,0x20,0x20,0xff,0x20,0x0,0xe,0x0,0x10,0xc1,0xf4,0x29}; //复合上传
			int[] bs = {0x28,0x88,0x88,0x88,0x88,0x88,0x88,0x10,0x88,0x0,0x0,0x98,0x29}; //心跳
			for(int i : bs) {
				os.write(i);
			}
			os.flush();
		}
	}
}
