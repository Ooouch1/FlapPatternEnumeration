package ouch.study.fpe.domain;

import java.awt.Color;

public enum LineType {

	/**
	 * mountaion fold.
	 */
	MOUNTAIN(Color.BLUE, 1),

	/**
	 * valley fold.
	 */
	VALLEY(Color.RED, 2),

	/**
	 * no line.
	 */
	EMPTY(null, 0);

	/**
	 * the color for graphic.
	 */
	private final Color color;

	/**
	 * the bit string for compiling pattern into key.
	 */
	private final long bit;

	public static final long BIT_LENGTH = 2;
	public static final long BIT_MASK = 0x03;

	private LineType(final Color c, final long b) {
		color = c;
		bit = b;
	}

	public LineType nextType() {
		LineType[] values = LineType.values();

		return values[(int) ((this.bit + 1) % values.length)];
	}

	/**
	 * 
	 * @return
	 *         0x01 for MOUNTAIN, 0x02 for VALLEY, 0x00 for EMPTY
	 */
	public long getBit() {
		return bit;
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
