package ch.exmachina.vaadin.autoforms;

import ch.exmachina.vaadin.autoforms.containers.utils.ContainerUtils;
import com.vaadin.data.fieldgroup.*;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;

import java.util.*;

/**
 * @autor Marco Manzi
 */
public abstract class FormCreator<T> {
	LinkedList<LinkedList<FormComponent>> components = new LinkedList<LinkedList<FormComponent>>();

	LinkedList<FormButton> buttons = new LinkedList<FormButton>();

	private Layout mainLayout;

	private Map<String, FormField> fieldsOfForm = new HashMap<String, FormField>();

	private BeanFieldGroup binder;

	private T bean;

	private ValidationManager validationManager = new ValidationManager();

	public FormCreator() {
		this (new FormGridRender());
	}

	public FormCreator(GridRender gridRender) {
		initFields();
		mainLayout = gridRender.render(this);
	}

	protected final void initWithBean(T bean) {
		this.bean = bean;
		bindToBean();
	}

	public Layout getMainLayout() {
		setDefaultStylesOnElements();
		beforeRendering();
		return mainLayout;
	}

	private void setDefaultStylesOnElements() {
		for (FormField field : getAllComponent()) {
			field.getField().setHeight(24, Sizeable.Unit.PIXELS);
		}
	}

	/**
	 * Return the current value for the field
	 *
	 * @param fieldName name of the field to check
	 * @return value on field current setted
	 */
	public Object getValueFor(String fieldName) {
		return fieldsOfForm.get(fieldName).getField().getValue();
	}

	/**
	 * Commit the current values on form, updating the bean
	 *
	 * @return the bean with values of form
	 * @throws IllegalArgumentException
	 */
	public T commit() {
		try {
			binder.commit();
			return (T) binder.getItemDataSource().getBean();
		} catch (FieldGroup.CommitException e) {
//			throw new IllegalArgumentException(e);
			return null;
		}
	}

	private void setValueOnSelectLazyLoadedValues(T bean) {
		for (String fieldName : fieldsOfForm.keySet()) {
			FormField formField = fieldsOfForm.get(fieldName);
			ContainerUtils.setOnSelectContainerBeanValue(bean, fieldName, formField.getField());
		}
	}

	/**
	 * Used to apply the last customization of layout, like styles.
	 * Normally a default style is used, in this method with getComponent and getAllComponents you can
	 * modify the default style or make last minute changes
	 */
	protected abstract void beforeRendering();

	/**
	 * Setup the field to be showed in the form, use addRow to add FormField and
	 * addButton to add buttons
	 */
	protected abstract void initFields();

	/**
	 * Return the backed bean of the form, this will we used with the BeanFieldGroup
	 * to provide auto validation, and will fill the fields with the model value.
	 * Field name and component properties have to be the same
	 *
	 * @return Model of the form
	 */
	public T getBean() {
		return (T) binder.getItemDataSource().getBean();
	}

	/**
	 * Update the form with the values of bean
	 *
	 * @param bean the bean with values to show in form
	 */
	public void setBean(T bean) {
		setValueOnSelectLazyLoadedValues(bean);
		binder.setItemDataSource(bean);
		for (String fieldName : fieldsOfForm.keySet()) {
			FormField formField = fieldsOfForm.get(fieldName);
			if (formField.isReadOnly()) {
				formField.getField().setReadOnly(true);
			}
		}
	}

	protected T getNotBindedBean() {
		return bean;
	}

	/**
	 * Add a row to the form, the fields can have a width's sum  <= 100
	 * @param fields
	 */
	protected void addRow(FormComponent... fields) {
		LinkedList<FormComponent> fieldsOfRow = new LinkedList<FormComponent>(Arrays.asList(fields));
		components.add(fieldsOfRow);
		for (FormComponent field : fields) {
			if (field instanceof FormField) {
				FormField formField = (FormField) field;
				fieldsOfForm.put(formField.getFieldName(), formField);
			}
		}
	}

	/**
	 * Deprecated, it's used only in the form grid rendere to add button to a
	 * row after all other fields.
	 * Call addRow with a FormButton inside to add buttons
	 *
	 * @param buttons
	 */
	@Deprecated
	protected void addButton(FormButton... buttons) {
		this.buttons.addAll(new LinkedList<FormButton>(Arrays.asList(buttons)));
	}

	LinkedList<FormField> getInputFields(LinkedList<FormComponent> rowFields) {
		LinkedList<FormField> formFields = new LinkedList<FormField>();
		for (FormComponent comp : rowFields) {
			if (comp.getType().equals(FormType.FIELD)) {
				formFields.add((FormField) comp);
			}
		}
		return formFields;
	}

	LinkedList<FormButton> getButtonFields(LinkedList<FormComponent> rowFields) {
		LinkedList<FormButton> formFields = new LinkedList<FormButton>();
		for (FormComponent comp : rowFields) {
			if (comp.getType().equals(FormType.BUTTON)) {
				formFields.add((FormButton) comp);
			}
		}
		return formFields;
	}

	/**
	 * Setup the binder to the bean
	 */
	private void bindToBean() {
		binder = new BeanFieldGroup(bean.getClass());
		validationManager.addRequiredValidationAndValidateOnBlur(binder);
		validationManager.setErrorMessage(getLabelFor(ValidationManager.KEY_REQUIRED_ERROR));

		for (String fieldName : fieldsOfForm.keySet()) {
			FormField formField = fieldsOfForm.get(fieldName);
			if (formField.useBinder()) {
				Field field = formField.getField();
				binder.bind((Field<?>) field, fieldName);
			}
		}
		setBean(bean);
	}

	public String getLabelFor(String fieldName) {
		if (this instanceof LocalizedForm) {
			return ((LocalizedForm) this).getMessageFor(this.getClass().getSimpleName() + ".form." + fieldName);
		}
		return fieldName;
	}

	public String getStyleNameFor(String fieldName) {
		return String.format(this.getClass().getSimpleName() + "-" + fieldName);
	}

	public FormField getComponent(String fieldName) {
		return fieldsOfForm.get(fieldName);
	}

	public FormButton getButton(String buttonName) {
		for (FormButton button : buttons) {
			if (button.getButtonName().equals(buttonName)) {
				return button;
			}
		}
		return null;
	}

	public LinkedList<FormField> getAllComponent() {
		return new LinkedList<FormField>(fieldsOfForm.values());
	}

}
