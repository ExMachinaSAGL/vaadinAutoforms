package ch.exmachina.vaadin.autoforms;

import com.vaadin.data.Container;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractSelect;

/**
 * @autor Marco Manzi
 */
public class FormFieldBuilder {
	private FormField formField;

	public FormFieldBuilder(String fieldName, AbstractField<?> field) {
		formField = new FormField(fieldName, field);
	}

	public FormFieldBuilder(String fieldName, Class<? extends AbstractField<?>> fieldClass) {
		formField = new FormField(fieldName, fieldClass);
	}

	public FormFieldBuilder(String fieldName, Class<? extends AbstractSelect> field, Container container) {
		formField = new FormField(fieldName, field, container);
	}

	public FormFieldBuilder(String fieldName, AbstractSelect field, Container container) {
		formField = new FormField(fieldName, field, container);
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
