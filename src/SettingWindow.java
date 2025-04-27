import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.util.Objects.isNull;

public class SettingWindow extends JFrame {
    JButton btnStart = new JButton("Старт");
    JTextField fieldLenInput = new JTextField("3", 1);
    int fieldLen;

    SettingWindow(GameWindow gameWindow) {

        setResizable(false);
        setSize(gameWindow.getSize());


        add(btnStart, BorderLayout.SOUTH);
        add(fieldLenInput, BorderLayout.NORTH);
        fieldLenInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldLen = Integer.parseInt(fieldLenInput.getText());
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gameWindow.map.startNewGame(0, fieldLen, fieldLen, fieldLen);
                setVisible(false);
            }
        });


    }


}
