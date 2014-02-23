package ouch.study.fpe.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ouch.study.fpe.domain.value.LineType;

/**
 * This object describes a flap which is composed with 360/k degrees angles.
 * 
 * @author Koji
 * 
 */
public class AngleUnitFlapPattern implements Cloneable, Comparable<AngleUnitFlapPattern> {

	private final List<LineType> lines;

	private final Integer divisionSize;

	/**
	 * 
	 * @param divisionSize
	 *            angle is defined as k * 360[deg.] / divisionSize.
	 */
	public AngleUnitFlapPattern(final Integer divisionSize) {
		this.divisionSize = divisionSize;

		LineType[] initialValues = new LineType[divisionSize];
		Arrays.fill(initialValues, LineType.EMPTY);

		lines = Arrays.asList(initialValues);
	}

	public AngleUnitFlapPattern(final Integer divisionSize, final List<LineType> lines) {
		this.divisionSize = divisionSize;
		this.lines = lines;
		if (lines.size() != divisionSize) {
			throw new IllegalArgumentException("size differs.");
		}
	}

	public void set(final int index, final LineType type) {
		lines.set(asIndex(index), type);
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
	public boolean isProbablyFoldable() {

		return holdsMaekawaTheorem() && holdsKawasakiTheorem();
	}

	public boolean holdsMaekawaTheorem() {
		int mountainCount = 0;
		int valleyCount = 0;

		for (LineType type : lines) {
			if (isEmpty(type)) {
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

			if (isEmpty(type)) {
				continue;
			}
			flipFlopSign *= -1;
		}

		return diff == 0;
	}

	public int findFirstLineIndex() {
		int index = 0;
		for (LineType type : lines) {
			if (isEmpty(type)) {
				index++;
			} else {
				return index;
			}
		}

		return -1;
	}

	public int findLastIndexOf(final LineType line) {
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

	public boolean isEmptyAt(final int index) {
		LineType type = getAt(index);
		return isEmpty(type);
	}

	public boolean isEmpty() {
		for (int i = 0; i < divisionSize; i++) {
			if (!isEmptyAt(i)) {
				return false;
			}
		}

		return true;
	}

	private boolean isEmpty(final LineType type) {
		return type == null || type == LineType.EMPTY;
	}

	public AngleUnitFlapPattern createMirroredPattern(final int axisIndex) {
		AngleUnitFlapPattern mirrored = new AngleUnitFlapPattern(divisionSize);

		for (int i = 0; i < divisionSize; i++) {
			// rotate the index of axisIndex == 0
			int index = rotateIndex(i, axisIndex);
			int reversedIndex = rotateIndex(divisionSize - i, axisIndex);

			mirrored.set(reversedIndex, this.getAt(index));
		}

		return mirrored;
	}

	/**
	 * 
	 * @param index
	 *            0<= index < divisionSize
	 * @param diff
	 *            any integer (both negative and positive)
	 * @return index + diff taking account of circle loop
	 */
	private int rotateIndex(final int index, final int diff) {
		return (index + divisionSize + diff % divisionSize) % divisionSize;
	}

	/**
	 * 
	 * @param n
	 *            index
	 * @return n as an index taking account of circle loop
	 */
	public int asIndex(final int n) {

		int index = n % divisionSize;

		if (index < 0) {
			return divisionSize + index;
		}

		return index;
	}

	@Override
	public String toString() {
		return lines.toString();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof AngleUnitFlapPattern)) {
			return false;
		}
		AngleUnitFlapPattern pattern = (AngleUnitFlapPattern) obj;
		return this.lines.equals(pattern.lines)
				// return equalLines(this.lines, pattern.lines)
				&& (this.divisionSize == pattern.divisionSize);
	}

	@Override
	public int compareTo(final AngleUnitFlapPattern o) {

		int thisCountLine = this.countLines();
		int oCountLine = o.countLines();

		if (this.divisionSize != o.divisionSize) {
			return Integer.compare(this.divisionSize, o.divisionSize);

		} else if (thisCountLine != oCountLine) {
			return Integer.compare(thisCountLine, oCountLine);
		} else {

			for (int i = 0; i < divisionSize; i++) {
				LineType thisLine = this.lines.get(i);
				LineType oLine = o.lines.get(i);
				if (thisLine == oLine) {
					continue;
				}
				return thisLine.compareTo(oLine);
			}
		}

		return 0;
	}

	public int countLines() {
		int count = 0;
		for (LineType type : lines) {
			if (!isEmpty(type)) {
				count++;
			}
		}

		return count;
	}

	public int countLines(final LineType t) {
		int count = 0;
		for (LineType type : lines) {
			if (type == t) {
				count++;
			}
		}

		return count;
	}

	public LineType getMajor() {
		int mountainCount = countLines(LineType.MOUNTAIN);
		int valleyCount = countLines(LineType.VALLEY);

		if (mountainCount > valleyCount) {
			return LineType.MOUNTAIN;
		}

		return LineType.VALLEY;
	}

	/**
	 * returns line type at given index taking account of circle loop.
	 * 
	 * @param index
	 * 
	 * @return
	 *         line type at the index
	 */
	public LineType getAt(final int index) {

		return lines.get(asIndex(index));
	}

	public AngleUnitFlapPattern createDual() {
		AngleUnitFlapPattern dual = cloneInstance();

		for (int i = 0; i < getDivisionSize(); i++) {
			dual.set(i, getAt(i).getDualType());
		}

		return dual;
	}

}
