package ouch.study.fpe.view;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This object manages grids (boxes which are tiled on the screen).
 * 
 * @author Koji
 * 
 */
public class PaintScreenGridManager {

	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(PaintScreenGridManager.class);

	private double columnSize = 5;

	private double width;
	private double height;

	private List<Rectangle2D> boxes = new ArrayList<>();

	/**
	 * 
	 * @param width
	 *            width of the area for whole of grids
	 * @param height
	 *            height of the area for whole of grids
	 */
	public PaintScreenGridManager(final double width, final double height) {

		update(width, height);

	}

	/**
	 * @param c
	 *            ammount of columns
	 */
	public void changeColumnSize(final double c) {
		if (columnSize == c) {
			return;
		}
		columnSize = c;
		update();
	}

	public void update() {
		LOGGER.debug("panel width: " + width);

		boxes = createBoundBoxes();
	}

	/**
	 * 
	 * @param w
	 *            width of the area for whole of grids
	 * @param h
	 *            height of the area for whole of grids
	 */
	public void update(final double w, final double h) {
		width = w;
		height = h;
		LOGGER.debug("panel width: " + width);

		boxes = createBoundBoxes();
	}

	/**
	 * 
	 * @param index
	 *            index of box
	 * @return
	 *         a box at the given index. null if index is out of grids.
	 */
	public Rectangle2D getBoundBox(final int index) {

		if (index >= boxes.size()) {
			return null;
		}

		return boxes.get(index);
	}

	/**
	 * 
	 * @return the count of all boxes.
	 */
	public int getBoxCount() {
		return boxes.size();
	}

	/**
	 * 
	 * @return
	 *         boxes
	 */
	private List<Rectangle2D> createBoundBoxes() {
		List<Rectangle2D> boxes = new ArrayList<>();

		double boxSize = width / columnSize;
		int rowSize = (int) (height / boxSize);

		for (int y = 0; y < rowSize; y++) {
			for (int x = 0; x < columnSize; x++) {

				Rectangle2D box = new Rectangle2D.Double(boxSize * x, boxSize * y, boxSize, boxSize);
				boxes.add(box);
			}
		}
		return boxes;
	}

	public int getIndexAt(final double x, final double y) {
		int i = 0;
		for (Rectangle2D box : boxes) {
			// LOGGER.debug("point x:" + x + ",y:" + y + " " + box);

			if (box.contains(x, y)) {
				return i;
			}
			i++;
		}

		return -1;
	}

}
