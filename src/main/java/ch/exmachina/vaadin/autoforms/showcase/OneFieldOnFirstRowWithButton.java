package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.FormButton;
import ch.exmachina.vaadin.autoforms.FormField;
import com.vaadin.ui.TextField;


/**
 * @autor Marco Manzi
 */
public class OneFieldOnFirstRowWithButton extends AbstractFormCreator<TestModel> {

	public OneFieldOnFirstRowWithButton() {
		super(new TestModel());
	}

	@Override
	protected void initFields() {
		addRow(new FormField("name", TextField.class), new FormButton("save", null));
	}


}
