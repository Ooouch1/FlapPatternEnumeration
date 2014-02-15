package ouch.study.fpe.view;

import java.awt.Component;
import java.awt.Container;

import ouch.study.fpe.view.main.MainWindow;

public class WindowParentFinder {
	public Container findParentWindow(final Component c) {
		Container parent = c.getParent();

		for (int i = 0; i < 10; i++) {
			if (parent instanceof MainWindow) {
				parent = parent.getParent();
			}
		}

		return parent;
	}

}
