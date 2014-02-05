package ouch.study.fpe.view.pieceinput;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.view.PaintScreen;
import ouch.study.fpe.view.PaintScreenGridManager;

public class PieceInputController {

	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(PieceInputController.class);

	private final PaintScreen screen;
	private int currentBoxIndex = -1;
	private int divisionSize;

	private LinkedList<AngleUnitFlapPattern> patterns = new LinkedList<>();

	public PieceInputController(final int divisionSize, final PaintScreen scr) {
		screen = scr;
		this.divisionSize = divisionSize;

		screen.addMouseListener(new OnMouseClick());
		screen.addMouseMotionListener(new OnMouseMove());

	}

	public List<AngleUnitFlapPattern> getPatterns() {
		return patterns;
	}

	private class OnMouseClick extends MouseAdapter {
		@Override
		public void mouseClicked(final MouseEvent e) {

			if (removeIfRightClicked(e)) {
				return;
			}

			LOGGER.debug("current boxIndex = " + currentBoxIndex);

			int patternIndex = screen.getPatternIndexAt(screen.getPage(), currentBoxIndex);

			if (isOutOfIndexBound(patternIndex)) {
				allocateNewPattern();
				return;
			}

			PaintScreenGridManager gridManager = screen.getGridManager();

			Rectangle2D box = gridManager.getBoundBox(currentBoxIndex);

			AngleUnitFlapPattern pattern = screen.getPatternAt(screen.getPage(), currentBoxIndex);

			LineTypeChanger changer = new LineTypeChanger(10);

			changer.changeIfCloseEnough(pattern, box, e.getX(), e.getY());

			screen.repaint();

		}

		private boolean removeIfRightClicked(final MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				patterns.removeLast();
				screen.setFlapPatterns(patterns);
				LOGGER.info("erase " + patterns.size() + "th piece");
				return true;
			}

			return false;
		}

		private boolean isOutOfIndexBound(final int patternIndex) {
			return (patternIndex >= patterns.size() || currentBoxIndex < 0);
		}

		private void allocateNewPattern() {
			AngleUnitFlapPattern pattern = new AngleUnitFlapPattern(divisionSize);
			pattern.set(0, LineType.MOUNTAIN);
			patterns.add(pattern);
			screen.setFlapPatterns(patterns);

			LOGGER.info("allocate " + (patterns.size() - 1) + "th piece");
			return;

		}
	}

	private class OnMouseMove extends MouseAdapter {

		@Override
		public void mouseMoved(final MouseEvent e) {
			PaintScreenGridManager gridManager = screen.getGridManager();

			double x = e.getPoint().getX();
			double y = e.getPoint().getY();
			// LOGGER.trace("mouse x:" + x + ", y:" + y);

			currentBoxIndex = gridManager.getIndexAt(x, y);
		}
	}
}
