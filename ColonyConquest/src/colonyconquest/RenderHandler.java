/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colonyconquest;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RenderHandler {

    private final BufferedImage view;
    private final Rectangle camera;
    private final int[] pixels;

    public RenderHandler(int width, int height) {
        view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();
        camera = new Rectangle(0, 0, width, height);
    }

    //Renders the background, drawImage creates the background
    void render(Graphics g) {
        final int BACKGROUND_X0 = 0;
        final int BACKGROUND_Y0 = 0;
        
        g.drawImage(view, BACKGROUND_X0, BACKGROUND_Y0, view.getWidth(), view.getHeight(), null);
    }

    //renders an image to the screen and to our pixels
    void renderImage(BufferedImage image, int xPos, int yPos, int xZoom, int yZoom) {
        int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        
        renderArray(imagePixels, image.getWidth(), image.getHeight(), xPos, yPos, xZoom, yZoom);
    }
    
    
    //Renders a sprite to the 
    void renderSprite(Sprite sprite, int xPosition, int yPosition, int xZoom, int yZoom)
    {
        renderArray(sprite.getPixels(), sprite.getWidth(), sprite.getHeight(), xPosition, yPosition, xZoom, yZoom);
    }

    //Renders an array to the screen, the area is a set of pixels which describes some type of graphic
    public void renderArray(int[] renderPixels, int width, int height, int xPos,
            int yPos, int xZoom, int yZoom) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int xZoomPosition = 0; xZoomPosition < xZoom; xZoomPosition++) {
                    for (int yZoomPosition = 0; yZoomPosition < yZoom; yZoomPosition++) {
                        setPixel(renderPixels[x + y * width], (x * xZoom) + xPos + xZoomPosition, ((y * yZoom) + yPos + yZoomPosition));
                    }
                }
            }
        }
    }

    //Checks if the pixel we're editing is in fact part of the canvas
    
    private void setPixel(int pixel, int x, int y) {
        if (x >= camera.x && y >= camera.y
                && x < camera.x + camera.width && y < camera.y + camera.height) {
            int pixelIndex = (x - camera.x) + (y - camera.y) * view.getWidth();
            if (pixelIndex < pixels.length && pixel != Game.alpha) {
                pixels[pixelIndex] = pixel;
            }
        }
    }

    //Creates a rectangle if its been initialized. 
    public void renderRectangle(Rectangle rectangle, int xZoom, int yZoom) {
        int[] rectanglePixels = rectangle.getPixels();
        
        if (rectanglePixels != null) {
            renderArray(rectanglePixels, rectangle.width, rectangle.height, rectangle.x, rectangle.y, xZoom, yZoom);
        }
    }
    Rectangle getCamera()
    {
        return camera;
    }
}
