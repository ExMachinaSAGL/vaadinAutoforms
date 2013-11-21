package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.Table;

public class FormTable extends AbstractFormTable {

	public FormTable(String fieldName, TableColumn... columns) {
		super(Table.class, fieldName, columns);
	}
}
