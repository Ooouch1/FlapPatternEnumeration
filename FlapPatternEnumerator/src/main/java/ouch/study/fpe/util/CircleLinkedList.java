package ouch.study.fpe.util;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class CircleLinkedList<Value> {

	/** refers first element. */
	CircleLinkElement<Value> head;
	Set<CircleLinkElement<Value>> elements = new HashSet<>();

	public CircleLinkedList(final Value first) {
		head = new CircleLinkElement<Value>(first);
		elements.add(head);
	}

	public CircleLinkElement<Value> getHead() {
		return head;
	}

	/**
	 * throws {@link NoSuchElementException}.
	 * 
	 * @param element
	 *            element
	 */
	public void markAsHead(final CircleLinkElement<Value> element) {
		throwExceptionIfNotContained(element);

		head = element;
	}

	/**
	 * 
	 * throws {@linkplain NoSuchElementException} if given element is not
	 * contained in this list.
	 * 
	 * @param element
	 *            element
	 * 
	 * 
	 */
	private void throwExceptionIfNotContained(final CircleLinkElement<Value> element) {
		if (!contains(element)) {
			throw new NoSuchElementException();
		}

	}

	/**
	 * throws {@link NoSuchElementException}.
	 * 
	 * @param target
	 *            element
	 * @param v
	 *            value to insert.
	 * 
	 * @return element for v.
	 */
	public CircleLinkElement<Value> insertAsPrevious(final CircleLinkElement<Value> target, final Value v) {
		throwExceptionIfNotContained(target);

		CircleLinkElement<Value> element = target.insertAsPrevious(v);
		elements.add(element);

		if (target == head) {
			head = element;
		}

		return element;
	}

	/**
	 * throws {@link NoSuchElementException}.
	 * 
	 * @param target
	 *            element
	 * @param v
	 *            value to insert.
	 * @return element for v.
	 */
	public CircleLinkElement<Value> insertAsNext(final CircleLinkElement<Value> target, final Value v) {
		throwExceptionIfNotContained(target);

		CircleLinkElement<Value> element = target.insertAsNext(v);
		elements.add(element);

		return element;

	}

	/**
	 * throws {@link NoSuchElementException}.
	 * 
	 * @param target
	 *            element
	 * @param shouldReturnPrevious
	 *            to select the return value, also to select new head if given
	 *            target is the current head.
	 * @return
	 *         previous of the target if shouldRetrunPrevious is true, otherwise
	 *         next of the target.
	 */
	public CircleLinkElement<Value> leaveFromCircle(final CircleLinkElement<Value> target,
			final boolean shouldReturnPrevious) {
		throwExceptionIfNotContained(target);

		CircleLinkElement<Value> element = target.leaveFromCircle(shouldReturnPrevious);
		if (target == head) {
			head = element;
		}

		elements.remove(target);

		return element;

	}

	public boolean contains(final CircleLinkElement<Value> e) {
		return elements.contains(e);
	}

	public int size() {
		return elements.size();
	}

}
