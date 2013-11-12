package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.AbstractField;

/**
 * @autor Marco Manzi
 */
public class FormFieldBuilder {
	private FormField formField;

	public FormFieldBuilder(String fieldName, Class<? extends AbstractField> fieldClass) {
		formField = new FormField(fieldName, fieldClass);
	}

	public FormFieldBuilder width(int width) {
		formField.setWidthPercent(width);
		return this;
	}

	public FormFieldBuilder marginLeft(int marginLeft) {
		formField.setMarginLeftPercent(marginLeft);
		return this;
	}

	public FormFieldBuilder labelPosition(FormField.LABEL_POSITION labelPosition) {
		formField.setLabelPosition(labelPosition);
		return this;
	}

	public FormField build() {
		return formField;
	}
}
