package ch.exmachina.vaadin.autoforms.containers;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.util.filter.UnsupportedFilterException;
import com.vaadin.ui.AbstractSelect;

import java.util.List;

public abstract class FilteredContainer<E> extends IndexedContainer {

	public static final String CAPTION = "caption";

	public void setOn(AbstractSelect select) {
		select.setContainerDataSource(this);
		select.setItemCaptionPropertyId(CAPTION);
		select.addContainerProperty(CAPTION, String.class, null);
	}

	public abstract String getItemCaption(E item);

	public abstract List<E> getItems(String filter);

	@Override
	public void addContainerFilter(Filter filter) throws UnsupportedFilterException {
		removeAllItems();

		String newFilterString = ((SimpleStringFilter) filter).getFilterString();

		if (newFilterString.length() > 2) {
			List<E> items = getItems(newFilterString);

			for (E item : items) {
				addItem(item);
			}
		}
	}

	@Override
	public Item addItem(Object itemId) {
		Item newItem = super.addItem(itemId);

		if (newItem != null) {
			try {
				newItem.getItemProperty(CAPTION).setValue(getItemCaption((E)itemId));
			} catch (ClassCastException e) {
				throw new RuntimeException("Wrong Item added to Form, only Item of type in generics expected");
			}
		}

		return newItem;
	}

}
