import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

class Face extends JPanel{
    private int width;
    private int height;
    private int x;
    private int y;
    private int expressionType;
    private Color color;

    Face() {
        width = 10;
        height = 10;
        x = 0;
        y = 0;
        expressionType = 0;
        color = Color.BLACK;
    }
    Face(int width, int height, int x, int y, int expressionType, Color color) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.expressionType = expressionType;
        this.color = color;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int  getXCord() {
        return x;
    }
    public int  getYCord() {
        return y;
    }
    public int getExpressionType() {
        return expressionType;
    }
    private Color getColor() {
        return color;
    }
    public void setWidth(int newValue) {
        width = newValue;
    }
    public void setHeight(int newValue) {
        height = newValue;
    }
   public void setX(int newValue) {
        x = newValue;
    }
    public void setY(int newValue) {
        y = newValue;
    }
    public void setExpressionType(int newValue) {
        expressionType = newValue;
    }
    public void setColor(Color newColor) {
        color = newColor;
    }

    @Override
    public String toString(){
        return "Face: X: " + x + " Y: " + y + " width: " + width + " height: " + height + " expression: " + expressionType;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(getColor());

        g.drawOval(getXCord(),   getYCord(), getWidth(), getHeight());
        g.drawOval(getXCord() + (getWidth() / 4),  getYCord() + (getHeight() / 4), getWidth() / 5, getHeight() / 5);
        g.drawOval(getXCord() + (getWidth() / 2),  getYCord() + (getHeight() / 4), getWidth() / 5, getHeight() / 5);

        if( getExpressionType() == 0){
            g.drawArc(getXCord() + (getWidth() / 4),  getYCord() + (getHeight() / 2), getWidth(), 50, 90, 60);
        }else if( getExpressionType() == 1){
            g.drawArc(getXCord() + (getWidth() / 50),  getYCord() + (getHeight() / 2), getWidth(), getHeight() - 10, 45, 90);
        }else{
            g.drawArc(getXCord() + (getWidth() / 50),  getYCord(), getWidth(), getHeight() - 10, -45, -90);
        }
    }
}
