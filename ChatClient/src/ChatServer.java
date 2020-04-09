import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class ChatServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatServer().start();
	}
	
	void start() {
		ServerSocket ss;
		Socket s;
		
		boolean bstarted=false;
		
		try {
			ss=new ServerSocket(50000);
			bstarted=true;
			
			while(bstarted) {
				s=ss.accept();
				System.out.println("A Client connected");
				
				new Thread(new Client(s)).start();
			}
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	
	class Client implements Runnable{

		Socket s;
		DataInputStream dis;
		
		Client(Socket s){
			this.s=s;
			try {
				dis=new DataInputStream(s.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				try {
					System.out.println(dis.readUTF());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	}
