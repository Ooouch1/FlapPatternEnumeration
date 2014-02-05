package ouch.study.fpe.view;

public interface ScreenWithPages {

	public abstract void requestDrawNextPage();

	public abstract void requestDrawPreviousPage();

	int getScreenWidth();

	/**
	 * Suggests drawing patterns.
	 * 
	 * @param page
	 *            indicates which sub-sequence of patterns to be drawn..
	 */
	void requestDraw(final int page);

}