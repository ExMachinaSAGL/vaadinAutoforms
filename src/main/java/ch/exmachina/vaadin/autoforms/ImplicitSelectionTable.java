package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.Table;

public class ImplicitSelectionTable extends Table {
	@Override
	public Object getValue() {
		setValue(getItemIds());
		return super.getValue();
	}

	public boolean isRequired() {
		return false;
	}
}
