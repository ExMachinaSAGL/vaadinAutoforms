package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.FormField;
import com.vaadin.ui.TextField;

/**
 * @autor Marco Manzi
 */
public class TwoFieldOnSameRow extends AbstractFormCreator<TestModel> {
	public TwoFieldOnSameRow() {
		super(new TestModel());
	}

	@Override
	protected void initFields() {
		addRow(new FormField("name", TextField.class), new FormField("surname", TextField.class));
	}
}
