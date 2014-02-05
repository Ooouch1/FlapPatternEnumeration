package ouch.study.fpe;

import javax.swing.JFrame;

import ouch.study.fpe.view.main.MainWindowManager;

public class FlapPatternEnumerationMain {

	public static void main(final String[] args) {

		MainWindowManager factory = new MainWindowManager();
		JFrame window = factory.getView();

		window.setVisible(true);
	}
}
