package ch.exmachina.vaadin.autoforms;

import com.sun.tools.javac.util.List;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.LinkedList;
import java.util.Set;

/**
 * @autor Marco Manzi
 */
public class PercentGridRendered implements GridRender {
	private GridLayout mainLayout;
	static int GRID_COL_WIDTH = 100;
	private PercentGridDesigner designer;
	private FormCreator formCreator;
	private Set<Integer> colsWithLabel;

	public PercentGridRendered() {
		this(GRID_COL_WIDTH);
	}

	/**
	 * The parameter in input will decide the max number of rows for grid,
	 * the sum of all components widths can't be greater than this.
	 * Reduce this parameter to reduce grid min Width when resizing the window
	 * @param numberOfMaxPercent
	 */
	public PercentGridRendered(int numberOfMaxPercent) {
		GRID_COL_WIDTH = numberOfMaxPercent;
		designer = new PercentGridDesigner();
	}

	@Override
	public GridLayout render(FormCreator formCreator) {
		this.formCreator = formCreator;
		LinkedList<LinkedList<FormComponent>> components = formCreator.components;
		LinkedList<LinkedList<FormComponent>> designedComponents = designer.addPercents(components);
		initMainLayout(designedComponents);
		int rowIndex = 0;
		for (LinkedList<FormComponent> rowComponents: components) {
			addElementsToRow(rowComponents, rowIndex++);
		}
		return mainLayout;
	}

	private void initMainLayout(LinkedList<LinkedList<FormComponent>> designedComponents) {
		mainLayout = new GridLayout(GRID_COL_WIDTH, designedComponents.size());
		mainLayout.setSpacing(true);
		mainLayout.setSizeFull();
		mainLayout.setHeight(-1, Sizeable.Unit.PIXELS);
	}

	private void addElementsToRow(LinkedList<FormComponent> rowComponents, int rowIndex) {
		int colIndex = 0;
		for (FormComponent inRow: rowComponents) {
			inRow.setupForForm(formCreator);
			switch (inRow.getType()) {
				case LABEL: addLabelToRow((FormLabel) inRow, colIndex, rowIndex); break;
				case FIELD: addFieldToRow((FormField) inRow, colIndex, rowIndex); break;
				case BUTTON: addButtonToRow((FormButton)inRow, colIndex, rowIndex); break;
			}
			colIndex += inRow.getWidthPercent() + inRow.getMarginLeftPercent();
		}
	}

	private void addLabelToRow(FormLabel inRow, int colIndex, int rowIndex) {
		colIndex += inRow.getMarginLeftPercent();
		mainLayout.addComponent(inRow.getLabel(), colIndex, rowIndex, colIndex +inRow.getWidthPercent() - 1, rowIndex);
		mainLayout.setComponentAlignment(inRow.getLabel(), Alignment.MIDDLE_RIGHT);
	}

	private void addButtonToRow(FormButton inRow, int colIndex, int rowIndex) {
		colIndex += inRow.getMarginLeftPercent();
		int fieldEndCols = colIndex + inRow.getWidthPercent() - 1;
		HorizontalLayout buttonContainer = new HorizontalLayout();
		buttonContainer.addComponent(inRow.getButton());
		mainLayout.addComponent(inRow.getButton(), colIndex, rowIndex, fieldEndCols, rowIndex);
	}

	private void addFieldToRow(FormField inRow, int colIndex, int rowIndex) {
		switch (inRow.getLabelPosition()) {
			case MIDDLE_LEFT: addFieldToRowWithLabelOnLeft(inRow, colIndex, rowIndex); break;
			case TOP_LEFT: addFieldToRowWithTop(inRow, colIndex, rowIndex); break;
		}
	}

	private void addFieldToRowWithLabelOnLeft(FormField inRow, int colIndex, int rowIndex) {
		colIndex += inRow.getMarginLeftPercent();
		addLabelToRow(new FormLabel(inRow.getFieldLabel()), colIndex, rowIndex);
		int fieldEndCols = colIndex + inRow.getWidthPercent() - 1;
		int fieldMargin = colIndex +FormLabel.LABEL_WIDTH_DEFAULT;
		mainLayout.addComponent(inRow.getField(), fieldMargin, rowIndex, fieldEndCols, rowIndex);
		inRow.getField().setSizeFull();
	}

	private void addFieldToRowWithTop(FormField inRow, int colIndex, int rowIndex) {
		colIndex += inRow.getMarginLeftPercent();
		int fieldEndCols = colIndex + inRow.getWidthPercent() - 1;
		VerticalLayout fieldContainer = new VerticalLayout();
		fieldContainer.addComponent(inRow.getFieldLabel());
		fieldContainer.setComponentAlignment(inRow.getFieldLabel(), Alignment.MIDDLE_LEFT);
		fieldContainer.addComponent(inRow.getField());
		mainLayout.addComponent(fieldContainer, colIndex, rowIndex, fieldEndCols, rowIndex);
		inRow.getField().setSizeFull();
	}


}


