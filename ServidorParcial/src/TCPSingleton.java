import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class TCPSingleton extends Thread {
	
	
	private static TCPSingleton tcpsingleton;

	private TCPSingleton() {}
	
	
	
	public static TCPSingleton getInstance() {
		if(tcpsingleton == null) {
			tcpsingleton = new TCPSingleton();
		}
		return tcpsingleton;
	}
	
	private Observer observer;
	private Socket socket;
	private ServerSocket server;
	private BufferedWriter writer;

	
	
	public void setObserver(Observer observer) {
		this.observer = observer;
	}
	
	
	@Override
	public void run() {
		try {
			//
			System.out.println("Esperando en el 5000...");
			server = new ServerSocket(5000);
			socket = server.accept();
			System.out.println("Conectamos");
			
			
			//
			InputStream is = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			writer = new BufferedWriter(new OutputStreamWriter(out));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			
			//
			while(true) {
				System.out.println("Esperando mensaje...");
				String lastMessage = reader.readLine();
				observer.mensajeRecibido(lastMessage);
			}
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
