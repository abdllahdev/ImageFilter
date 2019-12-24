package gui.imagefilter;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class EdgeDetectorFilter extends Filter {
    @Override
    public Image filterImage(Image image) {
        Color[][] pixelDataExtendedPixels = this.getPixelDataExtended(image);
        Color[][] greyScaleImagePixels = GrayscaleFilter.applyGreyscale(pixelDataExtendedPixels);
        double[][] filter = this.createFilter();
        Color[][] filteredImagePixels = this.applyFilter(greyScaleImagePixels, filter);

        return this.getFilteredImage(filteredImagePixels);
    }

    @Override
    public double[][] createFilter() {
        return new double[][]{{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
    }
}
