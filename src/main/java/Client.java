import lombok.extern.log4j.Log4j;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.net.Socket;
import java.util.ResourceBundle;

@Log4j
public class Client {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        ResourceBundle rb = ResourceBundle.getBundle("config");

        int port = Integer.parseInt(rb.getString("ListeningPort"));

        try {
            Socket clientSocket = new Socket("localhost", 1234);
            Chat chat = new Chat("Client", clientSocket);
        } catch (IOException e) {
            log.warn("Client cannot create connection.", e);
        }
    }
}