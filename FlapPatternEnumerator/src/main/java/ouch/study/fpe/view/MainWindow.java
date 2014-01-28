package ouch.study.fpe.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ouch.study.fpe.controller.RunEnumeration;
import ouch.study.fpe.domain.AngleUnitFlapPattern;

public class MainWindow extends JFrame {
	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(MainWindow.class);

	private final JButton executionButton = new JButton("run");
	private final PaintScreen paintScreen = new PaintScreen();

	private final JTextField divisionText = new JTextField(8);

	private final JLabel messageArea = new JLabel("message");

	public MainWindow() {

		configureViewElements();
		configureListeners();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void configureViewElements() {
		this.setSize(800, 400);

		// make a group of input
		JPanel inputArea = new JPanel();
		inputArea.setBorder(new BevelBorder(BevelBorder.LOWERED));
		inputArea.setLayout(new BoxLayout(inputArea, BoxLayout.Y_AXIS));
		inputArea.add(divisionText);
		inputArea.add(executionButton);

		SpringLayout layout = new SpringLayout();
		// example: layout.putConstraint(SpringLayout.NORTH, button1, 10,
		// SpringLayout.NORTH, p);

		// configure inputArea: put it on left top.
		layout.putConstraint(SpringLayout.WEST, inputArea, 10,
				SpringLayout.NORTH, this);

		// configure messageArea; put it under the inputArea.
		layout.putConstraint(SpringLayout.NORTH, messageArea, 10,
				SpringLayout.SOUTH, inputArea);
		// layout.putConstraint(SpringLayout.SOUTH, messageArea, -20,
		// SpringLayout.SOUTH, getContentPane());

		// configure paint screen layout: put it on right widely,
		layout.putConstraint(SpringLayout.WEST, paintScreen, 10,
				SpringLayout.EAST, inputArea);
		layout.putConstraint(SpringLayout.EAST, paintScreen, -10,
				SpringLayout.EAST, getContentPane());
		layout.putConstraint(SpringLayout.NORTH, paintScreen, 10,
				SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, paintScreen, -20,
				SpringLayout.SOUTH, getContentPane());

		this.setLayout(layout);

		Container contentPane = getContentPane();
		contentPane.add(inputArea);
		contentPane.add(messageArea);
		contentPane.add(paintScreen);

	}

	private void configureListeners() {
		executionButton.addActionListener(new OnClickExecutionButton());
		paintScreen.addMouseListener(new OnMouseClicked());
	}

	// ==========================================================================
	// View Controllers:
	// Sends input values to the controllers.
	// these class should have short methods only.
	// ==========================================================================

	private class OnClickExecutionButton implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent e) {
			try {
				RunEnumeration command = new RunEnumeration(
						Integer.parseInt(divisionText.getText()), true, true);

				Collection<AngleUnitFlapPattern> patterns = command.run();

				messageArea.setText(Integer.toString(patterns.size()));

				paintScreen.setPatterns(patterns);
				paintScreen.requestDraw(0);

				repaint();

			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(MainWindow.this, ex);
			}

		}
	}

	private class OnMouseClicked extends MouseAdapter {
		@Override
		public void mouseClicked(final MouseEvent e) {
			if (e.getX() > paintScreen.getWidth() / 2) {
				paintScreen.requestDrawNextPage();
			} else {
				paintScreen.requestDrawPreviousPage();
			}
		}
	}

	// private class OnKeyPressed extends KeyAdapter {
	// @Override
	// public void keyPressed(final KeyEvent e) {
	// int keyCode = e.getKeyCode();
	//
	// LOGGER.info("key " + keyCode + " pressed.");
	//
	// switch (keyCode) {
	// case KeyEvent.VK_LEFT:
	// case KeyEvent.VK_UP:
	//
	// paintScreen.requestDrawPreviousPage();
	// break;
	// case KeyEvent.VK_RIGHT:
	// case KeyEvent.VK_DOWN:
	// paintScreen.requestDrawNextPage();
	// break;
	// }
	// }
	// }
}
