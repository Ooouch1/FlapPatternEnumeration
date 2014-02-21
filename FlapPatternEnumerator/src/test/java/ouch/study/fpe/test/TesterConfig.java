package ouch.study.fpe.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@ComponentScan(
		basePackages = { "ouch.study.fpe.*" })
@EnableAutoConfiguration
@EnableSpringConfigured
public class TesterConfig {

}
