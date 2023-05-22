package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    MainPanel mp;
    public int mouseX;
    public int mouseY;
    public boolean middleButtonPressed = false;
    public boolean rightButtonPressed = false;
    public boolean leftButtonPressed = false;

    public MouseInput(MainPanel mp){
        this.mp = mp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    public boolean isMouseClicked(int c){
        if(c == 1){
            var val = leftButtonPressed;
            leftButtonPressed = false;
            return val;
        }
        if(c == 2){
            var val = middleButtonPressed;
            middleButtonPressed = false;
            return val;
        }
        if(c == 3){
            var val = rightButtonPressed;
            rightButtonPressed = false;
            return val;
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        switch (e.getButton()){
            case MouseEvent.BUTTON1 -> {
                leftButtonPressed = true;
            }
            case MouseEvent.BUTTON2 -> {
                middleButtonPressed = true;
            }
            case MouseEvent.BUTTON3 -> {
                rightButtonPressed = true;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()){
            case MouseEvent.BUTTON1 -> {
                leftButtonPressed = false;
            }
            case MouseEvent.BUTTON2 -> {
                middleButtonPressed = false;
            }
            case MouseEvent.BUTTON3 -> {
                rightButtonPressed = false;
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
