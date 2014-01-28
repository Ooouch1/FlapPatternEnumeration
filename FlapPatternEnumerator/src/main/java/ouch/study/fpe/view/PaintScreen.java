package ouch.study.fpe.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ouch.study.fpe.domain.AngleUnitFlapPattern;

/**
 * This ojbect implements how to show obtained patterns.
 * 
 * @author Koji
 * 
 */
public class PaintScreen extends JPanel {

	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(PaintScreen.class);

	private List<AngleUnitFlapPattern> patterns = new ArrayList<>();

	/**
	 * starts from 0.
	 */
	private int page = 0;

	private final PaintScreenGridManager gridManager = new PaintScreenGridManager(this.getWidth(), this.getHeight());

	/**
	 * Initialized background by white.
	 */
	public PaintScreen() {

		addComponentListener(new OnResized());

		this.setBackground(Color.white);
	}

	/**
	 * Receives patterns.
	 * 
	 * @param patterns2
	 */
	public void setPatterns(
			final Collection<AngleUnitFlapPattern> patternsToShow) {
		patterns = new ArrayList<>(patternsToShow);

	}

	/**
	 * Changes the order of shown patterns.
	 * 
	 * @param cmp
	 *            criterion of sorting.
	 */
	public void reorderPatterns(final Comparator<AngleUnitFlapPattern> cmp) {
		if (patterns == null) {
			return;
		}
		Collections.sort(patterns, cmp);
	}

	/**
	 * Suggests drawing patterns.
	 * 
	 * @param page
	 *            indicates which sub-sequence of patterns to be drawn..
	 */
	public void requestDraw(final int page) {
		this.page = page;

		LOGGER.debug("#patterns to be drawn " + gridManager.getBoxCount());

		gridManager.update(this.getWidth(), this.getHeight());
		repaint();
	}

	public void requestDrawNextPage() {
		if (firstIndexOnPage(page) >= patterns.size()) {
			return;
		}

		requestDraw(page + 1);
	}

	public void requestDrawPreviousPage() {
		if (page <= 0) {
			return;
		}

		requestDraw(page - 1);
	}

	/**
	 * Draws several patterns on this panel.
	 */
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		int boxCount = gridManager.getBoxCount();

		for (int boxIndex = 0; boxIndex < boxCount; boxIndex++) {

			Rectangle2D box = gridManager.getBoundBox(boxIndex);
			AngleUnitFlapPattern pattern;
			try {
				pattern = getPatternAt(page, boxIndex);
			} catch (RuntimeException e) {
				break;
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("draw " + boxIndex + " " + pattern + " @ " + box);
			}

			PatternInsideBoxDrawer drawer = new PatternInsideBoxDrawer();

			drawer.drawIndex(g2, boxIndex, box);
			drawer.drawPattern(g2, pattern, box);
		}

	}

	/**
	 * 
	 * @param page
	 *            page of patterns.
	 * @param boxIndex
	 *            start from 0 to box count.
	 * @return
	 *         pattern at the specified place.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             exceeds patterns list.
	 */
	private AngleUnitFlapPattern getPatternAt(final int page, final int boxIndex) {
		final int startIndex = firstIndexOnPage(page);
		int patternIndex = startIndex + boxIndex;

		return patterns.get(patternIndex);
	}

	private int firstIndexOnPage(final int page) {
		return page * gridManager.getBoxCount();
	}

	/**
	 * The action on resizing.
	 * 
	 * @author Koji
	 * 
	 */
	private class OnResized extends ComponentAdapter {
		/**
		 * repaints the patterns.
		 */
		@Override
		public void componentResized(final ComponentEvent e) {

			if (patterns == null || patterns.isEmpty()) {
				return;
			}
			requestDraw(page);
		}
	}

}
