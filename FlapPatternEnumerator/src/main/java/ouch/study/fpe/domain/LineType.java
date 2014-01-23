package ouch.study.fpe.domain;

import java.util.Comparator;

public enum LineType {
	MOUNTAIN, VALLEY;

	/**
	 * MOUNTAIN < VALLEY < null
	 * @author Koji
	 *
	 */
	public static class LineTypeComparator implements Comparator<LineType> {
		@Override
		public int compare(LineType o1, LineType o2) {
			if (o1 == o2) {
				return 0;
			}

			if (o1 == null) {
				return 1;
			}

			if (o2 == null) {
				return -1;
			}

			if (o1 == MOUNTAIN && o2 == VALLEY) {
				return -1;
			}

			if (o1 == VALLEY && o2 == MOUNTAIN) {
				return 1;
			}

			return 0;
		}
	}
}
