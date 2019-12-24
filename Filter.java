package gui.imagefilter;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;

public abstract class Filter {
    public Image filterImage(Image image) {
        Color[][] pixelDataExtendedPixels = this.getPixelDataExtended(image);
        double[][] filter = this.createFilter();
        Color[][] filteredImagePixels = this.applyFilter(pixelDataExtendedPixels, filter);

        return this.getFilteredImage(filteredImagePixels);
    };

    public double[][] createFilter() {
        return null;
    };

    public Image getFilteredImage(Color[][] filteredImagePixels) {
        WritableImage writableImage = new WritableImage(filteredImagePixels.length, filteredImagePixels.length);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int i = 0; i < filteredImagePixels.length; i++) {
            for (int j = 0; j < filteredImagePixels.length; j++) {
                pixelWriter.setColor(i, j, filteredImagePixels[i][j]);
            }
        }

        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
        Image filteredImage = SwingFXUtils.toFXImage(bufferedImage, null );

        return filteredImage;
    }

    public Color[][] getPixelDataExtended(Image image) {
        int imageExtendedLength = (int) image.getHeight() + 2;
        final Color black = new Color(0.0, 0.0, 0.0, 1.0);

        Color[][] pixelDataExtended = new Color[imageExtendedLength][imageExtendedLength];
        Color[][] pixelData = this.getPixelData(image);

        for (int i = 0; i < imageExtendedLength; i++) {
            for (int j= 0; j < imageExtendedLength; j++) {
                if (i == 0) {
                    pixelDataExtended[i][j] = black;
                } else if (i == imageExtendedLength - 1) {
                    pixelDataExtended[i][j] = black;
                } else if (j == 0) {
                    pixelDataExtended[i][j] = black;
                } else if (j == imageExtendedLength - 1) {
                    pixelDataExtended[i][j] = black;
                } else {
                    pixelDataExtended[i][j] = pixelData[i-1][j-1];
                }
            }
        }

        return pixelDataExtended;
    }

    public Color[][] getPixelData(Image image) {
        int imageLength = (int) image.getHeight();

        Color[][] pixelData = new Color[imageLength][imageLength];

        PixelReader pixelReader = image.getPixelReader();

        for (int i = 0; i < imageLength; i++) {
            for (int j = 0; j < imageLength; j++) {
                pixelData[i][j] = pixelReader.getColor(i, j);
            }
        }

        return pixelData;
    }

    private Color[][] getNeighbours(Color[][] pixels, int y, int x) {
        Color[][] neighbours = new Color[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                neighbours[i][j] = pixels[x-1+i][y-1+j];
            }
        }

        return neighbours;
    }

    public Color[][] applyFilter(Color[][] pixels, double[][] filter) {
        Color[][] extendedFilteredImage = new Color[pixels.length][pixels.length];
        Color[][] filteredImage = new Color[pixels.length-2][pixels.length-2];

        for (int y = 1; y < extendedFilteredImage.length - 1; y++) {
            for (int x = 1; x < extendedFilteredImage.length - 1; x++) {
                Color[][] neighbours = this.getNeighbours(pixels, y, x);

                double red = 0.0;
                double green = 0.0;
                double blue = 0.0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        red += neighbours[i][j].getRed() * filter[i][j];
                        green += neighbours[i][j].getGreen() * filter[i][j];
                        blue += neighbours[i][j].getBlue() * filter[i][j];
                    }
                }

                if (red > 1.0) {
                    red = 1.0;
                } else if (red < 0.0) {
                    red = 0.0;
                }

                if (green > 1.0) {
                    green = 1.0;
                } else if (green < 0.0) {
                    green = 0.0;
                }

                if (blue > 1.0) {
                    blue = 1.0;
                } else if (blue < 0.0) {
                    blue = 0.0;
                }

                Color filteredPixel = new Color(red, green, blue, 1.0);

                extendedFilteredImage[x][y] = filteredPixel;
            }
        }

        for (int i = 0; i < filteredImage.length; i++) {
            for (int j = 0; j < filteredImage.length; j++) {
                filteredImage[i][j] = extendedFilteredImage[i + 1][j + 1];
            }
        }

        return filteredImage;
    }
}
