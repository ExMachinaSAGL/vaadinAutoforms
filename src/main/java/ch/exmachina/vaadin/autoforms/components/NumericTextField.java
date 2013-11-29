package ch.exmachina.vaadin.autoforms.components;

import org.apache.commons.lang3.StringUtils;

public class NumericTextField extends ConstrainedTextField {

	public NumericTextField(String value) {
		super();

		if (!isValidText(value)) {
			throw new IllegalArgumentException(value + " is not a valid numeric value");
		}
		setValue(value);
	}

	public NumericTextField() {
		super();
	}

	@Override
	protected boolean isValidText(String text) {
		return StringUtils.isNumeric(text) || StringUtils.isBlank(text);
	}
}
