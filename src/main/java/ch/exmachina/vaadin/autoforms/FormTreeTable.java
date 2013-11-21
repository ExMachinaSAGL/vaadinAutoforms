package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.TreeTable;

import java.util.*;

public class FormTreeTable extends AbstractFormTable {

	private HashMap<Object, List<Object>> parentToChildrenFlightServices = new HashMap<Object, List<Object>>();

	public FormTreeTable(String fieldName, TableColumn... columns) {
		super(TreeTable.class, fieldName, columns);

		getTable().setEditable(false);
	}

	public void addRow(final Object itemId, Object... colValues) {
		super.addRow(itemId, colValues);

		parentToChildrenFlightServices.put(itemId, new ArrayList<Object>());
	}

	public void addChildRow(Object parentItemId, final Object itemId, Object... colValues) {
		assertColumnsCount(colValues);

		addNewItemWithValues(itemId, colValues);
		parentToChildrenFlightServices.get(parentItemId).add(itemId);

		getTable().setChildrenAllowed(itemId, false);
		getTable().setParent(itemId, parentItemId);
	}

	public boolean removeItemAndChildren(Object itemId) {
		for (Object child : parentToChildrenFlightServices.remove(itemId)) {
			getTable().removeItem(child);
		}

		return getTable().removeItem(itemId);
	}

	public TreeTable getTable() {
		return (TreeTable) field;
	}
}
