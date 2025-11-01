import java.awt.*;

public class Pipe {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image image;
    private int velocityX;
    private boolean passed = false;

    // Konstruktor
    public Pipe(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocityX = 0;
        this.passed = false;
    }

    // Getter & Setter untuk posX
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    // Getter & Setter untuk posY
    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    // Getter & Setter untuk width
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // Getter & Setter untuk height
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Getter & Setter untuk image
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    // Getter & Setter untuk velocityX (kalau nanti mau dipakai)
    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    // Getter & Setter
    public boolean isPassed() {

        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
