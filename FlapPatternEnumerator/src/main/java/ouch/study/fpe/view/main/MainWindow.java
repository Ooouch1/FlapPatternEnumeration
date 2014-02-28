package ouch.study.fpe.view.main;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;

import ouch.study.fpe.view.part.PaintScreen;

@Configurable
public class MainWindow extends JFrame {
	/** Logger. */
	private static final Logger LOGGER = LogManager.getLogger(MainWindow.class);

	// =================================================================
	// Action Triggers, Data Sources and Action Reactors
	// =================================================================

	private PaintScreen paintScreen;

	private DivisionSizeText divisionText;

	private JLabel messageArea;

	private JButton brutForceButton;
	private JButton puzzleButton;
	private JButton openPieceEditButton;

	// =================================================================
	// Additional View components
	// =================================================================
	// @Value("${app.name}")
	// private String appName;

	public MainWindow() {

		// configureViewElements();
		// configureListeners();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	void configureViewElementsLayout() {
		this.setSize(800, 400);

		// make a group of input
		JPanel divisionSizeArea = createDivisionSizeInputArea();
		JTabbedPane inputArea = createInputTabs();

		// JPanel leftPanel = createBasicInputArea();
		// leftPanel.add(divisionSizeArea);
		// leftPanel.add(inputArea);

		SpringLayout layout = new SpringLayout();
		// example: layout.putConstraint(SpringLayout.NORTH, button1, 10,
		// SpringLayout.NORTH, p);

		// configure inputArea: put it on left top.

		layout.putConstraint(SpringLayout.WEST, divisionSizeArea, 10,
				SpringLayout.WEST, getContentPane());
		layout.putConstraint(SpringLayout.NORTH, divisionSizeArea, 10,
				SpringLayout.NORTH, getContentPane());

		layout.putConstraint(SpringLayout.WEST, inputArea, 10,
				SpringLayout.WEST, getContentPane());
		layout.putConstraint(SpringLayout.NORTH, inputArea, 10,
				SpringLayout.SOUTH, divisionSizeArea);
		layout.putConstraint(SpringLayout.SOUTH, inputArea, 10,
				SpringLayout.NORTH, messageArea);

		// configure messageArea; put it under the inputArea.
		layout.putConstraint(SpringLayout.NORTH, messageArea, -50,
				SpringLayout.SOUTH, getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, messageArea, -20,
				SpringLayout.SOUTH, getContentPane());

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
		contentPane.add(divisionSizeArea);
		contentPane.add(inputArea);
		contentPane.add(messageArea);
		contentPane.add(paintScreen);

	}

	private JPanel createDivisionSizeInputArea() {
		JPanel area = createBasicInputArea();
		area.add(divisionText);

		return area;
	}

	private JTabbedPane createInputTabs() {
		JTabbedPane tabManager = new JTabbedPane();

		// make a group of input

		tabManager.addTab("brute force", createBruteForceInputArea());

		tabManager.addTab("puzzle", createPuzzleInputArea());
		return tabManager;
	}

	private JPanel createBruteForceInputArea() {
		JPanel bruteForceInputs = createBasicInputArea();
		bruteForceInputs.add(new JLabel("may be slow."));
		bruteForceInputs.add(brutForceButton);

		return bruteForceInputs;

	}

	private JPanel createPuzzleInputArea() {
		JPanel puzzleInputs = createBasicInputArea();
		puzzleInputs.add(openPieceEditButton);
		puzzleInputs.add(puzzleButton);

		return puzzleInputs;

	}

	private JPanel createBasicInputArea() {
		JPanel area = new JPanel();
		area.setBorder(new BevelBorder(BevelBorder.LOWERED));
		area.setLayout(new BoxLayout(area, BoxLayout.Y_AXIS));

		return area;
	}

	void setBruteForceExcecutionButton(final JButton executionButton) {
		this.brutForceButton = executionButton;
	}

	void setPuzzleExcecutionButton(final JButton executionButton) {
		this.puzzleButton = executionButton;
	}

	void setPaintScreen(final PaintScreen paintScreen) {
		this.paintScreen = paintScreen;
	}

	void setDivisionText(final DivisionSizeText divisionText) {
		this.divisionText = divisionText;
	}

	void setMessageArea(final JLabel messageArea) {
		this.messageArea = messageArea;
	}

	void setOpenPieceEditButton(final JButton b) {
		this.openPieceEditButton = b;
	}

	// ==========================================================================
	// View Controllers:
	// Sends input values to the controllers.
	// these class should have short methods only.
	// ==========================================================================

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
