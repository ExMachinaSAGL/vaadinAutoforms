package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.FormField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * @autor Marco Manzi
 */
public class ThreeFieldsTwoRowsWithTwoOnOneRowForm extends AbstractFormCreator<TestModel> {
	protected ThreeFieldsTwoRowsWithTwoOnOneRowForm() {
		super(new TestModel());
	}

	@Override
	protected void initFields() {
		addRow(new FormField("name", TextField.class), new FormField("surname", TextField.class));
		addRow(new FormField("description", TextArea.class));

	}
}
