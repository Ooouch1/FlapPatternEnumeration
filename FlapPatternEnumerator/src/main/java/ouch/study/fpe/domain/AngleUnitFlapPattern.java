package ouch.study.fpe.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AngleUnitFlapPattern implements Cloneable {

	private final List<LineType> lines;

	private final Integer divisionSize;

	/**
	 * 
	 * @param divisionSize
	 *            angle is defined as k * 360[deg.] / divisionSize.
	 */
	public AngleUnitFlapPattern(Integer divisionSize) {
		this.divisionSize = divisionSize;

		LineType[] initialValues = new LineType[divisionSize];
		Arrays.fill(initialValues, null);

		lines = Arrays.asList(initialValues);
	}

	public AngleUnitFlapPattern(Integer divisionSize, List<LineType> lines) {
		this.divisionSize = divisionSize;
		this.lines = lines;
		if (lines.size() != divisionSize) {
			throw new IllegalArgumentException("size differs.");
		}
	}

	public void set(int index, LineType type) {
		lines.set(index, type);
	}

	public List<LineType> getLines() {
		return lines;
	}

	public Integer getDivisionSize() {
		return divisionSize;
	}

	/**
	 * 
	 * @return true if this pattern is foldable.
	 */
	public boolean isFoldable() {

		return holdsMaekawaTheorem() && holdsKawasakiTheorem();
	}

	public boolean holdsMaekawaTheorem() {
		int mountainCount = 0;
		int valleyCount = 0;

		for (LineType type : lines) {
			if (type == null) {
				continue;
			}
			switch (type) {
			case MOUNTAIN:
				mountainCount++;
				break;
			case VALLEY:
				valleyCount++;
			default:
				break;
			}
		}

		return Math.abs(mountainCount - valleyCount) == 2;
	}

	public boolean holdsKawasakiTheorem() {
		int firstLineIndex = findFirstLineIndex();

		int diff = 0;
		int flipFlopSign = 1;

		for (int i = 1; i <= divisionSize; i++) {
			LineType type = lines.get((firstLineIndex + i) % divisionSize);

			diff += flipFlopSign;

			if (type == null) {
				continue;
			}
			flipFlopSign *= -1;
		}

		return diff == 0;
	}

	public int findFirstLineIndex() {
		int index = 0;
		for (LineType type : lines) {
			if (type == null) {
				index++;
			} else {
				return index;
			}
		}

		return -1;
	}

	public int findLastIndexOf(LineType line) {
		return lines.lastIndexOf(line);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		List<LineType> clonedLines = new ArrayList<>(lines);
		AngleUnitFlapPattern cloned = new AngleUnitFlapPattern(divisionSize,
				clonedLines);

		return cloned;
	}

	/**
	 * 
	 * @return max index of line list.
	 */
	public int getTailIndex() {
		return divisionSize - 1;
	}

	public AngleUnitFlapPattern cloneInstance() {
		try {
			return (AngleUnitFlapPattern) clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isEmptyAt(int index) {
		return lines.get(index) == null;
	}

	public boolean isEmpty() {
		for (int i = 0; i < divisionSize; i++) {
			if (!isEmptyAt(i)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * @param pattern
	 *            pattern to be tested
	 * @param axisIndex
	 *            a line index of mirroring center.
	 * 
	 * @return true if this instance is a mirrored pattern according to given
	 *         axis.
	 */
	public boolean isMirrorOf(final AngleUnitFlapPattern pattern,
			final int axisIndex) {

		if (this.divisionSize != pattern.divisionSize) {
			return false;
		}

		for (int i = 0; i < divisionSize; i++) {
			// rotate the index for the case of axisIndex == 0
			int index = rotateIndex(i, axisIndex);
			int reversedIndex = rotateIndex(divisionSize - i, axisIndex);

			if (this.lines.get(index) != pattern.lines.get(reversedIndex)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * test for each axises.
	 * 
	 * @param pattern
	 *            expected to be mirror
	 * @return true if {@link #isMirrorOf(AngleUnitFlapPattern, int)} is true
	 *         for some axis.
	 */
	public boolean isMirrorOf(final AngleUnitFlapPattern pattern) {

		for (int i = 0; i < divisionSize; i++) {
			if (this.isMirrorOf(pattern, i)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param index
	 *            0<= index < divisionSize
	 * @param diff
	 *            any integer (both negative and positive)
	 * @return index + diff taking account of circle loop
	 */
	private int rotateIndex(int index, int diff) {
		return (index + divisionSize + diff % divisionSize) % divisionSize;
	}

	/**
	 * 
	 * @param pattern
	 *            base of rotation
	 * @param diff
	 *            how much rotated in index circle.
	 * @return true if this instance is rotation of given pattern with given
	 *         difference.
	 */
	public boolean isRotationOf(final AngleUnitFlapPattern pattern,
			final int diff) {

		for (int i = 0; i < divisionSize; i++) {
			int index = rotateIndex(i, diff);
			if (lines.get(index) != pattern.lines.get(i)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * @param pattern
	 * 
	 * @return true if {@link #isRotationOf(AngleUnitFlapPattern, int)} holds
	 *         true for some diff.
	 */
	public boolean isRotationOf(final AngleUnitFlapPattern pattern) {
		for (int i = 0; i < divisionSize; i++) {
			if (isRotationOf(pattern, i)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return lines.toString();
	}

	public boolean equals(AngleUnitFlapPattern obj) {
		if (!(obj instanceof AngleUnitFlapPattern)) {
			return false;
		}
		AngleUnitFlapPattern pattern = (AngleUnitFlapPattern) obj;
		return this.lines.equals(pattern.lines)
				// return equalLines(this.lines, pattern.lines)
				&& (this.divisionSize == pattern.divisionSize);
	}

}
