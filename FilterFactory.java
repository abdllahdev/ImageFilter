package gui.imagefilter;

public class FilterFactory {
    public static Filter createFilter(String filterName) {
        if (filterName.equalsIgnoreCase("edgeDetectorFilter")) {
            return new EdgeDetectorFilter();
        } else if (filterName.equalsIgnoreCase("sharpenFilter")) {
            return new SharpenFilter();
        } else if (filterName.equalsIgnoreCase("blurFilter")) {
            return new BlurFilter();
        } else if (filterName.equalsIgnoreCase("embossFilter")) {
            return new EmbossFilter();
        } else if (filterName.equalsIgnoreCase("grayscaleFilter")) {
            return new GrayscaleFilter();
        }

        return null;
    }
}
