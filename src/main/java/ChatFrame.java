import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class ChatFrame extends Frame {

    private String chatFrameName;
    private JFrame frame = new JFrame();
    private JTextArea txtArea = new JTextArea();
    private JTextField txtField = new JTextField();
    private JButton btnSend = new JButton("Send");


    public ChatFrame(String chatFrameName) {
        this.chatFrameName = chatFrameName;
    }



    public void drawChatFrame() {
        frame.getContentPane().add(btnSend);
        btnSend.addActionListener(new SendHandler());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setTitle(chatFrameName);
        frame.setVisible(true);
    }


    private class SendHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String appendText = "You: " + txtField.getText() + "\n";
            txtArea.append(appendText);
        }
    }
}
