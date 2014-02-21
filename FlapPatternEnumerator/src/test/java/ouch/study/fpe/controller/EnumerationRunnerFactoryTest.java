package ouch.study.fpe.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ouch.study.fpe.test.TesterConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = TesterConfig.class,
		loader = SpringApplicationContextLoader.class)
public class EnumerationRunnerFactoryTest {

	// @Autowired
	private EnumerationRunnerFactory factory;
	@Mock
	private IntegerGettable mockDivisionSize;
	@Mock
	private FlapPatternsSettable mockPatternDestination;

	@Before
	public void initialize() {
		MockitoAnnotations.initMocks(this);

		factory = new EnumerationRunnerFactory();

		mockDivisionSize = mock(IntegerGettable.class);
		mockPatternDestination = mock(FlapPatternsSettable.class);
	}

	@Test
	public void testCreateBruteForce() {
		when(mockDivisionSize.getInteger()).thenReturn(4);

		assertNotNull(factory.createBruteForceRunner(mockDivisionSize, mockPatternDestination));
	}
}
