import java.awt.*;
import javax.swing.ImageIcon;

public class Sprite {
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;
    private Image spriteImage;

    public Sprite(int xPosition, int yPosition, int width, int height, String imageName) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.spriteImage = new ImageIcon(imageName).getImage();
    }

    public int getX() {
        return xPosition;
    }
    public int getY() {
        return yPosition;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Image getSpriteImage() {
        return spriteImage;
    }

    public void updateX(int xPosition) {
        this.xPosition = xPosition;
    }
    public void updateY(int yPosition) {
        this.yPosition = yPosition;
    }
    public void updateSpriteImage(String imageName) {
        this.spriteImage = new ImageIcon(imageName).getImage();
    }
}

