package ouch.study.fpe;

import javax.swing.JFrame;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ouch.study.fpe.view.main.MainWindowManager;

@Component
public class ApplicationRunner implements CommandLineRunner {

	@Override
	public void run(final String... arg0) throws Exception {
		MainWindowManager windowManager = new MainWindowManager();
		JFrame window = windowManager.getView();
		window.setVisible(true);

	}
}
