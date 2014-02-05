package ouch.study.fpe.view.pieceinput;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ouch.study.fpe.controller.FlapPatternsSettable;
import ouch.study.fpe.controller.IntegerGettable;
import ouch.study.fpe.view.ChangeScreenPageOnMouseClicked;
import ouch.study.fpe.view.PaintScreen;

public class PieceEditWindowManager {
	private final PieceEditWindow window = new PieceEditWindow();

	private final int clickableRange = 20;

	private PieceInputController pieceInputController;

	private final PaintScreen pieceInputScreen = new PaintScreen(clickableRange);
	private final JButton applyButton = new JButton("Done");

	private int divisionSize;
	private FlapPatternsSettable patternHolder;

	public PieceEditWindowManager(final FlapPatternsSettable patternHolder, final IntegerGettable divisionSizeHolder) {
		this.patternHolder = patternHolder;
		this.divisionSize = divisionSizeHolder.getInteger();
		configureComponentActions();
	}

	private void configureComponentActions() {
		applyButton.addActionListener(new OnClickApplyButton());

		pieceInputController = new PieceInputController(divisionSize, pieceInputScreen);
		pieceInputScreen.addMouseListener(new ChangeScreenPageOnMouseClicked(pieceInputScreen, clickableRange));
	}

	public JFrame getView() {
		window.setPaintScreen(pieceInputScreen);
		window.setApplyButton(applyButton);

		window.configureViewElementsLayout();

		return window;
	}

	private class OnClickApplyButton implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			patternHolder.setFlapPatterns(pieceInputController.getPatterns());
			window.setVisible(false);
			window.dispose();
		}
	}

}
