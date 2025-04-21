import javax.swing.*;
import java.awt.*;


public class GameWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private static final String NAME_OF_WINDOW = "TicTacToe";

    JButton btnStart = new JButton("Новая игра");
    JButton btnExit = new JButton("Выход");
    Map map;
    SettingWindow settings;

       GameWindow() {
        setTitle(NAME_OF_WINDOW);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        Map map = new Map();
        settings = new SettingWindow(this);

        JPanel panelBtn = new JPanel(new GridLayout(1, 2));
        panelBtn.add(btnStart);
        panelBtn.add(btnExit);
        add(panelBtn, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
    }
}
