package ouch.study.fpe.view.pieceinput;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import ouch.study.fpe.controller.FlapPatternsSettable;
import ouch.study.fpe.controller.IntegerGettable;
import ouch.study.fpe.domain.AngleUnitFlapPattern;
import ouch.study.fpe.view.ChangeScreenPageOnMouseClicked;
import ouch.study.fpe.view.PaintScreen;

public class PieceEditWindowManager {
	private PieceEditWindow window;

	private final int clickableRange = 20;

	private PieceInputController pieceInputController;

	private final PaintScreen pieceInputScreen = new PaintScreen(clickableRange);
	private final JButton applyButton = new JButton("Done");

	private final JCheckBox useDual = new JCheckBox("use dual patt.");

	private int divisionSize;
	private FlapPatternsSettable pieceHolder;

	public PieceEditWindowManager(final FlapPatternsSettable pieceHolder, final IntegerGettable divisionSizeHolder) {
		this.pieceHolder = pieceHolder;
		this.divisionSize = divisionSizeHolder.getInteger();
		configureComponentActions();
	}

	private void configureComponentActions() {
		applyButton.addActionListener(new OnClickApplyButton());

		pieceInputController = new PieceInputController(divisionSize, pieceInputScreen);
		pieceInputScreen.addMouseListener(new ChangeScreenPageOnMouseClicked(pieceInputScreen, clickableRange));
	}

	public JFrame getView() {

		if (window != null) {
			return window;
		}

		window = new PieceEditWindow();
		window.setPaintScreen(pieceInputScreen);
		window.setApplyButton(applyButton);
		window.setDualPatternCheckBox(useDual);

		window.configureViewElementsLayout();

		return window;
	}

	private class OnClickApplyButton implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			List<AngleUnitFlapPattern> patterns = pieceInputController.getPatterns();
			if (useDual.isSelected()) {
				patterns.addAll(createDuals(patterns));
			}

			pieceHolder.setFlapPatterns(patterns);
			window.setVisible(false);
			window.dispose();
		}
	}

	private Collection<AngleUnitFlapPattern> createDuals(final Collection<AngleUnitFlapPattern> patterns) {
		LinkedList<AngleUnitFlapPattern> duals = new LinkedList<>();

		for (AngleUnitFlapPattern pattern : patterns) {
			duals.add(pattern.createDual());
		}

		return duals;
	}

}
