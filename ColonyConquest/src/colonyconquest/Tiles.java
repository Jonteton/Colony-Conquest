
package colonyconquest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tiles {
    
    private SpriteSheet spriteSheet;
    private Scanner scanner;
    private ArrayList<Tile> tilesList = new ArrayList<Tile>();
    
    public Tiles(File tilesFile, SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
        
        try {
            scanner = new Scanner(tilesFile);
            while (scanner.hasNextLine()) {
                //Reads each line and create a Tile
                
                String line = scanner.nextLine();
                
                if (!line.startsWith("//")) {
                    String[] splitString = line.split("-");
                    String tileName = splitString[0];
                    int spriteX = Integer.parseInt(splitString[1]);
                    int spriteY = Integer.parseInt(splitString[2]);
                    Tile tile = new Tile(tileName, spriteSheet.getSprite(spriteX, spriteY));
                    tilesList.add(tile);
                }
            }
        } catch (Exception e) {
            System.out.println("Didnt manage to find file");
        }
    }
    
    public void renderTile(int tileID, RenderHandler renderer, 
            int xPosition, int yPosition, int xZoom, int yZoom) 
    {
        if(tilesList.size() > tileID && tileID >= 0)
        {
            renderer.renderSprite(tilesList.get(tileID).sprite, xPosition, yPosition, xZoom, yZoom);
        }
        else{
            System.out.println("TileID" + tileID + "doesnt exist, check textfile size!");
        }
    }
    
    class Tile {

        String tileName;
        Sprite sprite;

        public Tile(String tilename, Sprite sprite) {
            this.tileName = tilename;
            this.sprite = sprite;
        }
    }
}

