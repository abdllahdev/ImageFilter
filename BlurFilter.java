package gui.imagefilter;

public class BlurFilter extends Filter {
    @Override
    public double[][] createFilter() {
        return new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}};
    }
}
