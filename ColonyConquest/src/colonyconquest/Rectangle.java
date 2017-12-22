/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colonyconquest;

public class Rectangle {

    int x, y, width, height;
    private int[] pixels;

    
    public Rectangle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }
    
    //if no inputs create an empty rectangle
    public Rectangle() {
        this(0, 0, 0, 0);
    }
    
    //graphical display of the rectangle
    void graphics(int borderWidth, int color) {
        pixels = new int[width * height];
        
        for (int i = 0; i < pixels.length; i++)
        {
            pixels[i] = Game.alpha;
        }
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < borderWidth; y++) {
                pixels[x + y * width] = color;
            }
        }
        for (int x = 0; x < borderWidth; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x + y * width] = color;
            }
        }
        for (int x = width - 1; x > width - borderWidth; x--) {
            for (int y = 0; y < height; y++) {
                pixels[x + y * width] = color;
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = height - 1; y > height - borderWidth; y--) {
                pixels[x + y * width] = color;
            }
        }
    }

    //the pixels covered by the rectangle
    public int[] getPixels() {
        if (pixels != null) {
            return pixels;
        } else {
            System.out.println("failed to make pixels");
            return null;
        }
    }
}
