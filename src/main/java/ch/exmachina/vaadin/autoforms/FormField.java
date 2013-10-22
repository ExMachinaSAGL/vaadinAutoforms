package ch.exmachina.vaadin.autoforms;

import ch.exmachina.vaadin.autoforms.containers.FilteredContainer;
import com.vaadin.data.Container;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Label;

/**
 * @autor Marco Manzi
 */
public class FormField implements FormComponent {
	private AbstractField field;
	private Label fieldLabel;
	private String fieldName;
	private boolean immediate = true;
	private boolean useBinder = true;
	private boolean readOnly = false;

	/**
	 * Create a form field, witha a field that is instance of fieldClass
	 *
	 * @param fieldName
	 * @param fieldClass
	 */
	public FormField(String fieldName, Class<? extends AbstractField> fieldClass) {
		this(fieldName, (AbstractField) wrapNewInstantiation(fieldClass));
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
		if (field instanceof AbstractSelect) {
			setupSelectItems((AbstractSelect) field, formCreator);
		}
		return this;
	}

	private void setupSelectItems(AbstractSelect field, FormCreator formCreator) {
		for (Object itemId : field.getItemIds()) {
			String itemCaption = formCreator.getItemLabelFor(itemId.toString());
			field.setItemCaption(itemId, itemCaption);
		}
	}

	@Override
	public FormType getType() {
		return FormType.FIELD;
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
}