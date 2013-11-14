package ch.exmachina.vaadin.autoforms;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * @autor Marco Manzi
 */
public class FormLabel implements FormComponent {
	public static final int LABEL_WIDTH_DEFAULT = 1;
	private Label label;
	private String name;
	private int marginLeftPercent;

	public FormLabel(String name) {
		this(new Label(name, ContentMode.HTML));
	}

	public FormLabel(Label label) {
		this.name = label.getValue();
		this.label = label;
		this.label.setSizeUndefined();
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public FormComponent setupForForm(FormCreator formCreator) {
		label.setValue(formCreator.getLabelFor(name));
		label.addStyleName(formCreator.getStyleNameFor(name) + "-label");
		return this;
	}

	@Override
	public FormType getType() {
		return FormType.LABEL;
	}

	@Override
	public int getWidthPercent() {
		return LABEL_WIDTH_DEFAULT;
	}

	@Override
	public void setWidthPercent(int widthPercent) {}

	@Override
	public int getMarginLeftPercent() {
		return this.marginLeftPercent;
	}

	@Override
	public void setMarginLeftPercent(int marginLeftPercent) {
		this.marginLeftPercent = marginLeftPercent;
	}

	@Override
	public boolean hasAutomaticWidth() {
		return false;
	}
}
