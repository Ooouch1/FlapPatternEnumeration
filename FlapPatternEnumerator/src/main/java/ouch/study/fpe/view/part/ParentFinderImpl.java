package ouch.study.fpe.view.part;

import java.awt.Component;
import java.awt.Container;

import ouch.study.fpe.view.main.ParentFinder;

@org.springframework.stereotype.Component
public class ParentFinderImpl implements ParentFinder {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ouch.study.fpe.view.main.ParentWindowFinder#findParentWindow(java.awt
	 * .Component)
	 */
	@Override
	public Container find(final Class<? extends Object> parentClass, final Component c) {
		Container parent = c.getParent();

		for (int i = 0; i < 10; i++) {
			if (!parentClass.isInstance(parent)) {
				parent = parent.getParent();
			}
		}

		return parent;
	}

}
