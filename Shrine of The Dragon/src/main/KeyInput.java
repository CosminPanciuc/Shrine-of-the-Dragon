package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    public boolean wPressed, sPressed, aPressed, dPressed;
    public short numberPressed = 10;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){wPressed = true;}
        if(code == KeyEvent.VK_A){aPressed = true;}
        if(code == KeyEvent.VK_S){sPressed = true;}
        if(code == KeyEvent.VK_D){dPressed = true;}
        switch (code){
            case KeyEvent.VK_1 -> numberPressed = 1;
            case KeyEvent.VK_2 -> numberPressed = 2;
            case KeyEvent.VK_3 -> numberPressed = 3;
            case KeyEvent.VK_4 -> numberPressed = 4;
            case KeyEvent.VK_5 -> numberPressed = 5;
            case KeyEvent.VK_6 -> numberPressed = 6;
            case KeyEvent.VK_7 -> numberPressed = 7;
            case KeyEvent.VK_8 -> numberPressed = 8;
            case KeyEvent.VK_9 -> numberPressed = 9;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            wPressed = false;
        }
        if(code == KeyEvent.VK_A){
            aPressed = false;
        }
        if(code == KeyEvent.VK_S){
            sPressed = false;
        }
        if(code == KeyEvent.VK_D){
            dPressed = false;
        }
    }
}
