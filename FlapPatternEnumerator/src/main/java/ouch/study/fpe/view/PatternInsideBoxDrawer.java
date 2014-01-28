package ouch.study.fpe.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import ouch.study.fpe.domain.AngleUnitFlapPattern;

public class PatternInsideBoxDrawer {

	private static final double GAP_FROM_BOX = 10;

	public void drawPattern(final Graphics2D g2, final AngleUnitFlapPattern pattern, final Rectangle2D box) {

		double length = box.getWidth() / 2 - GAP_FROM_BOX;

		pattern.draw(g2, box.getCenterX(), box.getCenterY(), length);

	}

	public void drawIndex(final Graphics2D g2, final int index, final Rectangle2D box) {
		g2.setColor(Color.BLACK);

		g2.drawString(Integer.toString(index),
				(float) (box.getX() + GAP_FROM_BOX), (float) (box.getY() + GAP_FROM_BOX));
	}

}
