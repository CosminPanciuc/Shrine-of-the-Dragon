package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        /**
         * @param window fereastra jocului
         */
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Shrine of The Dragon");

        MainPanel panel = MainPanel.getInstance();
        window.add(panel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        //window.setSize(1360,720);

        panel.startGameThread();
    }
}
