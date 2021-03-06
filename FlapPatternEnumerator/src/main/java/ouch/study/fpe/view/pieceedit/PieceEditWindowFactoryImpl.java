package ouch.study.fpe.view.pieceedit;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import ouch.study.fpe.controller.FlapPatternsSettable;
import ouch.study.fpe.controller.IntegerGettable;
import ouch.study.fpe.view.main.PieceEditWindowFactory;

@Component
public class PieceEditWindowFactoryImpl implements PieceEditWindowFactory {

	@Override
	public JFrame createWindow(final FlapPatternsSettable patternDestination, final IntegerGettable divisionSize) {
		PieceEditWindowManager manager = new PieceEditWindowManager(patternDestination, divisionSize);
		JFrame view = manager.getView();

		return view;
	}

}
