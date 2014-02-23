package ouch.study.fpe.domain.rule.lib;

import oripa.util.collection.AbstractRule;
import ouch.study.fpe.util.CircleLinkPointer;
import ouch.study.fpe.util.CircleLinkedList;

/**
 * 
 * @author Koji
 * 
 */
public class LayerFlattenability extends AbstractRule<LineGapCircle> {

	@Override
	public boolean holds(final LineGapCircle oneVertexPattern) {
		return canBeFlat(oneVertexPattern);
	}

	private boolean canBeFlat(final LineGapCircle lineGaps) {

		CircleLinkPointer<LineGap> iter = lineGaps.getHeadPointer();

		while (iter.hasNext()) {

			iter = findGapToMerge(iter);
			if (iter == null) {
				break;
			}

			// returned pointer refers the merged gap.
			iter = mergeAroundByFolding(iter);

			// mark the updated element as the head of circle loop
			// to recognize when this algorithm should finish.
			lineGaps.markAsHead(iter);
		}

		return isFoldedOut(lineGaps);
	}

	/**
	 * 
	 * @param head
	 *            the head of cirle loop
	 * @return
	 *         pointer refering the element to be merged.
	 *         null if no such element is found.
	 */
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

	/**
	 * tests whether the given pattern is in the acceptable state.
	 * 
	 * @param lineGaps
	 *            pattern
	 * @return
	 *         true if the pattern has been flattened.
	 */
	private boolean isFoldedOut(final CircleLinkedList<LineGap> lineGaps) {
		if (lineGaps.size() != 2) {
			return false;
		}

		CircleLinkPointer<LineGap> headPointer = lineGaps.getHeadPointer();

		LineGap head = headPointer.get();
		LineGap tail = headPointer.getNextValue();

		return head.getLine() == tail.getLine();
	}

	/**
	 * 
	 * @param iter
	 *            a pointer refering the element to test
	 * @return
	 *         true if the element is minimal.
	 *         Here "minimal" means both side neighbors have larger or equal
	 *         angle to the element.
	 */
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
	 *         previous pointer of {@code middle}, which is the result of merge.
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
