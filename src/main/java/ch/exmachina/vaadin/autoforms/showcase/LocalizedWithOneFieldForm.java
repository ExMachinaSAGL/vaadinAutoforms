package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.*;
import com.vaadin.ui.TextField;

/**
 * @autor Marco Manzi
 */
public class LocalizedWithOneFieldForm extends AbstractFormCreator<TestModel> implements LocalizedForm{

	public LocalizedWithOneFieldForm() {
		super(new TestModel());
	}

	protected void initFields() {
		addRow(new FormField("name", TextField.class));
	}

	@Override
	public String getMessageFor(String label) {
		return "LocalizedName";
	}

	@Override
	public String getItemCaptionFor(String itemId) {
		return "LocalizedItem:" + itemId;
	}

}
