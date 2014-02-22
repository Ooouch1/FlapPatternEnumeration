package ouch.study.fpe.util;

public interface CircleLinkPointer<Value> {

	public abstract Value get();

	public abstract Value getNextValue();

	public abstract Value getPreviousValue();

	/**
	 * Inserts given value as the previous element of this pointer.
	 * 
	 * If this pointer is the head, the new element will be the tail of the
	 * refered list.
	 * 
	 * @throws InvalidUsageException
	 *             if this list is empty.
	 * 
	 * @param v
	 *            value to insert.
	 * 
	 * @return pointer for v.
	 */
	public abstract CircleLinkPointer<Value> insertAsPrevious(Value v);

	/**
	 * @throws InvalidUsageException
	 *             if this list is empty.
	 * 
	 * @param v
	 *            value to insert.
	 * @return pointer for v.
	 */
	public abstract CircleLinkPointer<Value> insertAsNext(Value v);

	/**
	 * 
	 * @param v
	 *            value
	 * @return
	 *         pointer for the new element if this list is empty, otherwise
	 *         null.
	 */
	public abstract CircleLinkPointer<Value> insertIfEmpty(Value v);

	/**
	 * the element refered by this pointer leaves from the circle.
	 * 
	 * if this pointer is the head, next head depends on {@code returnPrevious}.
	 * 
	 * @param returnPrevious
	 *            to select the return value. Also to select new head if this
	 *            pointer is the current head: new head is the tail (= previous
	 *            of current head) if
	 *            this boolean is true, otherwise the next of current head.
	 * @return
	 *         previous of the target if returnPrevious is true,
	 *         null if the refered list becomes empty after the element leaves,
	 *         otherwise next of the element.
	 */
	public abstract CircleLinkPointer<Value> leaveFromCircle(boolean shouldReturnPrevious);

	public abstract CircleLinkPointer<Value> getPrevious();

	public abstract CircleLinkPointer<Value> getNext();

	boolean hasNext();

}