
package colonyconquest;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Map {
    
    private Tiles tileSet;
    private Scanner scanner;
    private int fillTileID = -1;
    
    private ArrayList<MappedTile> mappedTiles = new ArrayList<MappedTile>();
    
    Map(File mapFile, Tiles tileSet)
    {
        this.tileSet = tileSet;  
        try {
            scanner = new Scanner(mapFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!line.startsWith("//")) {
                    if (line.contains(",")) {
                        String[] splitString = line.split(",");
                        if (splitString[0].equalsIgnoreCase("Fill")) {
                            fillTileID = Integer.parseInt(splitString[1]);
                            continue;
                        }
                    }
                    String[] splitString = line.split(",");
                    if (splitString.length >= 3) {
                        MappedTile mappedTile = new MappedTile(
                                Integer.parseInt(splitString[0]),
                                Integer.parseInt(splitString[1]),
                                Integer.parseInt(splitString[2]));
                        mappedTiles.add(mappedTile);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Didnt manage to find file");
        }
    }

    void render(RenderHandler renderer, int xZoom, int yZoom) {
        int xIncrement = 16 * xZoom;
        int yIncrement = 16 * yZoom;

//        if (fillTileID >= 0) {
//            Rectangle camera = renderer.getCamera();
//            for(int y = 0; y < camera.height; y++){
//                for (int x = 0; x < camera.width; x += mappedTile.x * xIncrement )
//            }
//                
//        }

        for (int i = 0; i < mappedTiles.size(); i++) {
            MappedTile mappedTile = mappedTiles.get(i);
            tileSet.renderTile(mappedTile.id, renderer, mappedTile.x * xIncrement, mappedTile.y * yIncrement, xZoom, yZoom);
        }
    }
    class MappedTile
    {
        int id, x, y;
        
        public MappedTile(int id, int x, int y)
        {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
    
}
