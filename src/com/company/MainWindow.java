package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import static java.awt.event.KeyEvent.*;


public class MainWindow {

    public static  JFrame jFrame = new JFrame();
    private final Set<Integer> pressed = new HashSet<>();

    public MainWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Инициализация фрейма
        jFrame.setLocation(screenSize.width / 2 - (StatisticField.STATISTIC_FIELD_W + GameField.GAME_FIELD_W) / 2,
                screenSize.height / 2 - StatisticField.STATISTIC_FIELD_H / 2);
        jFrame.setTitle("Air Hockey");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setLayout(new FlowLayout());
        jFrame.setResizable(false);

        // Добавление движения игроков
        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                pressed.add(e.getKeyCode());
                if(pressed.contains(VK_UP)){
                    if(GameField.getPlayer2Y() != 0) {
                        GameField.setPlayer2Y(GameField.getPlayer2Y() - 5);
                        jFrame.repaint(5);
                    }
                }

                if(pressed.contains(VK_DOWN)){
                    if(GameField.getPlayer2Y() + GameField.getPlayerHeight() != GameField.GAME_FIELD_H) {
                        GameField.setPlayer2Y(GameField.getPlayer2Y() + 5);
                        jFrame.repaint(5);
                    }
                }

                if(pressed.contains(VK_W)){
                    if(GameField.getPlayer1Y() != 0) {
                        GameField.setPlayer1Y(GameField.getPlayer1Y() - 5);
                        jFrame.repaint(5);
                    }
                }

                if(pressed.contains(VK_S)){
                    if(GameField.getPlayer1Y() + GameField.getPlayerHeight() != GameField.GAME_FIELD_H) {
                        GameField.setPlayer1Y(GameField.getPlayer1Y() + 5);
                        jFrame.repaint(5);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                pressed.remove(e.getKeyCode());
            }
        });

        // Создание игрового и статистического поля
        StatisticField statisticField = new StatisticField();
        GameField gameField = new GameField();
        statisticField.setPreferredSize(new Dimension(StatisticField.STATISTIC_FIELD_W, StatisticField.STATISTIC_FIELD_H));
        gameField.setPreferredSize(new Dimension(GameField.GAME_FIELD_W, GameField.GAME_FIELD_H));

        jFrame.add(gameField);
        jFrame.add(statisticField);
        jFrame.pack();

    }


    public static void main(String[] args) {
        new MainWindow();
    }
}
