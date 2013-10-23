package ch.exmachina.vaadin.autoforms.containers;

import com.vaadin.data.util.filter.*;

import java.util.List;

public abstract class FilteredContainer<E> extends LocalizedContainer {

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

}
