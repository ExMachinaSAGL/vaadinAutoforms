package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.*;
import com.vaadin.ui.*;


/**
 * @autor Marco Manzi
 */
public class OneFieldWithOkAndCancelButton extends AbstractFormCreator<TestModel> {

	public OneFieldWithOkAndCancelButton() {
		super(new TestModel());
	}

	protected void initFields() {
		addRow(new FormField("name", TextField.class));
		addButton(new FormButton("Ok", new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {

			}
		}));
		addButton(new FormButton("Cancel", new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				//To change body of implemented methods use File | Settings | File Templates.
			}
		}));
	}

}
