package gui.imagefilter;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GrayscaleFilter extends Filter {
    @Override
    public Image filterImage(Image image) {
        Color[][] filteredImagePixels = GrayscaleFilter.applyGreyscale(this.getPixelData(image));
        return this.getFilteredImage(filteredImagePixels);
    }

    public static Color[][] applyGreyscale(Color[][] pixels) {
        Color[][] greyScalePixels = new Color[pixels.length][pixels.length];

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels.length; j++) {
                double blue = pixels[i][j].getBlue();
                double red = pixels[i][j].getRed();
                double green = pixels[i][j].getGreen();
                double greyScalePixel = (blue + red + green) / 3;

                greyScalePixels[i][j] = new Color(greyScalePixel, greyScalePixel, greyScalePixel, 1.0);
            }
        }

        return greyScalePixels;
    }
}
