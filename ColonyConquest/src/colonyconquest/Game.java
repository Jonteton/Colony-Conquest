/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colonyconquest;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import java.lang.Thread;
import javax.imageio.ImageIO;


public class Game extends JFrame implements Runnable {

    
    //2.18.54
    private final int WIDTH = 1280, HEIGHT = 720;
    private final int maxFPS = 60;
    private final int BORDERWIDTH = 10;
    private final int PIXELCOLOR = 12234;
    
    static int alpha = 0xFFFF00DC;

    private Canvas canvas;

    private RenderHandler renderer;
    
    private SpriteSheet sheet;
    
    private Tiles tiles;
    
    private Map map;

    BufferedImage grassImage;
    
    Sprite testSprite;

    Thread gameThread;

    Rectangle testRectangle = new Rectangle(30, 30, 50, 100);

    public Game() {
        super("Diablo game");
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        canvas = new Canvas();

        this.add(canvas);

        canvas.createBufferStrategy(3);

        renderer = new RenderHandler(WIDTH, HEIGHT);
        
        BufferedImage sheetImage = loadImage("Tiles1.png");
        sheet = new SpriteSheet(sheetImage);
        sheet.loadSprites(16, 16);

        grassImage = loadImage("GrassTile.png");
//        testSprite = sheet.getSprite(1, 2);

        testRectangle.graphics(BORDERWIDTH, PIXELCOLOR);
        
        tiles = new Tiles(new File("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\DiabloTest\\src\\diablotest\\Tiles.txt"), sheet);
        
        map = new Map(new File("C:\\Users\\Jonas\\Documents\\NetBeansProjects\\DiabloTest\\src\\diablotest\\Map.txt"), tiles);
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nanoSecondConversion = 1000000000.0 / maxFPS; //60 frames per second
        double deltaSeconds = 0;

        while (true) {
            long now = System.nanoTime();
            deltaSeconds += (now - lastTime) / nanoSecondConversion;

            if (deltaSeconds >= 1) {
                deltaSeconds = 0;
                update();
            }
            lastTime = now;
            render();
        }
    }

    void update() {
    }

    void render() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();
        super.paint(g);

        final int GRASS_ZOOM_X = 4;
        final int GRASS_ZOOM_Y = 4;
        final int GRASS_xPos = 50;
        final int GRASS_yPos = 0;

        final int RECT_ZOOM_X = 1;
        final int RECT_ZOOM_Y = 1;
        
        
        final int SPRITE_ZOOM_X = 5;
        final int SPRITE_ZOOM_Y = 5;
        final int SPRITE_POSITION_X = 100;
        final int SPRITE_POSITION_Y = 100;
        

        //----------body for generating graphic, its linear so renders in order. Last render overrides the other
        //renderer.renderImage(grassImage, GRASS_xPos, GRASS_yPos, GRASS_ZOOM_X, GRASS_ZOOM_Y); //last 2 parameters are zoom
//        renderer.renderSprite(testSprite, SPRITE_POSITION_X, SPRITE_POSITION_Y, SPRITE_ZOOM_X, SPRITE_ZOOM_Y);
//        tiles.renderTile(5, renderer, 100, 100, 3, 3);
        map.render(renderer, 3, 3);
        renderer.renderRectangle(testRectangle, RECT_ZOOM_X, RECT_ZOOM_Y);
        renderer.render(g);

        //----------
        g.dispose();
        bufferStrategy.show();

    }

    //Takes a file from the path, formattedimage transform it to an image we can use for our program.
    private BufferedImage loadImage(String path) {
        final int X_ORIGIN = 0;
        final int Y_ORIGIN = 0;

        try {
            BufferedImage loadedImage = ImageIO.read(Game.class.getResource(path));
            BufferedImage formattedImage = new BufferedImage(loadedImage.getWidth(),
                    loadedImage.getHeight(), BufferedImage.TYPE_INT_RGB);

            formattedImage.getGraphics().drawImage(loadedImage, X_ORIGIN, Y_ORIGIN, null);

            return formattedImage;
        } catch (IOException e) {
            System.out.println("Couldn't locate the image");
            return null;
        }
    }
}
