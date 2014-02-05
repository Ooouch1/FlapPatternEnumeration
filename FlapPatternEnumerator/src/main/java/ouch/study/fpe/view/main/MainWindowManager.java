package ouch.study.fpe.view.main;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ouch.study.fpe.controller.BruteForceEnumerationRunner;
import ouch.study.fpe.controller.EnumerationRunner;
import ouch.study.fpe.controller.FlapPatternsSettable;
import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.domain.LineType;
import ouch.study.fpe.view.ChangeScreenPageOnMouseClicked;
import ouch.study.fpe.view.PaintScreen;
import ouch.study.fpe.view.pieceinput.PieceEditWindowManager;
import ouch.study.fpe.view.pieceinput.PieceLineFactory;

/**
 * generates only one window per this instance.
 * 
 * @author Koji
 * 
 */
public class MainWindowManager implements FlapPatternsSettable {

	// ===============================================================================
	// Main window view
	// ===============================================================================
	private final MainWindow window = new MainWindow();

	private final MessageArea messageArea = new MessageArea();
	private final PaintScreen paintScreen = new PaintScreen(10);
	private final DivisionSizeText divisionText = new DivisionSizeText(8);

	// ----------------------------------------------------------------
	// Brute-Force mode
	private final JButton bruteForceButton = new JButton("Run");

	// ----------------------------------------------------------------
	// puzzle mode
	private final JButton openPieceEditButton = new JButton("Edit Pieces");
	private final JButton puzzleButton = new JButton("Run");
	private Collection<AngleUnitFlapPattern> pieces = new LinkedList<>();

	public MainWindowManager() {
		configureComponentActions();
	}

	private void configureComponentActions() {
		addOutputScreenActions(paintScreen);
		addEnumerationRunnerActions(bruteForceButton);
		addPieceEditOpenerActions(openPieceEditButton);

	}

	public JFrame getView() {

		window.setBruteForceExcecutionButton(bruteForceButton);
		window.setPaintScreen(paintScreen);
		window.setDivisionText(divisionText);

		window.setMessageArea(messageArea);

		window.setOpenPieceEditButton(openPieceEditButton);
		window.setPuzzleExcecutionButton(puzzleButton);

		window.configureViewElementsLayout();

		return window;
	}

	private void addOutputScreenActions(final PaintScreen screen) {
		screen.addMouseListener(new ChangeScreenPageOnMouseClicked(screen, 80));
	}

	private void addEnumerationRunnerActions(final JButton button) {
		button.addActionListener(new OnClickBruteForceButton(button));
	}

	private void addPieceEditOpenerActions(final JButton button) {
		button.addActionListener(new OnClickOpenPieceEditButton());
	}

	private Container findParentWindow(final Component c) {
		Container parent = c.getParent();

		for (int i = 0; i < 10; i++) {
			if (parent instanceof MainWindow) {
				parent = parent.getParent();
			}
		}

		return parent;
	}

	// ==========================================================================
	// Event Listeners
	// ==========================================================================

	/**
	 * Runs enumeration.
	 * 
	 * @author Koji
	 * 
	 */
	private class OnClickBruteForceButton implements ActionListener {
		private final JComponent button;

		public OnClickBruteForceButton(final JComponent button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(final ActionEvent e) {

			Container parent = findParentWindow(button);
			BruteForceEnumerationRunner runner = new BruteForceEnumerationRunner(divisionText, paintScreen);

			runEnumeration(runner, parent);
		}
	}

	private void runEnumeration(final EnumerationRunner runner, final Container parent) {

		try {

			Collection<AngleUnitFlapPattern> patterns = runner.run(toPieceLines(pieces));

			messageArea.setText(Integer.toString(patterns.size()));

			parent.repaint();

		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(parent, ex);
		}

	}

	private ArrayList<List<LineType>> toPieceLines(final Collection<AngleUnitFlapPattern> patterns) {
		ArrayList<List<LineType>> pieces = new ArrayList<>(patterns.size());

		PieceLineFactory factory = new PieceLineFactory();
		for (AngleUnitFlapPattern pattern : patterns) {
			pieces.add(factory.createPieceLines(pattern));
		}

		return pieces;
	}

	/**
	 * Open piece edit window.
	 */
	private class OnClickOpenPieceEditButton implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			PieceEditWindowManager manager = new PieceEditWindowManager(MainWindowManager.this, divisionText);
			manager.getView().setVisible(true);

		}
	}

	@Override
	public void setFlapPatterns(final Collection<AngleUnitFlapPattern> patterns) {
		pieces = patterns;
	}

}
