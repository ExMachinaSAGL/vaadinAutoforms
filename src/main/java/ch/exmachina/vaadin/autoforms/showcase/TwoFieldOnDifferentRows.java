package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.FormCreator;
import ch.exmachina.vaadin.autoforms.FormField;
import com.vaadin.ui.TextField;


/**
 * @autor Marco Manzi
 */
public class TwoFieldOnDifferentRows extends AbstractFormCreator<TestModel> {

	public TwoFieldOnDifferentRows() {
		super(new TestModel());
	}

	protected void initFields() {
		addRow(new FormField("name", TextField.class));
		addRow(new FormField("surname", TextField.class));
	}

}
