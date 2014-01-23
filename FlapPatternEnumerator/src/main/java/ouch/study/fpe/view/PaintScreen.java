package ouch.study.fpe.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ouch.study.fpe.domain.AngleUnitFlapPattern;

public class PaintScreen extends JPanel {

	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(PaintScreen.class);

	private List<AngleUnitFlapPattern> patterns;
	private int page = 0;

	private final PaintScreenGridManager gridManager = new PaintScreenGridManager(this);

	public PaintScreen() {
		this.setBackground(Color.white);
	}

	public void setPatterns(Set<AngleUnitFlapPattern> patterns) {
		this.patterns = new ArrayList<>(patterns);
	}

	public void requestDraw(int page) {
		this.page = page;

		LOGGER.debug("#patterns to be drawn " + gridManager.getBoxCount());

		gridManager.update();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		int boxCount = gridManager.getBoxCount();

		final int startIndex = page * boxCount;
		for (int index = startIndex; index < startIndex + boxCount; index++) {

			AngleUnitFlapPattern pattern = patterns.get(index);
			Rectangle2D box = gridManager.getBoundBox(index);

			if (box == null) {
				return;
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("draw " + index + " " + pattern + " @ " + box);
			}

			double length = box.getWidth() / 2 - 10;

			pattern.draw(g2, box.getCenterX(), box.getCenterY(), length);
		}

	}

}
