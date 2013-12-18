package ch.exmachina.vaadin.autoforms.components;

import com.vaadin.event.FieldEvents.*;
import com.vaadin.ui.TextField;

public abstract class ConstrainedTextField extends TextField implements TextChangeListener {

	private String lastValidValue;

	public ConstrainedTextField() {
		setTextChangeEventMode(TextChangeEventMode.EAGER);
		addTextChangeListener(this);
		setNullRepresentation("");
	}

	@Override
	synchronized  public void textChange(TextChangeEvent event) {
		String text = event.getText().trim();

		if (isValidText(text)) {
			lastValidValue = text;
		} else {
			setValue(lastValidValue != null ? lastValidValue : "");
			setCursorPosition(event.getCursorPosition() - 1);
		}
	}

	protected abstract boolean isValidText(String text);

}
