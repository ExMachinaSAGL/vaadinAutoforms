package ch.exmachina.vaadin.autoforms;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

import java.util.LinkedList;

/**
 * @autor Marco Manzi
 */
public class FormGridRender implements GridRender {
	private GridLayout mainLayout;
	private int gridColumnNum = 0;
	HorizontalLayout buttonLayout;
	private UnbindedFormCreator formCreator;

	@Override
	public Layout render(UnbindedFormCreator formCreator) {
		this.formCreator = formCreator;
		initButtonLayout();
		initMainLayout();
		return mainLayout;
	}

	private void initButtonLayout() {
		buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
	}

	private void initMainLayout() {
		createGridLayout();
		int row = 0;
		LinkedList<LinkedList<FormComponent>> components = formCreator.components;
		for (LinkedList<FormComponent> rowFields : components) {
			addFieldsOfRowToGrid(rowFields, row++);
		}
		LinkedList<FormButton> formButtons = formCreator.buttons;
		for (FormButton button : formButtons) {
			button.setupForForm(formCreator);
			buttonLayout.addComponent(button.getButton());
		}
	}

	/**
	 * It create the grid layout, size full if to let the form enlarge on window change
	 */
	private void createGridLayout() {
		LinkedList<LinkedList<FormComponent>> components = formCreator.components;
		initGridColumnNum(components);

		int rowSize = formCreator.buttons.size() > 0 ? components.size() + 1 : components.size();
		mainLayout = new GridLayout(gridColumnNum, rowSize);
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSizeFull();
		mainLayout.setHeight(-1, Sizeable.Unit.PIXELS);
		if (formCreator.buttons.size() > 0) {
			mainLayout.addComponent(buttonLayout, 0, components.size(), mainLayout.getColumns() - 1, components.size());
		}
	}

	private void initGridColumnNum(LinkedList<LinkedList<FormComponent>> components) {
		for (LinkedList<FormComponent> fieldsOfRow : components) {
			int columnForFields = formCreator.getInputFields(fieldsOfRow).size() * 2;
			int columnForButtons = formCreator.getButtonFields(fieldsOfRow).size();
			int columnForRow = columnForFields + columnForButtons;
			gridColumnNum = gridColumnNum > columnForRow ? gridColumnNum : columnForRow;
		}
	}

	/**
	 * Add the field to the grid row, it set the field expand ratio to let the field get the max space,
	 * moreover it add the field the the max number of cols it can depending on the number of cols of grid
	 *
	 * @param rowFields
	 * @param row
	 */
	private void addFieldsOfRowToGrid(LinkedList<FormComponent> rowFields, int row) {
		int column = 0;
		mainLayout.setRowExpandRatio(row, 1);
		int numOfColsToAddForEachField = evaluateColumnsToAddForField(rowFields);
		for (FormComponent formField : rowFields) {
			formField.setupForForm(formCreator);
			switch (formField.getType()) {
				case FIELD:
					column = addFieldToGrid((FormField) formField, column, row, numOfColsToAddForEachField);
					break;
				case BUTTON:
					column = addButtonToGrid((FormButton) formField, column, row);
					break;
				case LABEL:
					break;
			}

		}
	}

	private int addButtonToGrid(FormButton formField, int column, int row) {
		mainLayout.addComponent(formField.getButton(), column++, row);
		return column;
	}

	private int addFieldToGrid(FormField formField, int column, int row, int numOfColsToAddForEachField) {
		mainLayout.addComponent(formField.getFieldLabel(), column++, row);
		mainLayout.setComponentAlignment(formField.getFieldLabel(), Alignment.MIDDLE_CENTER);
		mainLayout.setColumnExpandRatio(column, 1);
		mainLayout.addComponent(formField.getField(), column, row, column + numOfColsToAddForEachField, row);
		return column + numOfColsToAddForEachField + 1;
	}

	private int evaluateColumnsToAddForField(LinkedList<FormComponent> rowFields) {
		int numberOfInputs = formCreator.getInputFields(rowFields).size();
		int maxNumberOfColumnForFields = gridColumnNum - formCreator.getButtonFields(rowFields).size();
		if (numberOfInputs != 0) {
			return (maxNumberOfColumnForFields - numberOfInputs) / numberOfInputs - 1;
		} else {
			return maxNumberOfColumnForFields - 1;
		}
	}

}
