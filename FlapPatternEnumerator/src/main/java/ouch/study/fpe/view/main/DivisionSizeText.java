package ouch.study.fpe.view.main;

import javax.swing.JTextField;

import ouch.study.fpe.controller.IntegerGettable;

public class DivisionSizeText extends JTextField implements IntegerGettable {

	public DivisionSizeText(final int length) {
		super(length);
	}

	@Override
	public Integer getInteger() {
		return Integer.parseInt(this.getText());
	}
}
