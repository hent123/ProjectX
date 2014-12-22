package projectx;

import java.io.IOException;
import java.net.ServerSocket;

public class CFMessagesReceiver {
private ServerSocket utilitySocket;
	//Class to receive known messages from clients
	
	public CFMessagesReceiver() {
		try {
			utilitySocket = new ServerSocket(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public ServerSocket getUtilitySocket(){
	return utilitySocket;
}
	
public int getPort(){
	return utilitySocket.getChannel().socket().getLocalPort();
}
}
