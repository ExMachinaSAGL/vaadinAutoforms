package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.*;
import com.vaadin.ui.TextField;

/**
 * @autor Marco Manzi
 */
public class PercentFormTest extends FormCreator<TestModel> {

	public PercentFormTest () {
		super(new PercentGridRendered());
	}

	@Override
	protected void beforeRendering() {}

	@Override
	protected void initFields() {
		FormField test = new FormFieldBuilder("surname", TextField.class).build();
	  	addRow(new FormFieldBuilder("name", TextField.class).width(50).build(),
				test,
				new FormFieldBuilder("itWork", TextField.class).build());
		addRow(new FormButton("Test of button with long string", null));
	}
}
