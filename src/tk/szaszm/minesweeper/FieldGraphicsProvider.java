package tk.szaszm.minesweeper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class FieldGraphicsProvider {
    private Map<String, BufferedImage> imageMap;
    private final static String[] types = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "bomb", "flag", "unrevealed" };

    public FieldGraphicsProvider() throws IOException {
        imageMap = new TreeMap<>();
        for(String type: types) {
            BufferedImage image = ImageIO.read(new File("res/"+type+".png"));
            imageMap.put(type, image);
        }
    }

    public BufferedImage get(String type) {
        return imageMap.get(type);
    }
}
