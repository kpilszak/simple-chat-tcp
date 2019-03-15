import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.BasicConfigurator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@Data
@Log4j
public class Chat {

    private Socket socket;
    private JFrame frame = new JFrame();
    private JTextArea textArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(textArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JPanel panel = new JPanel();
    private JTextField textField = new JTextField();
    private JButton buttonSend = new JButton("Send");
    private ReceiverThread receiverThread;
    private DataOutputStream output;
    private DataInputStream input;


    private Action action = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            String appendText = "You: " + textField.getText() + "\n";
            textArea.append(appendText);

            try {
                output.writeUTF(textField.getText());
            } catch (IOException exp) {
                log.warn("Cannot send message.", exp);
            }

            textField.setText("");
        }
    };


    public Chat(String chatFrameTitle, Socket socket) {
        frame.setTitle(chatFrameTitle);
        this.socket = socket;
        BasicConfigurator.configure();
        setupSocket();
        initialize();
    }


    public void setupSocket() {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            log.warn("Cannot open streams.", e);
        }

        receiverThread = new ReceiverThread();
        receiverThread.start();
    }

    public void initialize() {
        textArea.setEditable(false);
        textField.setSize(400, 100);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(textField);
        panel.add(buttonSend);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(scrollPane);
        textField.addActionListener(action);
        buttonSend.addActionListener(action);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.addWindowListener(new WindowHandler());
        frame.setVisible(true);
    }


    public void writeMessage(String message) {
        if (!"quit".equalsIgnoreCase(message)) {
            textArea.append("Other: " + message + "\n");
        } else {
            try {
                output.writeUTF("quit");
                input.close();
                output.close();
                socket.close();
            } catch (IOException e) {
                log.warn("Cannot close streams.", e);
            }
        }
    }



    class WindowHandler extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            try {
                output.writeUTF("quit");
            } catch (IOException exp) {
                log.warn("Cannot send \"quit\" message", exp);
            }
        }
    }

    class ReceiverThread extends Thread {

        public void run() {
            while (!interrupted()) {
                try {
                    String message = input.readUTF();
                    writeMessage(message);
                } catch (IOException e) {
                    log.warn("Cannot read message.", e);
                }
            }
        }
    }
}
