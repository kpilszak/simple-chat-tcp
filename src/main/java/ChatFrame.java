import javax.swing.*;
import lombok.Getter;

@Data
public class ChatFrame {

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
        frame.setSize(300, 300);
        frame.setTitle(chatFrameName);
        frame.setVisible(true);
    }
}
