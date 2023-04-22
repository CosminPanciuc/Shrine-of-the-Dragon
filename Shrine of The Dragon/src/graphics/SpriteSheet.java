package graphics;

import main.MainPanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage spriteSheet;
    MainPanel mp;

    public SpriteSheet(MainPanel mp, BufferedImage buffImg) {
        this.mp = mp;
        spriteSheet = buffImg;
    }

    public BufferedImage crop(int x, int y) {
        return spriteSheet.getSubimage(x * mp.tileSize, y * mp.tileSize, mp.tileSize, mp.tileSize);
    }


    public BufferedImage crop(int x, int y, int size) {
        return spriteSheet.getSubimage(x * size, y * size, size, size);
    }
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
