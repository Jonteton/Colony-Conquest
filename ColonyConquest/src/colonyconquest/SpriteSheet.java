/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colonyconquest;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    
    private int pixels[];
    private BufferedImage image;
    private final int SIZE_X, SIZE_Y;
    private Sprite[] loadedSprites = null;
    private boolean spritesLoaded = false;
    
    private int spriteSizeX, spriteSizeY;
    
    
    public SpriteSheet(BufferedImage sheetImage)
    {
        image = sheetImage;
        SIZE_X = sheetImage.getWidth();
        SIZE_Y = sheetImage.getHeight();
        
        int x = 0;
        int y = 0;
        
        pixels = new int[SIZE_X * SIZE_Y];
        pixels = image.getRGB(x, y, SIZE_X, SIZE_Y, pixels, 0, SIZE_X);
    }
   
    void loadSprites(int spriteSizeX, int spriteSizeY) {
        this.spriteSizeX = spriteSizeX;
        
        loadedSprites = new Sprite[(SIZE_X / spriteSizeX) * (SIZE_Y / spriteSizeY)];
        int spriteID = 0;
        for (int y = 0; y < SIZE_Y; y += spriteSizeY) {
            for (int x = 0; x < SIZE_X; x += spriteSizeX) {
                loadedSprites[spriteID] = new Sprite(this, x, y, spriteSizeX, spriteSizeY);
                spriteID++;
            }
        }
        spritesLoaded = true;
    }

    Sprite getSprite(int x, int y) {
        if (spritesLoaded) {
            int spriteID = x + y * (SIZE_X / spriteSizeX);
            
            if(spriteID < loadedSprites.length)
            {
                return loadedSprites[spriteID];
            }
            else
            {
                System.out.println("Index out of range, cant access sprite");
            }
        } else {
            System.out.println("Couldn't fetch sprite due to not being loaded");
            
        }
        return null;    
    }

    int[] getPixels() {
        return pixels;
    }

    BufferedImage getImage() {
        return image;
    }
}
