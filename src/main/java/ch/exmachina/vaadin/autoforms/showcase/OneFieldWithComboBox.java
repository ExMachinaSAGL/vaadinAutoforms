package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.FormField;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.ComboBox;


/**
 * @autor Marco Manzi
 */
public class OneFieldWithComboBox extends AbstractFormCreator<TestModel> {

	public OneFieldWithComboBox() {
		super(new TestModel());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void initFields() {
		Container datasource = new IndexedContainer();
		datasource.addContainerProperty("id", Integer.class, 0);
		Item first = datasource.addItem("FirstSelection");
		first.getItemProperty("id").setValue(1);
		Item second =  datasource.addItem("SecondSelection");
		second.getItemProperty("id").setValue(2);

		addRow(new FormField("multichoice", ComboBox.class, datasource));
	}

}
