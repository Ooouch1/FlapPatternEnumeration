package ouch.study.fpe;

import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ouch.study.fpe.view.main.MainWindowManager;
import ouch.study.fpe.view.part.WindowManager;

@Component
public class ApplicationRunner implements CommandLineRunner {

	@Override
	public void run(final String... arg0) throws Exception {
		WindowManager windowManager = new MainWindowManager();

		JFrame window = windowManager.getView();

		Properties appInfo = getAppInfo();
		String appName = appInfo.getProperty("app.name");
		String appVersion = appInfo.getProperty("app.version");
		String title = appName + " " + appVersion;
		window.setTitle(title);

		window.setVisible(true);

	}

	private Properties getAppInfo() throws IOException {
		Properties appInfo = new Properties();

		appInfo.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("pom-info.properties"));

		return appInfo;
	}
}
