import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@Data
public class ChatFrame extends Frame {

    private String chatFrameName;
    private JFrame frame = new JFrame();
    private JTextArea textArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(textArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JPanel panel = new JPanel();
    private JTextField textField = new JTextField();
    private JButton buttonSend = new JButton("Send");

    private Action action = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            String appendText = "You: " + textField.getText() + "\n";
            textArea.append(appendText);
            textField.setText("");
        }
    };


    public ChatFrame(String chatFrameName) {
        this.chatFrameName = chatFrameName;
    }


    public void drawChatFrame() {
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
        frame.setTitle(chatFrameName);
        frame.setVisible(true);
    }
}
