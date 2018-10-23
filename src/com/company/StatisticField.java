package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticField extends JPanel {

    public final static int STATISTIC_FIELD_H = 400;                                        // Высота статистического поля
    public final static int STATISTIC_FIELD_W = 200;                                        // Ширина статистического поля

    // Компоненты
    private static Label player1Name = new Label("Player 1", Label.CENTER);

    private static Label player2Name = new Label("Player 2", Label.CENTER);

    private TextField player1NameTextField = new TextField();
    private TextField player2NameTextField = new TextField();

    private static Label point1 = new Label("0", Label.CENTER);
    private static Label point2 = new Label("0", Label.CENTER);

    private static Button start = new Button("Start Game");

    public static Label getPoint1() {
        return point1;
    }

    public static Label getPoint2() {
        return point2;
    }

    public static Label getPlayer1Name() {
        return player1Name;
    }

    public static Label getPlayer2Name() {
        return player2Name;
    }

    public static Button getStart() {
        return start;
    }

    public StatisticField() {

        Font fontScore = new Font("TimesRoman", Font.BOLD, 100);
        Font fontButton = new Font("Times New Roman", Font.BOLD, 25);

        // Компоненты
        JPanel settingPanel = new JPanel();
        JPanel setting1player = new JPanel();
        JPanel setting2player = new JPanel();
        JPanel points = new JPanel();
        Label colon = new Label(":", Label.CENTER);
        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder());
        Label setting1Up = new Label("\"W\" - UP", Label.CENTER);
        setting1Up.setForeground(Color.WHITE);
        setting1Up.setBackground(Color.BLUE);
        Label setting2Up = new Label("\"UP\" - UP", Label.CENTER);
        setting2Up.setForeground(Color.WHITE);
        setting2Up.setBackground(Color.BLUE);
        Label setting1Down = new Label("\"S\" - DOWN", Label.CENTER);
        setting1Down.setForeground(Color.WHITE);
        setting1Down.setBackground(Color.BLUE);
        Label setting2Down = new Label("\"DOWN\" - DOWN", Label.CENTER);
        setting2Down.setForeground(Color.WHITE);
        setting2Down.setBackground(Color.BLUE);
        Button renamePlayer1 = new Button("Rename");
        renamePlayer1.setForeground(Color.WHITE);
        renamePlayer1.setBackground(Color.BLUE);
        Button renamePlayer2 = new Button("Rename");
        renamePlayer2.setForeground(Color.WHITE);
        renamePlayer2.setBackground(Color.BLUE);
        player1Name.setForeground(Color.WHITE);
        player1Name.setBackground(Color.BLUE);
        player2Name.setForeground(Color.WHITE);
        player2Name.setBackground(Color.BLUE);
        player1NameTextField.setForeground(Color.WHITE);
        player1NameTextField.setBackground(Color.BLUE);
        player2NameTextField.setForeground(Color.WHITE);
        player2NameTextField.setBackground(Color.BLUE);
        start.setFont(fontButton);
        start.setForeground(Color.WHITE);
        start.setBackground(Color.RED);
        colon.setBackground(Color.WHITE);
        point1.setBackground(Color.WHITE);
        point2.setBackground(Color.WHITE);
//        start.setBackground(Color.decode("#090F9F"));
//        colon.setBackground(Color.decode("#9497E5"));
//        point1.setBackground(Color.decode("#9497E5"));
//        point2.setBackground(Color.decode("#9497E5"));

        // Установка менеджеров компановки
        setLayout(new GridLayout(3, 1));
        setting1player.setLayout(new GridLayout(5, 1));
        setting2player.setLayout(new GridLayout(5, 1));
        settingPanel.setLayout(new GridLayout(1, 2));
        points.setLayout(new GridLayout(1, 3));

        // Установка рамок
        settingPanel.setBorder(border);
        points.setBorder(border);

        // Установка шрифта
        point1.setFont(fontScore);
        point2.setFont(fontScore);
        colon.setFont(fontScore);

        // Добавление на панель points
        points.add(point1);
        points.add(colon);
        points.add(point2);

        // Добавление на панель setting1player
        setting1player.add(player1Name);
        setting1player.add(setting1Up);
        setting1player.add(setting1Down);
        setting1player.add(player1NameTextField);
        setting1player.add(renamePlayer1);

        // Добавление на панель setting2player
        setting2player.add(player2Name);
        setting2player.add(setting2Up);
        setting2player.add(setting2Down);
        setting2player.add(player2NameTextField);
        setting2player.add(renamePlayer2);

        // Добавление на панель settingPanel
        settingPanel.add(setting1player);
        settingPanel.add(setting2player);

        // Добавление на статистическое поле
        add(points);
        add(settingPanel);
        add(start);

        // Обработчик кнопки start
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start.setLabel("Continue Game");
                GameField.setInGame(true);
                MainWindow.jFrame.requestFocus();
            }
        });

        // Обработчик кнопки renamePlayer1
        renamePlayer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!player1NameTextField.getText().equals("")) {
                    player1Name.setText(player1NameTextField.getText());
                    player1NameTextField.setText("");
                }
            }
        });

        // Обработчик кнопки renamePlayer2
        renamePlayer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!player2NameTextField.getText().equals("")) {
                    player2Name.setText(player2NameTextField.getText());
                    player2NameTextField.setText("");
                }
            }
        });
    }

}
