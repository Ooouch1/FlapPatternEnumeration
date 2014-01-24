package ouch.study.fpe.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ouch.study.fpe.domain.AngleUnitFlapPattern;

public class PaintScreen extends JPanel {

	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(PaintScreen.class);

	private List<AngleUnitFlapPattern> patterns = new ArrayList<>();
	private int page = 0;

	private final PaintScreenGridManager gridManager = new PaintScreenGridManager(this.getWidth(), this.getHeight());

	public PaintScreen() {

		addComponentListener(new OnResized());

		this.setBackground(Color.white);
	}

	public void setPatterns(Set<AngleUnitFlapPattern> p) {
		this.patterns = new ArrayList<>(p);

		Collections.sort(patterns);

	}

	public void requestDraw(int page) {
		this.page = page;

		LOGGER.debug("#patterns to be drawn " + gridManager.getBoxCount());

		gridManager.update(this.getWidth(), this.getHeight());
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

	private class OnResized extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent e) {

			if (patterns == null || patterns.isEmpty()) {
				return;
			}
			requestDraw(page);
		}
	}

}
