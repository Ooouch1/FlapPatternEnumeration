package ouch.study.fpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@ComponentScan(
		basePackages = {
				"ouch.study.fpe"
		})
@EnableAutoConfiguration
@EnableSpringConfigured
public class FlapPatternEnumerationMain {

	public static void main(final String[] args) {
		SpringApplication app = new SpringApplication(
				FlapPatternEnumerationMain.class);

		app.setShowBanner(false);
		// app.setLogStartupInfo(false);

		app.setHeadless(false);
		app.run(args);

	}
}
