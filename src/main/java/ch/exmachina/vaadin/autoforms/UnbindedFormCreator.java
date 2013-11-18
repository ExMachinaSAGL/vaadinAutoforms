package ch.exmachina.vaadin.autoforms;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Layout;

import java.util.*;

public abstract class UnbindedFormCreator {
	protected Map<String, FormField> fieldsOfForm = new HashMap<String, FormField>();

	LinkedList<LinkedList<FormComponent>> components = new LinkedList<LinkedList<FormComponent>>();

	LinkedList<FormButton> buttons = new LinkedList<FormButton>();

	private Layout mainLayout;

	private Map<String, FormButton> buttonsOfForm = new HashMap<String, FormButton>();

	public UnbindedFormCreator() {
		this(new FormGridRender());
	}

	public UnbindedFormCreator(GridRender gridRender) {
		initFields();
		mainLayout = gridRender.render(this);
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
	 * Add a row to the form, the fields can have a width's sum  <= 100
	 *
	 * @param fields
	 */
	protected void addRow(FormComponent... fields) {
		LinkedList<FormComponent> fieldsOfRow = new LinkedList<FormComponent>(Arrays.asList(fields));
		components.add(fieldsOfRow);
		for (FormComponent field : fields) {
			if (field instanceof FormField) {
				FormField formField = (FormField) field;
				fieldsOfForm.put(formField.getFieldName(), formField);
			} else if (field instanceof FormButton) {
				buttonsOfForm.put(((FormButton) field).getButtonName(), (FormButton) field);
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
		if (buttonsOfForm.containsKey(buttonName))
			return buttonsOfForm.get(buttonName);
		return null;
	}

	public LinkedList<FormField> getAllComponent() {
		return new LinkedList<FormField>(fieldsOfForm.values());
	}
}
