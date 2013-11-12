package ch.exmachina.vaadin.autoforms;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.LinkedList;

/**
 * @autor Marco Manzi
 */
public class PercentGridRendered implements GridRender {
	private GridLayout mainLayout;
	private static final int LABEL_WIDTH_DEFAULT = 0;


	private PercentGridDesigner designer;
	private FormCreator formCreator;

	public PercentGridRendered() {
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
		mainLayout = new GridLayout(100, designedComponents.size());
		mainLayout.setSpacing(true);
		mainLayout.setSizeFull();
		mainLayout.setHeight(-1, Sizeable.Unit.PIXELS);
	}

	private void addElementsToRow(LinkedList<FormComponent> rowComponents, int rowIndex) {
		int colIndex = 0;
		for (FormComponent inRow: rowComponents) {
			inRow.setupForForm(formCreator);
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
		if (LABEL_WIDTH_DEFAULT >= inRow.getWidthPercent()) {
			throw new IllegalStateException("Label width can't be greater than field");
		}
		switch (inRow.getLabelPosition()) {
			case MIDDLE_LEFT: addFieldToRowWithLabelOnLeft(inRow, colIndex, rowIndex); break;
			case TOP_LEFT: addFieldToRowWithTop(inRow, colIndex, rowIndex); break;
		}
	}

	private void addFieldToRowWithLabelOnLeft(FormField inRow, int colIndex, int rowIndex) {
		colIndex += inRow.getMarginLeftPercent();
		mainLayout.addComponent(inRow.getFieldLabel(), colIndex, rowIndex, colIndex + LABEL_WIDTH_DEFAULT, rowIndex);
		mainLayout.setComponentAlignment(inRow.getFieldLabel(), Alignment.MIDDLE_RIGHT);
		int fieldEndCols = colIndex + inRow.getWidthPercent() - 1;
		int fieldMargin = colIndex + LABEL_WIDTH_DEFAULT + 1;
		mainLayout.addComponent(inRow.getField(), fieldMargin, rowIndex, fieldEndCols, rowIndex);
		inRow.getField().setSizeFull();
		for (int i = fieldMargin; i <= fieldEndCols; i++) {
			mainLayout.setColumnExpandRatio(i, 1);
		}

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
		for (int i = colIndex; i <= fieldEndCols; i++) {
			mainLayout.setColumnExpandRatio(i, 1);
		}
	}


}


