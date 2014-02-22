package ouch.study.fpe.domain.rule;

import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.util.CircleLinkPointer;
import ouch.study.fpe.util.CircleLinkedList;

/**
 * 
 * @author Koji
 * 
 */
public class LayerCollisionChecker {

	/**
	 * 
	 * @param pattern
	 *            a pattern which holds Maekawa and Kawasaki theorems.
	 * @return
	 *         true if the pattern can be folded flatten.
	 */
	public boolean patternCanBeFlatten(final AngleUnitFlapPattern pattern) {
		FlapPatternConverter converter = new FlapPatternConverter();

		LineGapCircle lineGaps = converter.toLineGapCircle(pattern);

		return foldFlap(lineGaps);
	}

	boolean foldFlap(final LineGapCircle lineGaps) {

		CircleLinkPointer<LineGap> iter = lineGaps.getHeadPointer();

		while (iter.hasNext()) {

			iter = findGapToMerge(iter);
			if (iter == null) {
				break;
			}

			iter = mergeAroundByFolding(iter);
			lineGaps.markAsHead(iter);
		}

		return isFoldedOut(lineGaps);
	}

	private CircleLinkPointer<LineGap> findGapToMerge(final CircleLinkPointer<LineGap> head) {
		CircleLinkPointer<LineGap> iter = head;

		while (!iter.getNext().equals(head)) {
			LineGap current = iter.get();
			LineGap next = iter.getNextValue();

			if (current.getLine() == next.getLine()) {

			} else if (isMinimalGap(iter)) {
				return iter;
			}
			iter = iter.getNext();
		}

		return null;
	}

	private boolean isFoldedOut(final CircleLinkedList<LineGap> lineGaps) {
		if (lineGaps.size() != 2) {
			return false;
		}

		CircleLinkPointer<LineGap> headPointer = lineGaps.getHeadPointer();

		LineGap head = headPointer.get();
		LineGap tail = headPointer.getNextValue();

		return head.getLine() == tail.getLine();
	}

	private boolean isMinimalGap(final CircleLinkPointer<LineGap> iter) {
		LineGap current = iter.get();
		LineGap previous = iter.getPreviousValue();
		LineGap next = iter.getNextValue();

		return current.angleIsLessThanOrEqual(previous)
				&& current.angleIsLessThanOrEqual(next);
	}

	/**
	 * 
	 * @param middle
	 *            pointer of middle area
	 * @return
	 *         previous pointer of {@code middle} whose value holds
	 *         merged angle.
	 */
	private CircleLinkPointer<LineGap> mergeAroundByFolding(
			final CircleLinkPointer<LineGap> middle) {
		LineGap current = middle.get();
		LineGap previous = middle.getPreviousValue();
		LineGap next = middle.getNextValue();

		// fold current line
		previous.subtractAngleBy(current);
		// fold next line
		previous.addAngleBy(next);

		// remove folded lines
		middle.getNext().leaveFromCircle(true);
		return middle.leaveFromCircle(true);
	}
}
