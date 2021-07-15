package core.ui.graphics;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.Pixmap;
import arc.graphics.g2d.PixmapRegion;

public class TextureEditor {

    public static Pixmap paint(String region, Color targetColor) {
        return paint(Core.atlas.getPixmap(region), targetColor);
    }

    public static Pixmap paint(PixmapRegion region, Color targetColor) {
        Pixmap result = new Pixmap(region.width, region.height);

        for(int x = 0; x < region.width; x++) {
            for(int y = 0; y < region.height; y++) {
                Color tempC = new Color(region.get(x,y));
                if(tempC.a != 0) {
                    tempC.a = (tempC.a + targetColor.a) / 2;
                    tempC.g = (tempC.g + targetColor.g) / 2;
                    tempC.b = (tempC.b + targetColor.b) / 2;
                    tempC.r = (tempC.r + targetColor.r) / 2;

                    result.setRaw(x, y, tempC.rgba());
                }
            }
        }
        return result;
    }

    public static Pixmap resize(Pixmap source, int targetWidth, int targetHeight) {
        Pixmap result = new Pixmap(targetWidth, targetHeight);
        for(int x = 0; x < targetWidth; x++) {
            for(int y = 0; y < targetHeight; y++) {
                Color tempC = new Color(source.get(x, y));

            }
        }
        return result;
    }
}
