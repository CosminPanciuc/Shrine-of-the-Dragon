package main;

import java.awt.*;

public class Menu {
    MainPanel mp;

    public Rectangle newButton;
    public Rectangle loadButton;
    public Rectangle quitButton;
    public Menu(MainPanel mp){
        this.mp = mp;
        newButton = new Rectangle(mp.screenWidth/2 - 100, mp.screenHeight/2 - 150, 200, 50);
        loadButton = new Rectangle(mp.screenWidth/2 - 100, mp.screenHeight/2 - 50, 200, 50);
        quitButton = new Rectangle(mp.screenWidth/2 - 100, mp.screenHeight/2 + 50, 200, 50);
    }
    public void draw(Graphics2D g2){
        Font fnt = new Font("arial", Font.BOLD, 50);
        g2.setFont(fnt);
        g2.setColor(Color.RED);
        g2.drawString("SHRINE OF THE DRAGON", mp.screenWidth/2 - 300, 100);

        g2.drawString("NEW", newButton.x + 40, newButton.y + 40);
        g2.draw(newButton);
        g2.drawString("LOAD", loadButton.x + 40, loadButton.y + 40);
        g2.draw(loadButton);
        g2.drawString("QUIT", quitButton.x + 40, quitButton.y  + 40);
        g2.draw(quitButton);
    }
    public void update(){
        // UNEORI MERGE FARA PRINT DACA NU MERGE DECOMENTEAZA
        //System.out.println("");
        if(mp.mouseInput.isMouseClicked(1)){
            if(mp.mouseInput.mouseX >= mp.screenWidth/2 - 100 && mp.mouseInput.mouseX <= mp.screenWidth/2 + 100){
                if(mp.mouseInput.mouseY >= mp.screenHeight/2 - 150 && mp.mouseInput.mouseY <= mp.screenHeight/2 - 100){
                    mp.defaultDatabase.LoadFromDatabase();
                    mp.currentState = MainPanel.GameState.PLAY;
                }
            }
            if(mp.mouseInput.mouseX >= mp.screenWidth/2 - 100 && mp.mouseInput.mouseX <= mp.screenWidth/2 + 100){
                if(mp.mouseInput.mouseY >= mp.screenHeight/2 - 50 && mp.mouseInput.mouseY <= mp.screenHeight/2){
                    mp.savesDatabase.LoadFromDatabase();
                    mp.currentState = MainPanel.GameState.PLAY;
                }
            }
            if(mp.mouseInput.mouseX >= mp.screenWidth/2 - 100 && mp.mouseInput.mouseX <= mp.screenWidth/2 + 100){
                if(mp.mouseInput.mouseY >= mp.screenHeight/2 + 50 && mp.mouseInput.mouseY <= mp.screenHeight/2 + 100){
                    System.exit(1);
                }
            }
        }
    }

    public void InGameMenuUpdate(){
        // UNEORI MERGE FARA PRINT DACA NU MERGE DECOMENTEAZA
        //System.out.println("");
            if(mp.mouseInput.isMouseClicked(1)){
                if(mp.mouseInput.mouseX >= mp.screenWidth/2 - 100 && mp.mouseInput.mouseX <= mp.screenWidth/2 + 100){
                    if(mp.mouseInput.mouseY >= mp.screenHeight/2 - 150 && mp.mouseInput.mouseY <= mp.screenHeight/2 - 100){
                        mp.currentState = MainPanel.GameState.MENU;
                    }
                }
                if(mp.mouseInput.mouseX >= mp.screenWidth/2 - 100 && mp.mouseInput.mouseX <= mp.screenWidth/2 + 100){
                    if(mp.mouseInput.mouseY >= mp.screenHeight/2 - 50 && mp.mouseInput.mouseY <= mp.screenHeight/2){
                        mp.savesDatabase.SaveToDatabase();
                        mp.currentState = MainPanel.GameState.PLAY;
                    }
                }
                if(mp.mouseInput.mouseX >= mp.screenWidth/2 - 100 && mp.mouseInput.mouseX <= mp.screenWidth/2 + 100){
                    if(mp.mouseInput.mouseY >= mp.screenHeight/2 + 50 && mp.mouseInput.mouseY <= mp.screenHeight/2 + 100){
                        System.exit(1);
                    }
                }
            }
    }
    public void drawInGameMenu(Graphics2D g2){
        Font fnt = new Font("arial", Font.BOLD, 50);
        g2.setFont(fnt);
        g2.setColor(Color.RED);

        g2.drawString("MENU", newButton.x + 40, newButton.y + 40);
        g2.draw(newButton);
        g2.drawString("SAVE", loadButton.x + 40, loadButton.y + 40);
        g2.draw(loadButton);
        g2.drawString("QUIT", quitButton.x + 40, quitButton.y  + 40);
        g2.draw(quitButton);
    }
}
