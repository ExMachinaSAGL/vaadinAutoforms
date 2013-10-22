package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.FormCreator;
import ch.exmachina.vaadin.autoforms.FormField;
import com.vaadin.ui.TextField;


/**
 * @autor Marco Manzi
 */
public class OneFieldOnFirstRowReadOnly extends AbstractFormCreator<TestModel> {

	public OneFieldOnFirstRowReadOnly() {
		super(new TestModel());
	}

	protected void initFields() {
		addRow(new FormField("name", TextField.class).setReadOnly(true));
	}


}
