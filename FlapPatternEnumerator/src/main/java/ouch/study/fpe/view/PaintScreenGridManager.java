package ouch.study.fpe.view;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PaintScreenGridManager {

	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(PaintScreenGridManager.class);

	private double columnSize = 5;

	private final JPanel panel;

	private List<Rectangle2D> boxes = new ArrayList<>();

	/**
	 * 
	 * @param p
	 *            panel
	 */
	public PaintScreenGridManager(JPanel p) {
		panel = p;

		update();
	}

	/**
	 * 
	 */
	public void changeColumnSize(double c) {
		if (columnSize == c) {
			return;
		}
		columnSize = c;
		update();
	}

	public void update() {
		LOGGER.debug("panel width: " + panel.getWidth());
		boxes = createBoundBoxes();
	}

	/**
	 * 
	 * @param index
	 * @return
	 *         a box at the given index. null if index is out of grids.
	 */
	public Rectangle2D getBoundBox(int index) {

		if (index >= boxes.size()) {
			return null;
		}

		return boxes.get(index);
	}

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

		double boxSize = panel.getWidth() / columnSize;
		int rowSize = (int) (panel.getHeight() / boxSize);

		for (int y = 0; y < rowSize; y++) {
			for (int x = 0; x < columnSize; x++) {

				Rectangle2D box = new Rectangle2D.Double(boxSize * x, boxSize * y, boxSize, boxSize);
				boxes.add(box);
			}
		}
		return boxes;
	}

}
