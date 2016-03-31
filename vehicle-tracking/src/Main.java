

import java.net.ServerSocket;
import java.net.Socket;

import com.surelution.vt.core.Connection;

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
				Thread t = new Thread(new Connection(s));
				t.start();
			}
		
		}
	}
}
