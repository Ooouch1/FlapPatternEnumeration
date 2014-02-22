package ouch.study.fpe.util;

public class CircleLinkElement<Value> {

	private Value value;

	private CircleLinkElement<Value> prev;
	private CircleLinkElement<Value> next;

	public CircleLinkElement(final Value v) {
		setValue(v);
		prev = this;
		next = this;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(final Value value) {
		this.value = value;
	}

	CircleLinkElement<Value> insertAsPrevious(final Value v) {
		CircleLinkElement<Value> element = new CircleLinkElement<Value>(v);

		// set new relation from element to others.
		// ...e....
		// ../|....
		// .p-t-n
		element.prev = prev;
		element.next = this;

		// set reverse side relation.
		// ...e....
		// ../|....
		// .p.t-n
		prev.next = element;
		prev = element;

		return element;
	}

	CircleLinkElement<Value> insertAsNext(final Value v) {
		CircleLinkElement<Value> element = new CircleLinkElement<Value>(v);

		// set new relation from element to others.
		// ...e....
		// ...|\...
		// .p-t-n
		element.next = next;
		element.prev = this;

		// set reverse side relation.
		// ...e....
		// ...|\...
		// .p-t.n
		next.prev = element;
		next = element;

		return element;
	}

	CircleLinkElement<Value> leaveFromCircle(final boolean shouldReturnPrevious) {
		prev.next = next;
		next.prev = prev;

		if (shouldReturnPrevious) {
			return prev;
		}

		return next;
	}

	public CircleLinkElement<Value> getPrevious() {
		return prev;
	}

	public CircleLinkElement<Value> getNext() {
		return next;
	}

}
