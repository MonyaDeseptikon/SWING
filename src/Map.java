import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.min;

public class Map extends JPanel {
    int panelWidth;
    int panelHeight;
    int cellHeight;
    int cellWidth;
    int mode = 1;
    int fSzX = 1; //fieldSizeX
    int fSzY = 1; //fieldSizeY
    int wLen = 1;
//    int mode;
//    int fSzX; //fieldSizeX
//    int fSzY; //fieldSizeY
//    int wLen;

    private boolean isGameOver;
    private boolean isInitialised;
    private int gameOverType;
    private static final int STATE_DROW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;
    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил компьютер!";
    private static final String MSG_DRAW = "Ничья!";

    private static final Random RANDOM = new Random();
    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;
    private int[][] field;
    private int x;
    private int y;

    public Map() {
        isInitialised = false;
        setBackground(Color.GREEN);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
//        initMap();
    }

    private void initMap() {
        field = new int[fSzY][fSzX];
        for (int i = 0; i < fSzY; i++) {
            for (int j = 0; j < fSzX; j++) {
                field[i][j] = EMPTY_DOT;
            }

        }
//        System.out.println(Arrays.deepToString(field));
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x <= fSzX && y >= 0 && y <= fSzY;
    }

    //откудато появляются не пустые ячейки
    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }

    private boolean chekWin(int dot) {

        for (int y = 0; y < fSzY; y++) {
            int winX = 0;
            for (int x = 0; x < fSzX; x++) {
                if (dot == field[y][x]) winX++;
                if (winX == wLen) return true;
            }
        }
        for (int x = 0; x < fSzX; x++) {
            int winY = 0;
            for (int y = 0; y < fSzY; y++) {
                if (dot == field[y][x]) winY++;
                if (winY == wLen) return true;
            }
        }
        //Проверка по диаганали
        int winD = 0;
        int winRevD = 0;
        int minLenField = min(field[0].length, field[1].length);
        for (int d = 0; d < minLenField; d++) {
            if (dot == field[d][d]) {
                winD++;
                System.out.println(winD);
                if (winD == wLen) return true;
            }
            //Вычитаем единицу, чтобы привести величину длины массива к номеру строки массива
            if (dot == field[minLenField - d-1][d]) {
                winRevD++;
//                System.out.println(winRevD);
                if (winRevD == wLen) return true;
            }

        }
        return false;
    }

    private void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fSzX);
            y = RANDOM.nextInt(fSzY);
        } while ((!isEmptyCell(x, y)));
        field[y][x] = AI_DOT;
    }

    //ничья?
    private boolean isMapFull() {
        for (int i = 0; i < fSzY; i++) {
            for (int j = 0; j < fSzX; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    private void update(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        if (isGameOver || !isInitialised) return;
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
        field[cellY][cellX] = HUMAN_DOT;
        if (chekEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) return;
        repaint();


        aiTurn();
        if (chekEndGame(AI_DOT, STATE_WIN_AI)) return;
        repaint();

//        System.out.printf("x=%d, y=%d\n", cellX, cellY);

    }

    private boolean chekEndGame(int dot, int gameOverType) {
        if (chekWin(dot)) {
            isGameOver = true;
            this.gameOverType = gameOverType;
            repaint();
            return true;
        }
        if (isMapFull()) {
            isGameOver = true;
            this.gameOverType = STATE_DROW;
            repaint();
            return true;
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        int DOT_PADDING = 10;
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / fSzY;
        cellWidth = panelWidth / fSzX;
        g.setColor(Color.BLUE);

        if (!isInitialised) return;

        for (int h = 0; h < fSzY; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int w = 0; w < fSzX; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int y = 0; y < fSzY; y++) {
            for (int x = 0; x < fSzX; x++) {
                if (field[y][x] == EMPTY_DOT) continue;
                if (field[y][x] == HUMAN_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING, cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                } else if (field[y][x] == AI_DOT) {
                    g.setColor(Color.MAGENTA);
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING, cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("Неожиданное значение " + field[y][x]);
                }

            }

        }
        if (isGameOver) showMessageGameOver(g);
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameOverType) {
            case STATE_DROW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
                break;

            case STATE_WIN_AI:
                g.drawString(MSG_WIN_AI, 20, getHeight() / 2);
                break;
            case STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("Неожданное состояние окончания игры" + gameOverType);
        }
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        this.mode = mode;
        this.fSzX = fSzX;
        this.fSzY = fSzY;
        this.wLen = wLen;
        isGameOver = false;
        isInitialised = true;

        initMap();
        repaint();
        System.out.printf("%d %d\n", getWidth(), getHeight());
        System.out.printf("Mode: %d; \nxSize: %d, \nySize: %d, \nwinLength: %d\n", mode, fSzX, fSzY, wLen);
    }
}
