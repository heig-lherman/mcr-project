package heig.mcr.visitor.math;

/**
 * A region of interest is a rectangle in an image.
 * The region is defined by its top left corner (x, y) and its width and height (w, h).
 *
 * @param x top left pixel x coordinate in the image coordinate system
 * @param y top left pixel y coordinate in the image coordinate system
 * @param w width in number of pixels
 * @param h height in number of pixels
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public record RegionOfInterest(int x, int y, int w, int h) {

    /**
     * @return top left pixel x coordinate
     */
    public int x1() {
        return x;
    }

    /**
     * @return bottom right pixel x coordinate
     */
    public int x2() {
        return x + w - 1;
    }

    /**
     * @return top left pixel y coordinate
     */
    public int y1() {
        return y;
    }

    /**
     * @return bottom right y coordinate
     */
    public int y2() {
        return y + h - 1;
    }
}
