package ouch.study.fpe.domain.graphic;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.domain.RadialLineGraphic;

/**
 * radial line to draw flap pattern.
 * 
 * @author Koji
 * 
 */
public class RadialLine implements RadialLineGraphic {
	private final double length;
	private final double rad;
	private final LineType type;

	/**
	 * 
	 * @param length
	 *            length of the line.
	 * @param rad
	 *            angle in radian.
	 * @param type
	 *            type of the lin
	 */
	public RadialLine(double length, double rad, LineType type) {
		this.length = length;
		this.rad = rad;
		this.type = type;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * ouch.study.fpe.domain.graphic.RadialLineGraphic#draw(java.awt.Graphics2D,
	 * double, double)
	 */
	@Override
	public void draw(Graphics2D g, double cx, double cy) {
		double dx = length * Math.cos(rad);
		double dy = length * Math.sin(rad);

		Line2D line = new Line2D.Double(cx, cy, cx + dx, cy + dy);
		g.setColor(type.getColor());

		g.draw(line);
	}
}
