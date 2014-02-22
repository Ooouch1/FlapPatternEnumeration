package ouch.study.fpe.util;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class CircleLinkedList<Value> {

	/** refers first element. */
	private CircleLinkElement<Value> head;
	/** manages given values . */
	private Set<CircleLinkElement<Value>> elements = new HashSet<>();

	public CircleLinkedList(final Value v) {
		head = insertIfEmpty(v);
	}

	public CircleLinkedList(final List<Value> values) {
		// dummy head for simple logic
		head = insertIfEmpty(values.get(0));

		// add all
		CircleLinkElement<Value> iter = head;
		for (Value v : values) {
			iter = insertAsNext(iter, v);
		}

		// remove dummy head
		leaveFromCircle(head, false);
	}

	public CircleLinkPointer<Value> getHeadPointer() {
		return new CircleLinkPointerImpl<>(this, head);
	}

	CircleLinkElement<Value> getHead() {
		return head;
	}

	/**
	 * @throws NoSuchElementException
	 *             if element at given pointer is not managed by this object.
	 * @throws IllegalArgumentException
	 *             if given pointer is not implemented by this class.
	 * 
	 * @param element
	 *            element
	 */
	public void markAsHead(final CircleLinkPointer<Value> p) {
		if (!(p instanceof CircleLinkPointerImpl)) {
			throw new IllegalArgumentException();
		}
		CircleLinkPointerImpl<Value> pointer = (CircleLinkPointerImpl<Value>) p;
		throwNoSuchElementExceptionIfNotContained(pointer.element);

		head = pointer.element;
	}

	// /**
	// * throws {@link NoSuchElementException}.
	// *
	// * @param element
	// * element
	// */
	// public void markAsHead(final CircleLinkElement<Value> element) {
	// throwExceptionIfNotContained(element);
	//
	// head = element;
	// }

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
	private void throwNoSuchElementExceptionIfNotContained(final CircleLinkElement<Value> element) {
		if (!contains(element)) {
			throw new NoSuchElementException();
		}

	}

	/**
	 * Inserts given value as the previous element of {@code target}.
	 * 
	 * If given target is the head, the new element will be the tail of this
	 * list.
	 * 
	 * @throws InvalidUsageException
	 *             if this list is empty.
	 * @throws NoSuchElementException
	 *             if given target is not managed by this object.
	 * 
	 * @param target
	 *            element
	 * @param v
	 *            value to insert.
	 * 
	 * @return element for v.
	 */
	CircleLinkElement<Value> insertAsPrevious(final CircleLinkElement<Value> target, final Value v) {
		if (isEmpty()) {
			throw new InvalidUsageException("Please use insertIfEmpty() for empty list.");
		} else {
			throwNoSuchElementExceptionIfNotContained(target);
		}

		CircleLinkElement<Value> element = target.insertAsPrevious(v);
		elements.add(element);

		// if (target == head) {
		// head = element;
		// }

		return element;
	}

	/**
	 * @throws InvalidUsageException
	 *             if this list is empty.
	 * @throws NoSuchElementException
	 *             if given target is not managed by this object.
	 * 
	 * 
	 * @param target
	 *            element
	 * @param v
	 *            value to insert.
	 * @return element for v.
	 */
	CircleLinkElement<Value> insertAsNext(final CircleLinkElement<Value> target, final Value v) {
		if (isEmpty()) {
			throw new InvalidUsageException("Please use insertIfEmpty() for empty list.");
		} else {
			throwNoSuchElementExceptionIfNotContained(target);
		}

		CircleLinkElement<Value> element = target.insertAsNext(v);
		elements.add(element);

		return element;

	}

	/**
	 * 
	 * @param v
	 *            value
	 * @return
	 *         new element if this list is empty, otherwise null.
	 */
	CircleLinkElement<Value> insertIfEmpty(final Value v) {
		if (isEmpty()) {
			head = new CircleLinkElement<Value>(v);
			elements.add(head);
			return head;
		}
		return null;
	}

	/**
	 * given element {@code target} leaves from the circle.
	 * 
	 * if the target is the head element, next head depends on
	 * {@code returnPrevious}.
	 * 
	 * @throws NoSuchElementException
	 *             if the target is not managed by this object.
	 * 
	 * @param target
	 *            element
	 * @param returnPrevious
	 *            to select the return value. Also to select new head if given
	 *            target is the current head: new head is the tail (= previous
	 *            of current head) if
	 *            this boolean is true, otherwise the next of current head.
	 * @return
	 *         previous of the target if returnPrevious is true,
	 *         null if this list becomes empty after the target leaves,
	 *         otherwise next of the target.
	 */
	CircleLinkElement<Value> leaveFromCircle(final CircleLinkElement<Value> target,
			final boolean returnPrevious) {
		throwNoSuchElementExceptionIfNotContained(target);

		CircleLinkElement<Value> element = target.leaveFromCircle(returnPrevious);

		if (target == head) {
			if (size() == 1) {
				element = null;
			}

			head = element;
		}

		elements.remove(target);

		return element;

	}

	private boolean contains(final CircleLinkElement<Value> e) {
		return elements.contains(e);
	}

	public int size() {
		return elements.size();
	}

	public boolean isEmpty() {

		if (head == null && elements.isEmpty()) {
			return true;
		}

		if (head != null && !elements.isEmpty()) {
			return false;
		}

		throw new IllegalStateException("Wrong implementation");
	}

	// ==============================================================================
	// Pointer implementation
	// ==============================================================================

	private static class CircleLinkPointerImpl<Value> implements CircleLinkPointer<Value> {

		private final CircleLinkedList<Value> parent;
		private CircleLinkElement<Value> element;

		CircleLinkPointerImpl(final CircleLinkedList<Value> list, final CircleLinkElement<Value> e) {
			parent = list;
			element = e;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ouch.study.fpe.util.CircleLinkPointer#get()
		 */
		@Override
		public Value get() {
			return element.getValue();
		}

		@Override
		public Value getNextValue() {
			return element.getNext().getValue();
		}

		@Override
		public Value getPreviousValue() {
			return element.getPrevious().getValue();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ouch.study.fpe.util.CircleLinkPointer#insertAsPrevious(Value)
		 */
		@Override
		public CircleLinkPointer<Value> insertAsPrevious(final Value v) {
			return new CircleLinkPointerImpl<>(parent, parent.insertAsPrevious(element, v));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ouch.study.fpe.util.CircleLinkPointer#insertAsNext(Value)
		 */
		@Override
		public CircleLinkPointer<Value> insertAsNext(final Value v) {
			return new CircleLinkPointerImpl<>(parent, parent.insertAsNext(element, v));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ouch.study.fpe.util.CircleLinkPointer#insertIfEmpty(Value)
		 */
		@Override
		public CircleLinkPointer<Value> insertIfEmpty(final Value v) {
			return new CircleLinkPointerImpl<>(parent, parent.insertIfEmpty(v));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ouch.study.fpe.util.CircleLinkPointer#leaveFromCircle(boolean)
		 */
		@Override
		public CircleLinkPointer<Value> leaveFromCircle(final boolean shouldReturnPrevious) {
			return new CircleLinkPointerImpl<>(parent, parent.leaveFromCircle(element, shouldReturnPrevious));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ouch.study.fpe.util.CircleLinkPointer#getPrevious()
		 */
		@Override
		public CircleLinkPointer<Value> getPrevious() {
			return new CircleLinkPointerImpl<>(parent, element.getPrevious());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ouch.study.fpe.util.CircleLinkPointer#getNext()
		 */
		@Override
		public CircleLinkPointer<Value> getNext() {
			return new CircleLinkPointerImpl<>(parent, element.getNext());
		}

		@Override
		public boolean hasNext() {
			return element.getNext() != element;
		}

		@Override
		public boolean equals(final Object obj) {
			if (obj instanceof CircleLinkPointerImpl) {
				CircleLinkPointerImpl<?> other = (CircleLinkPointerImpl<?>) obj;
				return other.element == element;
			}

			return false;
		}

	}

}
