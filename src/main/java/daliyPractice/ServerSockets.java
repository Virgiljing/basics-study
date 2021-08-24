package daliyPractice;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSockets {
	private ServerSocket server;
	public void setUpServer(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("已创建服务器"+port);
			Socket client = server.accept();
			OutputStream out =client.getOutputStream();
			InputStream in = client.getInputStream();
			out.write("欢迎来到聊天室\r\n".getBytes());
			out.write("输入quit退出\r\n".getBytes());
			String s = new String();
			while (true) {
			char ch = (char) in.read();
			System.out.println();
			System.out.print("用户输入：" + ch);
			if (ch == '\n') {
			out.write(("服务器收到:\r" + s).getBytes());
			out.write("\r\n".getBytes());
			s = new String();
			}
			s = s + ch;
			if (s.contains("quit")) {
			break;
			}
			}
			out.flush();
			out.close();


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
