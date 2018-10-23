package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class GameField extends JPanel implements ActionListener {

    public static final int WIN_POINTS = 7;                     // Очков для победы
    public static int GAME_FIELD_H = 400;                     // Высота игрового поля
    public final static int GAME_FIELD_W = 700;            // Ширина игрового поля
    private final static int PUCK_SIZE = 20;                     // Размер шайбы
    private final static int PLAYER_HEIGHT = 100;           // Высота игроков
    private final static int PLAYER_WIDTH = 15;             // Ширина игроков
    private int puckX;                                                          // Х-координата шайбы
    private int puckY;                                                          // У-координата шайбы
    private static int player1X;                                                     // Х-координата 1-го игрока
    private static int player1Y;                                                     // У-координата 1-го игрока
    private static int player2X;                                                    // Х-координата 2-го игрока
    private static int player2Y;                                                    // У-координата 2-го игрока
    private static int player1Points;                                     // Очки 1-го игрока
    private static int player2Points;                                    // Очки 2-го игрока
    private boolean puckUp;                                               // Направление движения шайбы
    private boolean puckDown;
    private boolean puckLeft;
    private boolean puckRight;
    private static boolean inGame = false;

    public static int getPlayerHeight() {
        return PLAYER_HEIGHT;
    }

    public static int getPlayer1Y() {
        return player1Y;
    }

    public static void setPlayer1Y(int player1Y) {
        GameField.player1Y = player1Y;
    }

    public static int getPlayer2Y() {
        return player2Y;
    }

    public static void setPlayer2Y(int player2Y) {
        GameField.player2Y = player2Y;
    }

    public static boolean isInGame() {
        return inGame;
    }

    public static void setInGame(boolean inGame) {
        GameField.inGame = inGame;
    }

    // Отрисовка шайбы и игроков
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D player1 = new Rectangle2D.Double(player1X, player1Y, PLAYER_WIDTH, PLAYER_HEIGHT);
        Rectangle2D player2 = new Rectangle2D.Double(player2X, player2Y, PLAYER_WIDTH, PLAYER_HEIGHT);
        Ellipse2D puck = new Ellipse2D.Double(puckX, puckY, PUCK_SIZE, PUCK_SIZE);
        g2.setColor(Color.RED);
        g2.fill(puck);
        g2.setColor(Color.BLACK);
        g2.fill(player1);
        g2.fill(player2);
    }

    // Движение шайбы
    public void movePuck() {
        if (puckUp)
            puckY -= 1;
        if (puckDown)
            puckY += 1;
        if (puckLeft)
            puckX -= 1;
        if (puckRight)
            puckX += 1;
    }

    // Выбор начального направления движения шайбы
    public void choiceOfDirection() {
        int route = (int) (Math.random() * 3);
        if (route == 0) {
            puckRight = true;
            puckUp = true;
            puckLeft = false;
            puckDown = false;
        }
        if (route == 1) {
            puckRight = false;
            puckUp = true;
            puckLeft = true;
            puckDown = false;
        }

        if (route == 2) {
            puckRight = false;
            puckUp = false;
            puckLeft = true;
            puckDown = true;
        }

        if (route == 3) {
            puckRight = true;
            puckUp = false;
            puckLeft = false;
            puckDown = true;
        }
    }

    // Инициализация игры
    public void initGame() {
        player1Points = 0;
        player2Points = 0;

        choiceOfDirection();

        // Начальные координаты шайбы и игроков
        puckX = GAME_FIELD_W / 2 - PUCK_SIZE / 2;
        puckY = GAME_FIELD_H / 2 - PUCK_SIZE / 2;
        player1X = 0;
        player1Y = GAME_FIELD_H / 2 - PLAYER_HEIGHT / 2;
        player2X = GAME_FIELD_W - PLAYER_WIDTH;
        player2Y = GAME_FIELD_H / 2 - PLAYER_HEIGHT / 2;

        Timer timerPuck = new Timer(5, this);
        timerPuck.start();
    }

    public GameField() {
        // Установка рамки
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        // Инициализация игры в конструкторе
        initGame();
        setFocusable(true);
        setBackground(Color.decode("#99F90B"));
    }

    public void playerWin(Label player) {
        JOptionPane.showMessageDialog(
                this,
                player.getText() + " WIN!!!",
                "Congratulations",
                JOptionPane.PLAIN_MESSAGE);
        StatisticField.getStart().setLabel("Start Game");
        initGame();
        StatisticField.getPoint1().setText("0");
        StatisticField.getPoint2().setText("0");
    }

    // Проверка на столкновение шайбы с бортами и на гол
    public void checkBorder() {
        if (puckY == 0) {
            puckDown = true;
            puckUp = false;
        }

        if (puckY == GAME_FIELD_H - PUCK_SIZE) {
            puckDown = false;
            puckUp = true;
        }


        if (puckX == PLAYER_WIDTH &&
                puckY + PUCK_SIZE / 2 >= player1Y &&
                puckY + PUCK_SIZE / 2 <= player1Y + PLAYER_HEIGHT) {
            puckRight = true;
            puckLeft = false;
        }

        if (puckX == -PUCK_SIZE) {
            player2Points++;
            inGame = false;
            choiceOfDirection();
            puckX = GAME_FIELD_W / 2 - PUCK_SIZE / 2;
            puckY = GAME_FIELD_H / 2 - PUCK_SIZE / 2;
            StatisticField.getPoint2().setText("" + player2Points);
            if (player2Points == WIN_POINTS) {
                playerWin(StatisticField.getPlayer2Name());
            }
        }

        if (puckX == GAME_FIELD_W - PUCK_SIZE - PLAYER_WIDTH &&
                puckY + PUCK_SIZE / 2 >= player2Y &&
                puckY + PUCK_SIZE / 2 <= player2Y + PLAYER_HEIGHT) {
            puckLeft = true;
            puckRight = false;
        }

        if (puckX == GAME_FIELD_W + PUCK_SIZE) {
            player1Points++;
            inGame = false;
            choiceOfDirection();
            puckX = GAME_FIELD_W / 2 - PUCK_SIZE / 2;
            puckY = GAME_FIELD_H / 2 - PUCK_SIZE / 2;
            StatisticField.getPoint1().setText("" + player1Points);
            if (player1Points == WIN_POINTS) {
                playerWin(StatisticField.getPlayer1Name());
            }
        }
    }

    // Событие таймера
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            movePuck();
            checkBorder();
            repaint();
        }
    }

}




