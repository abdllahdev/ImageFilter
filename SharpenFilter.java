package gui.imagefilter;

public class SharpenFilter extends Filter {
    @Override
    public double[][] createFilter() {
        return new double[][]{{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
    }
}
