import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
//    private static final int WINDOW_HEIGHT = 230; //555;
//    private static final int WINDOW_WIDTH = 350; //507;

    JButton btnStart = new JButton("Старт");


    SettingWindow(GameWindow gameWindow) {

        setResizable(false);
        setSize(gameWindow.getSize());

        add(btnStart);



btnStart.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        gameWindow.map.startNewGame(0,6,6,6);
        setVisible(false);
    }
});



    }


}
