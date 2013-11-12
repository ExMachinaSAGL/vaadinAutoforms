package ch.exmachina.vaadin.autoforms;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;

import java.util.LinkedList;

/**
 * @autor Marco Manzi
 */
public class PercentGridRendered implements GridRender {
	private GridLayout mainLayout;
	private int labelWidth = 14;
	private static final int LABEL_WIDTH_DEFAULT = 14;

	private PercentGridDesigner designer;
	private FormCreator formCreator;

	public PercentGridRendered() {
		this(0);
	}

	public PercentGridRendered(int labelWidth) {
		this.labelWidth = labelWidth > 0? labelWidth: LABEL_WIDTH_DEFAULT;
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
		mainLayout.setMargin(true);
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
		colIndex += inRow.getMarginLeftPercent();
		mainLayout.addComponent(inRow.getFieldLabel(), colIndex, rowIndex, colIndex + labelWidth, rowIndex);
		mainLayout.setComponentAlignment(inRow.getFieldLabel(), Alignment.MIDDLE_CENTER);
		int fieldEndCols = colIndex + inRow.getWidthPercent() - 1;
		int fieldMargin = colIndex + labelWidth + 1;
		mainLayout.addComponent(inRow.getField(), fieldMargin, rowIndex, fieldEndCols, rowIndex);
		mainLayout.setColumnExpandRatio(colIndex, 1);
	}


}


