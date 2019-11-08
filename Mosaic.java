/*
    @author Emily O'Connell
    Project: Mosaic
*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

/*
    The MosaicTile class creates, randomizes, and draws the tiles and faces. It owns all the properties of the tiles. It also
    overrides the toString method and listens for a mouse click. After the mouse click, it changes the tile to a face and
    vice versa.
*/
class MosaicTile extends JPanel implements MouseListener {
    private int red, green, blue;
    private char letter;
    private boolean drawFace;
    private boolean isCircle;
    private Face myFace;
    
    public MosaicTile () {
        setRandomValue();
        addMouseListener(this);
        drawFace = false;
    }

    final public void setRandomValue() {
        red = getNumberBetween(0, 255);
        green = getNumberBetween(0, 255);
        blue = getNumberBetween(0, 255);

        Random random = new Random();
        letter = (char)(random.nextInt(26) + 'a');
        
        myFace = new Face(0, 0, 0, 0, getNumberBetween(0, 2), new Color(red, green, blue));

        if(getNumberBetween(0, 1) == 0){
            isCircle = false;
        }else{
            isCircle = true;
        }
    }

    private static int getContrastingColor(int colorIn) {
        return ((colorIn + 128) % 256);
    }

    private static final int getNumberBetween(int min, int max){
        Random myRandom = new Random();
        return min + myRandom.nextInt(max - min + 1);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if(drawFace){
            System.out.println(myFace);

            myFace.setWidth(panelWidth);
            myFace.setHeight(panelHeight);

            myFace.paintComponent(g);
        }else{
            System.out.println(this);

            g.setColor(new Color(red, green, blue));

            if(isCircle){
                g.fillOval(0, 0, panelWidth, panelHeight);
            }else{
                g.fillRect(0, 0, panelWidth, panelHeight);
            }

            g.setColor(new Color(getContrastingColor(red), getContrastingColor(green), getContrastingColor(blue)));

            final int fontSize = 40;
            g.setFont(new Font("ComicSans", Font.PLAIN, fontSize));
            int stringX = (panelWidth / 2) - 15;
            int stringY = (panelHeight / 2) + 15;
            g.drawString(("" + letter).toUpperCase(), stringX, stringY);
        }
    }

    public boolean getDrawFace() {
        return drawFace;
    }

    public void setDrawFace(boolean value) {
        drawFace = value;
    }

    @Override
    public String toString() {
        if(drawFace){
            return myFace.toString();
        }else{
            return "Tile: Cirlce: " + isCircle + " Letter: " + letter + " Color: red: " + red + " green: " + green + " blue: " + blue;
        }
    }

    @Override
    public void mousePressed(MouseEvent e){

    }

    @Override
    public void mouseReleased(MouseEvent e){

    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(drawFace) {
            drawFace = false;
        }else{
            drawFace = true;
        }
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e){
   
    }

    @Override
    public void mouseEntered(MouseEvent e){
      
    }
}
/*
    The MosaicFrame class has one constructor that creates a BorderLayout. The buttons are in the south of the layout while the grid
    of the tiles are on the center area. It also adds buttons with their action listener. Randomize randomizes the tiles and faces while
    invert changes all tiles to faces and vice versa. Set all to tile sets every panel to a tile while set all to face sets every panel
    to a face. The set grid size button allows you to set the size in the command prompt.
*/
class MosaicFrame extends JFrame {
    private ArrayList<MosaicTile> titleList;
    private int width;
    private int height;

    public MosaicFrame() {
        setBounds(200, 200, 800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        width = 12;
        height = 12;

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel mosaicPanel = new JPanel();

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton randomize = new JButton("Randomize!");
        buttonPanel.add(randomize);
        randomize.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Paint***");
                for(MosaicTile tile:titleList) {
                    tile.setRandomValue();
                    System.out.println(tile);
                }
                repaint();
            }
        });

        JButton invert = new JButton("Invert!");
        buttonPanel.add(invert);
        invert.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Paint***");
                for(MosaicTile tile:titleList) {
                    if(tile.getDrawFace()){
                        tile.setDrawFace(false);
                    }else{
                        tile.setDrawFace(true);
                    }
                    System.out.println(tile);
                }
                repaint();
            }
        });

        JButton setTile = new JButton("Set all to tile");
        buttonPanel.add(setTile);
        setTile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Paint***");
                for(MosaicTile tile:titleList) {
                    if(tile.getDrawFace()){
                        tile.setDrawFace(false);
                    }
                    System.out.println(tile);
                }
                repaint();
            }
        });

        JButton setFace = new JButton("Set all to face");
        buttonPanel.add(setFace);
        setFace.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Paint***");
                for(MosaicTile tile:titleList) {
                    if(!tile.getDrawFace()){
                        tile.setDrawFace(true);
                    }
                    System.out.println(tile);
                }
                repaint();
            }
        });
        
        JButton setGrid = new JButton("Set Grid Size");
        buttonPanel.add(setGrid);
        setGrid.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Paint***");
                resizeGrid();
                mosaicPanel.setLayout(new GridLayout(width, height));
                titleList.clear();
                mosaicPanel.removeAll();
                for(int i = 0; i <  width * height; i++) {
                    MosaicTile title = new MosaicTile();
                    titleList.add(title);
                    mosaicPanel.add(title);
                }
                revalidate();
                repaint();
            }
        });

        contentPane.add(mosaicPanel, BorderLayout.CENTER);
        mosaicPanel.setLayout(new GridLayout(width, height));

        titleList = new ArrayList<MosaicTile>();
        for(int i = 0; i <  width * height; i++) {
            MosaicTile title = new MosaicTile();
            titleList.add(title);
            mosaicPanel.add(title);
        }
    }

    private final void resizeGrid(){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter width: ");
        width = input.nextInt();

        System.out.println("Enter height: ");
        height = input.nextInt();
    }
}
/*
    This is the main class that creates the frame.
*/
public class Mosaic {
    public static void main(String[] args) {
        System.out.println("Start Paint***");
        MosaicFrame frame = new MosaicFrame();
        frame.setVisible(true);
    }
}