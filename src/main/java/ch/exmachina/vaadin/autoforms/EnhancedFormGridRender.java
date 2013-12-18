package ch.exmachina.vaadin.autoforms;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.GridLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * @autor Marco Manzi
 */
public class EnhancedFormGridRender {
	private GridLayout mainLayout;

	public EnhancedFormGridRender() {}

	public EnhancedFormGridRender(LinkedList<FormComponent> formComponents) {
		initMainLayout(formComponents);
		placeFormComponentsInGrid(formComponents);
	}


	int numOfGridColumn(List<FormComponent> formFields) {
		GridCellInfoBuilder builder = new GridCellInfoBuilder(formFields);
		return 10 * builder.build().size();
	}

	private void placeFormComponentsInGrid(LinkedList<FormComponent> formComponents) {
		List<GridCellInfoBuilder.GridCellInfo> cellInfos = new GridCellInfoBuilder(formComponents).build();
		for (GridCellInfoBuilder.GridCellInfo cellInfo : cellInfos) {
			mainLayout.addComponent(cellInfo.getLabel(), 0, 0, 2, 0);
		}
	}

	/**
	 * It create the grid layout, size full if to let the form enlarge on window change
	 */
	private void initMainLayout(List<FormComponent> cellInfos) {
		mainLayout = new GridLayout(numOfGridColumn(cellInfos), 1);
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSizeFull();
		mainLayout.setHeight(-1, Sizeable.Unit.PIXELS);
	}

	GridLayout render() {
		return mainLayout;
	}
}
