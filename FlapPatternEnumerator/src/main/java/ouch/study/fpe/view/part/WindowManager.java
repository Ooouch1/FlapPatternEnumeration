package ouch.study.fpe.view.part;

import javax.swing.JFrame;

/**
 * generates only one window per this instance.
 * 
 * @author Koji
 * 
 */

public abstract class WindowManager {

	protected JFrame window;

	public final JFrame getView() {

		configureComponentActions();

		if (window == null) {
			window = createView();
		}

		return window;
	}

	/**
	 * configure event listeners for each component.
	 */
	protected abstract void configureComponentActions();

	/**
	 * 
	 * @return a window
	 */
	protected abstract JFrame createView();

}