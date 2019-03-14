import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class ChatFrame extends Frame implements ActionListener {

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
        frame.getDefaultCloseOperation();
        frame.setSize(500, 500);
        frame.setTitle(chatFrameName);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String appendText = txtArea.getText() + "\n" + "You: " + txtField.getText();
        txtArea.setText(appendText);
    }
}
