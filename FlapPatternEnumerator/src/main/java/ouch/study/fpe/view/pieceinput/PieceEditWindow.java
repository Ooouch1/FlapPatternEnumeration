package ouch.study.fpe.view.pieceinput;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

import ouch.study.fpe.view.PaintScreen;

public class PieceEditWindow extends JFrame {

	private PaintScreen paintScreen;
	private JButton applyButton;

	void configureViewElementsLayout() {
		this.setSize(400, 300);

		SpringLayout layout = new SpringLayout();
		// example: layout.putConstraint(SpringLayout.NORTH, button1, 10,
		// SpringLayout.NORTH, p);

		layout.putConstraint(SpringLayout.WEST, applyButton, 10,
				SpringLayout.WEST, getContentPane());

		// configure paint screen layout: put it on right widely,
		layout.putConstraint(SpringLayout.WEST, paintScreen, 10,
				SpringLayout.EAST, applyButton);
		layout.putConstraint(SpringLayout.EAST, paintScreen, -10,
				SpringLayout.EAST, getContentPane());
		layout.putConstraint(SpringLayout.NORTH, paintScreen, 10,
				SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, paintScreen, -20,
				SpringLayout.SOUTH, getContentPane());

		this.setLayout(layout);

		Container contentPane = getContentPane();
		contentPane.add(paintScreen);
		contentPane.add(applyButton);

	}

	void setPaintScreen(final PaintScreen p) {
		paintScreen = p;
	}

	void setApplyButton(final JButton b) {
		applyButton = b;
	}
}
