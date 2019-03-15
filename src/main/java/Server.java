import lombok.extern.log4j.Log4j;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;

@Log4j
public class Server {

    public static void main(String[] args) throws IOException {

        BasicConfigurator.configure();

        ResourceBundle rb = ResourceBundle.getBundle("config");

        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(rb.getString("ListeningPort")));

        while(true) {
            Socket socket = serverSocket.accept();
            Chat chat = new Chat("Server", socket);
        }
    }
}
