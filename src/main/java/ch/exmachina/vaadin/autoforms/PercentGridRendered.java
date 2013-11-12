package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

import java.util.LinkedList;

/**
 * @autor Marco Manzi
 */
public class PercentGridRendered implements GridRender {
	private GridLayout mainLayout;
	private int LABEL_CONSTANT_COLS = 14;

	private PercentGridDesigner designer;

	public PercentGridRendered() {
		designer = new PercentGridDesigner();
	}

	@Override
	public GridLayout render(FormCreator formCreator) {
		LinkedList<LinkedList<FormComponent>> components = formCreator.components;
		LinkedList<LinkedList<FormComponent>> designedComponents = designer.addPercents(components);
		mainLayout = new GridLayout(100, designedComponents.size());
		int rowIndex = 0;
		for (LinkedList<FormComponent> rowComponents: components) {
			addElementsToRow(rowComponents, rowIndex++);
		}
		return mainLayout;
	}

	private void addElementsToRow(LinkedList<FormComponent> rowComponents, int rowIndex) {
		int colIndex = 0;
		for (FormComponent inRow: rowComponents) {
			switch (inRow.getType()) {
				case FIELD: addFieldToRow((FormField) inRow, colIndex, rowIndex); break;
				case BUTTON: addButtonToRow((FormButton)inRow, colIndex, rowIndex); break;
			}
			colIndex += inRow.getWidthPercent() + inRow.getMarginLeftPercent();
		}
	}

	private void addButtonToRow(FormButton inRow, int colIndex, int rowIndex) {
		colIndex += inRow.getMarginLeftPercent();
		int fieldEndCols = colIndex + inRow.getWidthPercent() - 1;
		mainLayout.addComponent(inRow.getButton(), colIndex, rowIndex, fieldEndCols, rowIndex);
		mainLayout.setColumnExpandRatio(colIndex, 1);
	}

	private void addFieldToRow(FormField inRow, int colIndex, int rowIndex) {
		colIndex += inRow.getMarginLeftPercent();
		mainLayout.addComponent(inRow.getFieldLabel(), colIndex, rowIndex, colIndex + LABEL_CONSTANT_COLS, rowIndex);
		mainLayout.setComponentAlignment(inRow.getFieldLabel(), Alignment.MIDDLE_CENTER);
		int fieldEndCols = colIndex + inRow.getWidthPercent() - 1;
		int fieldMargin = colIndex + LABEL_CONSTANT_COLS + 1;
		mainLayout.addComponent(inRow.getField(), fieldMargin, rowIndex, fieldEndCols, rowIndex);
		mainLayout.setColumnExpandRatio(colIndex, 1);
	}


}


