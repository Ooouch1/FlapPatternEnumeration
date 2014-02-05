package ouch.study.fpe.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.graphic.GraphicDrawer;

public class PatternInsideBoxDrawer {

	private final double gap;

	public PatternInsideBoxDrawer(final double surroundingGap) {
		gap = surroundingGap;
	}

	public void drawPattern(final Graphics2D g2, final AngleUnitFlapPattern pattern, final Rectangle2D box) {

		double length = box.getWidth() / 2 - gap;

		GraphicDrawer drawer = new GraphicDrawer(g2);

		drawer.draw(pattern, box.getCenterX(), box.getCenterY(), length);

		g2.setColor(Color.GRAY);
		g2.draw(box);

	}

	public void drawIndex(final Graphics2D g2, final int index, final Rectangle2D box) {
		g2.setColor(Color.BLACK);

		g2.drawString(Integer.toString(index),
				(float) (box.getX() + gap), (float) (box.getY() + gap));
	}

}
