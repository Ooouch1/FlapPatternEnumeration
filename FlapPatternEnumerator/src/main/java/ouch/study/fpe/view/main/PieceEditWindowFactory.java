package ouch.study.fpe.view.main;

import javax.swing.JFrame;

import ouch.study.fpe.controller.FlapPatternsSettable;
import ouch.study.fpe.controller.IntegerGettable;

public interface PieceEditWindowFactory {
	JFrame createWindow(FlapPatternsSettable patternDestination, IntegerGettable divisionSize);
}
