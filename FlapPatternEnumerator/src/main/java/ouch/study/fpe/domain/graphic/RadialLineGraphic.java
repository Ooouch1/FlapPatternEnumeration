package ouch.study.fpe.domain.graphic;

import java.awt.Graphics2D;

public interface RadialLineGraphic {

	/**
	 * draw a line.
	 * 
	 * @param g
	 *            the place to draw.
	 * @param cx
	 *            x position of the center of radiation.
	 * @param cy
	 *            y position of the center of radiation.
	 */
	public abstract void draw(Graphics2D g, double cx, double cy);

}