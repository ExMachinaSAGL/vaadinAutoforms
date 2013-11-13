package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.FormCreator;
import ch.exmachina.vaadin.autoforms.FormField;
import ch.exmachina.vaadin.autoforms.FormFieldBuilder;
import ch.exmachina.vaadin.autoforms.PercentGridRendered;
import com.vaadin.ui.TextField;

/**
 * @autor Marco Manzi
 */
public class PercentFormTest extends FormCreator<TestModel> {

	public PercentFormTest () {
		super(new PercentGridRendered());
	}

	@Override
	protected void beforeRendering() {


	}

	@Override
	protected void initFields() {
		FormField test = new FormFieldBuilder("surname", TextField.class).width(5).build();
	  	addRow(new FormFieldBuilder("name", TextField.class).width(2).build(),
				test,
		new FormFieldBuilder("itWork", TextField.class).width(3).build()
		);
	}
}
