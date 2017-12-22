/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colonyconquest;

import java.awt.image.BufferedImage;

public class Sprite {
    private final int WIDTH, HEIGHT;
    private int[] pixels;
    
    public Sprite(SpriteSheet sheet, int startX, int startY, int width, int height)
    {
        WIDTH = width;
        HEIGHT = height;
        
        pixels = new int[WIDTH*HEIGHT];
        sheet.getImage().getRGB(startX, startY, WIDTH, HEIGHT, pixels, 0, WIDTH);
    }
    
    Sprite(BufferedImage image)
    {
        WIDTH = image.getWidth();
        HEIGHT = image.getHeight();
        
        pixels = new int [WIDTH*HEIGHT];
        image.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
                
    }

    int getWidth() {
        return WIDTH;
    }

    int getHeight() {
        return HEIGHT;
    }

    int[] getPixels() {
        return pixels;
    }
}
