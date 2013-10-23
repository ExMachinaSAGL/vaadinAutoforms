package ch.exmachina.vaadin.autoforms.containers;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.AbstractSelect;

public abstract class LocalizedContainer extends IndexedContainer {

	public static final String CAPTION = "caption";

	protected abstract String getItemCaption(Object item);

	public void setOn(AbstractSelect select) {
		select.setContainerDataSource(this);
		select.setItemCaptionPropertyId(CAPTION);
		select.addContainerProperty(CAPTION, String.class, null);
	}

	@Override
	public Item addItem(Object itemId) {
		Item newItem = super.addItem(itemId);

		if (newItem != null) {
			try {
				newItem.getItemProperty(CAPTION).setValue(getItemCaption(itemId));
			} catch (ClassCastException e) {
				throw new RuntimeException("Wrong Item added to Form, only Item of type in generics expected");
			}
		}

		return newItem;
	}

}
