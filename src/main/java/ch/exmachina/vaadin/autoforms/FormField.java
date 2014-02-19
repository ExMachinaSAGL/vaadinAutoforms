package ch.exmachina.vaadin.autoforms;

import ch.exmachina.vaadin.autoforms.containers.FilteredContainer;
import com.vaadin.data.Container;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

/**
 * @autor Marco Manzi
 */
public class FormField implements FormComponent {
	protected AbstractField<?> field;
	private Label fieldLabel;
	private String fieldName;
	private boolean immediate = true;
	private boolean useBinder = true;
	private boolean readOnly = false;
	private int widthPercent = 0;
	private int marginLeftPercent = 0;
	private LABEL_POSITION labelPosition = LABEL_POSITION.MIDDLE_LEFT;

	public enum LABEL_POSITION {
		TOP_LEFT, MIDDLE_LEFT
	}

	/**
	 * Create a form field, with a field that is instance of fieldClass, with widthPercent you can set the width in row's width %
	 * and with marginLeftPercent you can set the margin as row's width %
	 *
	 * @param fieldName
	 * @param fieldClass
	 */
	public FormField(String fieldName, Class<? extends AbstractField<?>> fieldClass) {
		this(fieldName, (AbstractField<?>) wrapNewInstantiation(fieldClass));
	}

	/**
	 * Create a form field
	 * @param fieldName
	 * @param field
	 */
	public FormField(String fieldName, AbstractField<?> field) {
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

	private static Object wrapNewInstantiation(Class<? extends AbstractField<?>> fieldClass) {
		try {
			return fieldClass.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("Wrong Class used for FormField" + fieldClass);
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
			((FilteredContainer<?>) container).setOn(field);
	}


	@Override
	public FormComponent setupForForm(UnboundFormCreator formCreator) {
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

	public AbstractField<?> getField() {
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

	@Override
	public int getWidthPercent() {
		return widthPercent;
	}

	@Override
	public void setWidthPercent(int widthPercent) {
		this.widthPercent = widthPercent;
	}

	@Override
	public int getMarginLeftPercent() {
		return marginLeftPercent;
	}

	@Override
	public void setMarginLeftPercent(int marginLeftPercent) {
		this.marginLeftPercent = marginLeftPercent;
	}

	@Override
	public boolean hasAutomaticWidth() {
		return widthPercent == 0;
	}

	public LABEL_POSITION getLabelPosition() {
		return labelPosition;
	}

	public void setLabelPosition(LABEL_POSITION labelPosition) {
		this.labelPosition = labelPosition;
	}
}