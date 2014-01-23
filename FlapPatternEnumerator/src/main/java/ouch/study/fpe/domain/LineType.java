package ouch.study.fpe.domain;

import java.awt.Color;

public enum LineType {

	/**
	 * mountaion fold.
	 */
	MOUNTAIN(Color.RED),

	/**
	 * valley fold.
	 */
	VALLEY(Color.BLUE);

	/**
	 * the color for graphic.
	 */
	private final Color color;

	private LineType(Color c) {
		color = c;
	}

	/**
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	// /**
	// * MOUNTAIN < VALLEY < null
	// * @author Koji
	// *
	// */
	// public static class LineTypeComparator implements Comparator<LineType> {
	// @Override
	// public int compare(LineType o1, LineType o2) {
	// if (o1 == o2) {
	// return 0;
	// }
	//
	// if (o1 == null) {
	// return 1;
	// }
	//
	// if (o2 == null) {
	// return -1;
	// }
	//
	// if (o1 == MOUNTAIN && o2 == VALLEY) {
	// return -1;
	// }
	//
	// if (o1 == VALLEY && o2 == MOUNTAIN) {
	// return 1;
	// }
	//
	// return 0;
	// }
	// }
}
