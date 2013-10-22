package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.FormCreator;
import ch.exmachina.vaadin.autoforms.FormField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * @autor Marco Manzi
 */
public class FourFieldOnTwoRowsForm extends AbstractFormCreator<TestModel> {
	public String name;
	public String surname;
	public String description;
	public String itwork;

	public FourFieldOnTwoRowsForm() {
		super(new TestModel());
	}

	@Override
	protected void initFields() {
		addRow(new FormField("name", TextField.class), new FormField("surname", TextField.class));
		addRow(new FormField("description", TextArea.class), new FormField("itwork", CheckBox.class));

	}
}
