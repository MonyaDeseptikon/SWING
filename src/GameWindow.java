import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


public class GameWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 600;
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

        map = new Map();
        settings = new SettingWindow(this);

        JPanel panelBtn = new JPanel(new GridLayout(1, 2));
        panelBtn.add(btnStart);
        panelBtn.add(btnExit);
        add(panelBtn, BorderLayout.SOUTH);
        add(map);
        setVisible(true);



        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {

            }

            @Override
            public void componentMoved(ComponentEvent e) {

               settings.setLocationRelativeTo(e.getComponent());
            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                settings.setVisible(true);
            }
        });

    }

}
