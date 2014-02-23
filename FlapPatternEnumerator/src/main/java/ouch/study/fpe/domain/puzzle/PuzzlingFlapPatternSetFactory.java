package ouch.study.fpe.domain.puzzle;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import oripa.util.collection.Rule;
import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.rule.AlwaysTrue;
import ouch.study.fpe.domain.value.LineType;

public class PuzzlingFlapPatternSetFactory {
	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(PuzzlingFlapPatternSetFactory.class);

	private Integer tailIndex;

	private List<AngleUnitFlapPattern> patterns = new LinkedList<>();

	@SuppressWarnings("unchecked")
	private Rule<AngleUnitFlapPattern> acceptablePatternCondition = new AlwaysTrue();

	@SuppressWarnings("unchecked")
	private Rule<AngleUnitFlapPattern> pruningCondition = new AlwaysTrue().asDenied();

	private int recursionCount = 0;

	private Map<LineType, Collection<Piece>> pieceMap;

	/**
	 * More detailed setting is enabled.
	 * 
	 * @param tailIndex
	 *            the index at which this object stops adding.
	 * @param acceptablePatternCondition
	 *            any {@link Rule} object which describes your preferrable
	 *            feature.
	 */
	public PuzzlingFlapPatternSetFactory(
			final Rule<AngleUnitFlapPattern> acceptablePatternCondition,
			final Rule<AngleUnitFlapPattern> pruningCondition) {

		this.acceptablePatternCondition = acceptablePatternCondition;
		this.pruningCondition = pruningCondition;
	}

	private Map<LineType, Collection<Piece>> separatePiecesByFirstLine(final Collection<List<LineType>> pieces) {
		Map<LineType, Collection<Piece>> map = new HashMap<>();
		map.put(LineType.MOUNTAIN, new HashSet<Piece>());
		map.put(LineType.VALLEY, new HashSet<Piece>());

		for (List<LineType> pieceLines : pieces) {
			map.get(pieceLines.get(0)).add(new Piece(pieceLines));
			LOGGER.debug("add piece to map: " + map);
		}

		return map;

	}

	public List<AngleUnitFlapPattern> createPatterns(final int divisionSize,
			final Collection<List<LineType>> pieces) {

		tailIndex = divisionSize;

		patterns = new LinkedList<>();
		pieceMap = separatePiecesByFirstLine(pieces);

		Collection<Piece> candidates = pieceMap.get(LineType.MOUNTAIN);
		if (candidates.isEmpty()) {
			candidates = pieceMap.get(LineType.VALLEY);
		}

		LOGGER.info(pieces.size() + "pieces: " + pieceMap);

		AngleUnitFlapPattern seed = new AngleUnitFlapPattern(divisionSize);
		createPatternsImpl(seed, 0, candidates);

		return patterns;
	}

	private void createPatternsImpl(final AngleUnitFlapPattern seed, final int indexToAdd,
			final Collection<Piece> pieces) {

		trace(seed);

		if (indexToAdd == tailIndex) {
			if (acceptablePatternCondition.holds(seed)) {
				trace("accepted");
				patterns.add(seed.cloneInstance());
			}
			return;
		}

		if (indexToAdd > tailIndex) {
			trace("recursion stopped: overlength");
			return;
		}

		if (pieces.isEmpty()) {
			trace("recurtion stopped: empty candidates");
			return;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("seed: " + seed);
			LOGGER.debug("indexToAdd: " + indexToAdd);
			LOGGER.debug("pieces: " + pieces);
		}

		for (Piece piece : pieces) {

			trace(piece);

			if (!isAppendable(seed, indexToAdd, piece)) {
				continue;
			}

			int nextIndex = appendTo(seed, indexToAdd, piece);
			if (!pruningCondition.holds(seed)) {
				// call with connectable pieces
				createPatternsImpl(seed, nextIndex, pieceMap.get(piece.getLast()));
			}

			undo(seed, indexToAdd, piece);
		}

	}

	private boolean isAppendable(final AngleUnitFlapPattern pattern, final int from, final Piece piece) {

		int tailOfAddition = from + piece.size() - 1;

		// overlength.
		if (tailOfAddition > tailIndex) {
			trace("not appendable: index overrun");
			return false;
		}

		// empty?
		for (int i = from + 1; i < tailOfAddition; i++) {
			if (!pattern.isEmptyAt(i)) {
				trace("not appendable: alredy filled");
				return false;
			}
		}

		// need connection.
		if (pattern.isEmptyAt(from) || pattern.getAt(from) == piece.getLineAt(0)) {
		} else {
			trace("not appendable: start line does not have connection");
			return false;
		}

		if (pattern.isEmptyAt(tailOfAddition) || pattern.getAt(tailOfAddition) == piece.getLast()) {
			trace("APPENDABLE: end line has connection");
			return true;
		}
		return false;
	}

	private void trace(final Object obj) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace(obj);
		}
	}

	/**
	 * 
	 * @param pattern
	 *            pattern which will be the place to put a piece.
	 * @param from
	 *            index of start point of addition.
	 * @param piece
	 *            the piece to append
	 * @return
	 *         last index of addition, which should be the start point of next
	 *         addition.
	 */
	private int appendTo(final AngleUnitFlapPattern pattern, final int from, final Piece piece) {
		for (int i = 0; i < piece.size(); i++) {
			pattern.set(from + i, piece.getLineAt(i));
		}

		return from + piece.size() - 1;
	}

	private void undo(final AngleUnitFlapPattern pattern, final int from, final Piece piece) {
		for (int i = 1; i < piece.size(); i++) {
			int index = from + i;
			if (pattern.asIndex(index) == 0) {
				break;
			}
			pattern.set(index, LineType.EMPTY);
		}
	}
}
