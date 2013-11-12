package ch.exmachina.vaadin.autoforms;

import ch.exmachina.vaadin.autoforms.containers.FilteredContainer;
import com.vaadin.data.Container;
import com.vaadin.data.Validator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

/**
 * @autor Marco Manzi
 */
public class FormField implements FormComponent {
	protected AbstractField field;
	private Label fieldLabel;
	private String fieldName;
	private boolean immediate = true;
	private boolean useBinder = true;
	private boolean readOnly = false;
	private int widthPercent = -1;
	private int marginLeftPercent = -1;

	/**
	 * Create a form field, with a field that is instance of fieldClass, with widthPercent you can set the width in row's width %
	 * and with marginLeftPercent you can set the margin as row's width %
	 *
	 * @param fieldName
	 * @param fieldClass
	 * @param widthPercent
	 * @param marginLeftPercent
	 */
	public FormField(String fieldName, Class<? extends AbstractField> fieldClass, int widthPercent, int marginLeftPercent) {
		this(fieldName, (AbstractField) wrapNewInstantiation(fieldClass));
		this.widthPercent = widthPercent;
		this.marginLeftPercent = marginLeftPercent;
	}

	/**
	 * Create a form field, with a field that is instance of fieldClass, position and width is automatically calculated on row
	 *
	 * @param fieldName
	 * @param fieldClass
	 */
	public FormField(String fieldName, Class<? extends AbstractField> fieldClass) {
		this(fieldName, fieldClass, 0, 0);
	}

	/**
	 * Create a form field, with a field that is instance of fieldClass, with widthPercent you can set the width in row's width %.
	 * The margin left will be zero
	 *
	 * @param fieldName
	 * @param fieldClass
	 * @param widthPercent
	 */
	public FormField(String fieldName, Class<? extends AbstractField> fieldClass, int widthPercent) {
		this(fieldName, fieldClass, widthPercent, 0);
	}

	private static Object wrapNewInstantiation(Class<? extends AbstractField> fieldClass) {
		try {
			return fieldClass.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("Wrong Class used for FormField" + fieldClass);
		}
	}

	public FormField(String fieldName, AbstractField field) {
		this.field = field;
		field.setSizeFull();
		field.setImmediate(immediate);
		this.fieldName = fieldName;
		fieldLabel = new Label(this.fieldName, ContentMode.HTML);
		fieldLabel.setSizeUndefined();

		if (field instanceof AbstractTextField) {
			((AbstractTextField)field).setNullRepresentation("");
		}
	}

	/**
	 * Call FormField(fieldName, fieldClass) and attach the container to the field
	 *
	 * @param fieldName
	 * @param field
	 * @param container
	 */
	public FormField(String fieldName, Class<? extends AbstractSelect> field, Container container) {
		this(fieldName, field);
		setupContainer((AbstractSelect) this.field, container);
	}


	public FormField(String fieldName, AbstractSelect field, Container container) {
		this(fieldName, field);
		setupContainer(field, container);
	}

	private void setupContainer(AbstractSelect field, Container container) {
		field.setContainerDataSource(container);
		if (container instanceof FilteredContainer)
			((FilteredContainer) container).setOn(field);
	}


	@Override
	public FormComponent setupForForm(FormCreator formCreator) {
		fieldLabel.setValue(formCreator.getLabelFor(fieldName));
		fieldLabel.addStyleName(formCreator.getStyleNameFor(fieldName) + "-label");
		field.addStyleName(formCreator.getStyleNameFor(fieldName) + "-field");
		return this;
	}

	@Override
	public FormType getType() {
		return FormType.FIELD;
	}

	public void setRequired(boolean b) {
		this.getField().setRequired(true);
	}

	public AbstractField getField() {
		return field;
	}

	public Label getFieldLabel() {
		return fieldLabel;
	}

	public String getFieldName() {
		return fieldName;
	}

	public boolean isImmediate() {
		return field.isImmediate();
	}

	public void setImmediate(boolean immediate) {
		this.field.setImmediate(immediate);
	}

	public boolean useBinder() {
		return useBinder;
	}

	public void setUseBinder(boolean useBinder) {
		this.useBinder = useBinder;
	}

	public FormField setReadOnly(boolean readOnly) {
		this.getField().setReadOnly(readOnly);
		this.readOnly = readOnly;
		return this;
	}

	public boolean isReadOnly() {
		return this.readOnly;
	}

	public int getWidthPercent() {
		return widthPercent;
	}

	@Override
	public void setWidthPercent(int widthPercent) {
		this.widthPercent = widthPercent;
	}

	public int getMarginLeftPercent() {
		return marginLeftPercent;
	}

	public boolean hasAutomaticWidth() {
		return widthPercent == 0;
	}
}