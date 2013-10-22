package ch.exmachina.vaadin.autoforms;

import ch.exmachina.vaadin.autoforms.showcase.*;
import com.vaadin.ui.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Marco Manzi
 */
public class FormCreatorTest {

	private FormCreator<TestModel> formWithOneField;
	private FormCreator formWithTwoFieldOnSameRow;
	private FormCreator formWithTwoFieldsOnTwoRow;
	private FormCreator formWithAComboBox;
	private FormCreator formWithOneFieldAndTwoButton;
	private FormCreator localizedFormWithOneField;
	private OneFieldOnFirstRowReadOnly formWithOneFieldReadOnly;
	private OneFieldOnFirstRowWithButton formWithOneFieldAndButtonOnSameRow;

	@Before
	public void setup() {
		formWithOneField = new OneFieldOnFirstRow();
		formWithOneField.initForm();
		formWithAComboBox = new OneFieldWithComboBox();
		formWithAComboBox.initForm();
		formWithTwoFieldOnSameRow = new TwoFieldOnSameRow();
		formWithTwoFieldOnSameRow.initForm();
		formWithTwoFieldsOnTwoRow = new TwoFieldOnDifferentRows();
		formWithTwoFieldsOnTwoRow.initForm();
		formWithOneFieldAndTwoButton = new OneFieldWithOkAndCancelButton();
		formWithOneFieldAndTwoButton.initForm();
		localizedFormWithOneField = new LocalizedWithOneFieldForm();
		localizedFormWithOneField.initForm();
		formWithOneFieldReadOnly = new OneFieldOnFirstRowReadOnly();
		formWithOneFieldReadOnly.initForm();
		formWithOneFieldAndButtonOnSameRow = new OneFieldOnFirstRowWithButton();
		formWithOneFieldAndButtonOnSameRow.initForm();
	}

	private GridLayout getFormCreatorContainer (FormCreator form) {
		return (GridLayout)form.getMainLayout();
	}

	private Component getElementOnGridPosition(FormCreator form, int column, int row) {
		return getFormCreatorContainer(form).getComponent(column, row);
	}

	private AbstractField getField(FormCreator form, int column, int row) {
		return (AbstractField) getElementOnGridPosition(form, column, row);
	}

	private Label getLabelForField(FormCreator form, int column, int row) {
		return (Label)getElementOnGridPosition(form, column, row);
	}

	@Test
	public void gridHasDoubleNumberOfColumnOfHisFields() {
		assertEquals(2, getFormCreatorContainer(formWithOneField).getColumns());
		assertEquals(4, getFormCreatorContainer(formWithTwoFieldOnSameRow).getColumns() );
		assertEquals(2, getFormCreatorContainer(formWithTwoFieldsOnTwoRow).getColumns());
	}

	@Test
	public void formWithOneFieldHasLabelAndColumn() {
		Component oneFieldLabel = getElementOnGridPosition(formWithOneField, 0, 0);
		Component oneFieldText = getElementOnGridPosition(formWithOneField, 1, 0);
		assertTrue(oneFieldLabel instanceof Label);
		assertTrue(oneFieldText instanceof AbstractField);
	}

	@Test
	public void formWithOneField() {
		Label oneFieldLabel = getLabelForField(formWithOneField, 0, 0);
		assertEquals(oneFieldLabel.getValue(), "name");
	}



	@Test
	public void formWithTwoFieldOneSameRow() {
		assertEquals("name", getLabelForField(formWithTwoFieldOnSameRow, 0, 0).getValue());
		assertEquals("surname", getLabelForField(formWithTwoFieldOnSameRow, 2, 0).getValue());
	}

	@Test
	public void formWithTwoFieldOnDifferentRow() {
		assertEquals("name", getLabelForField(formWithTwoFieldsOnTwoRow, 0, 0).getValue());
		assertEquals("surname", getLabelForField(formWithTwoFieldsOnTwoRow, 0, 1).getValue());
	}

	@Test
	public void oneFieldFormModelBinding() {
		assertEquals("OneFieldValue", getField(formWithOneField, 1, 0).getValue());
	}

	@Test
	public void oneFieldFormWithADatasource() {
		ComboBox combo = (ComboBox) getField(formWithAComboBox, 1, 0);
		assertNotNull(combo.getContainerDataSource());
		assertTrue(combo.getContainerDataSource().getItemIds().size() > 0);
		assertEquals("FirstSelection", combo.getValue());
		assertEquals(1, combo.getItem(combo.getValue()).getItemProperty("id").getValue());
	}


	@Test
	public void beanResetOnForm() {
		assertEquals("OneFieldValue", formWithOneField.getValueFor("name"));
		TestModel testModel = formWithOneField.getBean();
		testModel.setName("ChangedValue");
		formWithOneField.setBean(testModel);
		assertEquals("ChangedValue", formWithOneField.getValueFor("name"));
	}

	@Test
	public void formWithOneFieldAndTwoButton() {
		GridLayout formGrid = getFormCreatorContainer(formWithOneFieldAndTwoButton);
		HorizontalLayout buttonLayout = (HorizontalLayout) formGrid.getComponent(0, formGrid.getColumns() - 1);
		assertTrue(buttonLayout.getComponent(0) instanceof Button);
		assertTrue(buttonLayout.getComponent(1) instanceof Button);
	}

	@Test
	public void localizedFormWithOneField() {
		Label oneFieldLabel = getLabelForField(localizedFormWithOneField, 0, 0);
		assertEquals(oneFieldLabel.getValue(), "LocalizedName");
	}

	@Test
	public void formWithOneReadOnlyField() {
		assertTrue(formWithOneFieldReadOnly.getComponent("name").getField().isReadOnly());
	}

	@Test
	public void formWithOneFieldAndButtonNear() {
		assertTrue(getFormCreatorContainer(formWithOneFieldAndButtonOnSameRow).getComponent(2, 0) instanceof Button);
	}

}