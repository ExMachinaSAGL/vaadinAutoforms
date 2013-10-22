package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.Label;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @autor Marco Manzi
 */
public class GridCellInfoBuilder {
	private List<FormComponent> formComponents;
	private List<GridCellInfo> gridInfoCells;

	public GridCellInfoBuilder(List<FormComponent> formComponents) {
		this.formComponents = formComponents;
		evaluateGridInfoCells();
	}

	private void evaluateGridInfoCells() {
		gridInfoCells = new LinkedList<GridCellInfo>();
		GridCellInfo cellInfo = new GridCellInfo();
		for (FormComponent comp : formComponents) {
			if (compMakeNewGridInfo(comp) &&
					cellInfo.numberOfComponentsInCell() > 0) {
				gridInfoCells.add(cellInfo);
				cellInfo = new GridCellInfo();
			}
			cellInfo.addComponent(comp);
		}
		gridInfoCells.add(cellInfo);
	}

	private boolean compMakeNewGridInfo(FormComponent formComponent) {
		return (formComponent instanceof FormField &&
				StringUtils.isNotEmpty(((FormField) formComponent).getFieldLabel().getValue()));
	}

	public List<GridCellInfo> build() {
		return gridInfoCells;
	}

	public class GridCellInfo {
		private LinkedList<FormComponent> formComponents;

		private GridCellInfo() {
			formComponents = new LinkedList<FormComponent>();
		}

		public void addComponent(FormComponent formComponent) {
			// If the first form compontent is not a label or the label is empty throw an exception because
			// First element has to have label by design
			if (formComponents.size() == 0 &&
					(!(formComponent instanceof FormField) ||
							(StringUtils.isEmpty(((FormField) formComponent).getFieldLabel().getValue())))) {
				throw new IllegalArgumentException("First element in a grid cell of form has to have the label");
			}
			formComponents.add(formComponent);
		}

		public int numberOfComponentsInCell() {
			return formComponents.size();
		}

		public Label getLabel() {
			return ((FormField) formComponents.get(0)).getFieldLabel();
		}

	}
}
