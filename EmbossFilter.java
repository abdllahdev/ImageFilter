package gui.imagefilter;

public class EmbossFilter extends Filter {
    @Override
    public double[][] createFilter() {
        return new double[][]{{-2, -1, 0}, {-1, 1, 1}, {0, 1, 2}};
    }
}
