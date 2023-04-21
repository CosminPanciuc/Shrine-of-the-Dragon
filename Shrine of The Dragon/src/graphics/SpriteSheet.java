package graphics;

import main.MainPanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage spriteSheet;
    MainPanel mp;
    public SpriteSheet(MainPanel mp, BufferedImage buffImg){
        this.mp = mp;
        spriteSheet = buffImg;
    }

    public BufferedImage crop(int x, int y){
        return spriteSheet.getSubimage(x * mp.tileSize, y * mp.tileSize,mp.tileSize,mp.tileSize);
    }

}
