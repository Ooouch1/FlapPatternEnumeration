package ouch.study.fpe.domain.graphic;

import java.awt.Graphics2D;
import java.util.List;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.LineType;

public class GraphicDrawer {

	private final Graphics2D g;

	public GraphicDrawer(final Graphics2D g2) {
		g = g2;
	}

	// =============================================================================================
	// Graphic
	// =============================================================================================

	/**
	 * draws all lines.
	 * 
	 * @param pattern
	 *            the pattern to draw
	 * @param cx
	 *            x position of the center
	 * @param cy
	 *            y position of the center
	 * @param length
	 *            the length of lines.
	 */
	public void draw(final AngleUnitFlapPattern pattern,
			final double cx, final double cy, final double length) {

		List<LineType> lines = pattern.getLines();

		GraphicFactory factory = new GraphicFactory();

		for (int index = 0; index < lines.size(); index++) {
			if (pattern.isEmptyAt(index)) {
				continue;
			}
			LineType type = lines.get(index);
			RadialLineGraphic lineGraphic = factory.createRadialLine(
					length, asRadian(index, pattern.getDivisionSize()), type);

			lineGraphic.draw(g, cx, cy);
		}
	}

	private double asRadian(final int index, final int divisionSize) {
		return (((double) index) / divisionSize) * 2 * Math.PI;
	}
}
