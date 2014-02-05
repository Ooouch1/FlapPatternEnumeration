package ouch.study.fpe.domain.puzzle;

import java.util.ArrayList;
import java.util.List;

import ouch.study.fpe.domain.LineType;

class Piece {
	private List<LineType> lines;

	public Piece(final List<LineType> l) {
		lines = new ArrayList<>(l);
	}

	public LineType getLineAt(final int index) {
		return lines.get(index);
	}

	public LineType getLast() {
		return lines.get(size() - 1);
	}

	public int size() {
		return lines.size();
	}

	@Override
	public String toString() {
		return lines.toString();
	}
}