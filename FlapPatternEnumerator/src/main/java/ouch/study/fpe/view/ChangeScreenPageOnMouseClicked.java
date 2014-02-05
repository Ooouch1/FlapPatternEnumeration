package ouch.study.fpe.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangeScreenPageOnMouseClicked extends MouseAdapter {

	final ScreenWithPages screen;

	final double clickableRange;

	public ChangeScreenPageOnMouseClicked(final ScreenWithPages obj, final double clickableRange) {
		screen = obj;
		this.clickableRange = clickableRange;
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		if (isInLeftRange(e.getX())) {
			screen.requestDrawPreviousPage();
		} else if (isInRightRange(e.getX())) {
			screen.requestDrawNextPage();
		}
	}

	private boolean isInLeftRange(final int x) {
		if (x < clickableRange) {
			return true;
		}

		return false;
	}

	private boolean isInRightRange(final int x) {
		if (x > screen.getScreenWidth() - clickableRange) {
			return true;
		}

		return false;
	}

}
