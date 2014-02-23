package ouch.study.fpe.domain.rule;

import java.util.HashMap;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.value.LineType;

/**
 * this object has a unique key among all flap patterns and its unified pattern.
 * unified pattern is the identical pattern in terms of rotation and mirroring.
 * 
 * @author Koji
 * 
 */
public class PatternHashElement {

	private Long bitString;
	private long maxShiftWidth;

	private AngleUnitFlapPattern pattern;
	private static final long ONE_LINE_BIT_LENGTH = LineType.BIT_LENGTH;

	private static HashMap<Integer, Long> BITSTRING_MASKS = new HashMap<>();

	// ==============================================================================
	// constructions
	// ==============================================================================

	@SuppressWarnings("unused")
	private PatternHashElement() {
		maxShiftWidth = 0;
		pattern = null;
	}

	/**
	 * 
	 * @param pattern
	 *            pattern
	 */
	public PatternHashElement(final AngleUnitFlapPattern pattern) {
		this.pattern = pattern;

		initialize(pattern);
	}

	PatternHashElement(final PatternHashElement e) {
		pattern = e.pattern;

		initialize(pattern);
	}

	private void initialize(final AngleUnitFlapPattern pattern) {
		Integer divisionSize = pattern.getDivisionSize();
		maxShiftWidth = createMaxShiftWidth(divisionSize);

		if (BITSTRING_MASKS.get(divisionSize) == null) {
			BITSTRING_MASKS.put(divisionSize, createBitStringMask(divisionSize));
		}
		bitString = compile(pattern);

	}

	/**
	 * 
	 * @return
	 *         width for shifting one line's bits side to side.
	 */
	private long createMaxShiftWidth(final int divisionSize) {
		return (divisionSize - 1) * ONE_LINE_BIT_LENGTH;
	}

	private Long createBitStringMask(final int divisionSize) {
		// initialize
		Long mask = 0L;
		Long oneLineMask = LineType.BIT_MASK;

		for (int i = 0; i < divisionSize; i++) {
			mask |= oneLineMask;
			oneLineMask <<= ONE_LINE_BIT_LENGTH;
		}

		return mask;
	}

	// ==============================================================================
	// domain methods
	// ==============================================================================

	public AngleUnitFlapPattern getPattern() {
		return pattern;
	}

	/**
	 * build bitString as a key.
	 * 
	 * @return
	 *         key
	 */
	private Long compile(final AngleUnitFlapPattern pattern) {
		Long bits = 0L;
		if (pattern.getDivisionSize() > Long.SIZE / ONE_LINE_BIT_LENGTH) {
			throw new IndexOutOfBoundsException("too many line to create bit string.");
		}

		// place bits sequentially
		for (LineType type : pattern.getLines()) {
			bits = (bits << ONE_LINE_BIT_LENGTH) | type.getBit();
		}

		return bits;
	}

	/**
	 * 
	 * retrieve the key.
	 * the key is a bit string whose highest bits is the first line type of the
	 * pattern and
	 * latter lines are sequentially placed until lowest bits.
	 * 
	 * @return
	 *         identical key.
	 */
	public Long getKey() {
		return bitString;
	}

	/**
	 * 
	 * @return
	 *         a new rotated hash element whose inner pattern is the same as
	 *         this object. the rotation is left to right, i.e., 00 11 00 will
	 *         results in 11 00 00 and 11 00 00 will become 00 00 11.
	 */
	public PatternHashElement createBitRotatedOneAngleUnit() {
		PatternHashElement rotated = new PatternHashElement(pattern);

		long highest = getHighestBits(bitString);

		rotated.bitString = bitString << ONE_LINE_BIT_LENGTH;

		rotated.bitString |= highest >> maxShiftWidth;

		rotated.bitString &= BITSTRING_MASKS.get(pattern.getDivisionSize());
		return rotated;
	}

	/**
	 * 
	 * 
	 * 
	 * @param axis
	 *            index of mirror center
	 * @return a new mirrored pattern whose inner pattern is the same as this
	 *         object.
	 * 
	 */
	public PatternHashElement createBitMirrored(final int axis) {

		PatternHashElement mirrored = new PatternHashElement(pattern);
		mirrored.bitString = compile(pattern.createMirroredPattern(axis));

		return mirrored;
	}

	/**
	 * 
	 * @param b
	 * @return
	 *         highest bits of given value
	 */
	private long getHighestBits(final long b) {

		return b & (LineType.BIT_MASK << maxShiftWidth);
	}

	public PatternHashElement cloneInstance() {
		return new PatternHashElement(this);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return cloneInstance();
	}

}
