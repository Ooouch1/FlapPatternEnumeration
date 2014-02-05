package ouch.study.fpe.view.main;

import javax.swing.JLabel;

public class MessageArea extends JLabel implements MessageSettable {

	@Override
	public void setMessage(final Object message) {
		this.setText(message.toString());
	}
}
