package ouch.study.fpe.domain;

import ouch.study.fpe.domain.graphic.RadialLine;

/**
 * creates objects for drawing.
 * 
 * this class is the gateway between domain package and domain.graphic package.
 * 
 * 
 * @author Koji
 * 
 */
class GraphicFactory {

	public RadialLineGraphic createRadialLine(double length, double rad, LineType type) {
		return new RadialLine(length, rad, type);
	}
}
