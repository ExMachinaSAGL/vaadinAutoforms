package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.Button;

/**
 * @autor Marco Manzi
 */
public class FormButton implements FormComponent{

	private final Button button;
	private String buttonName;
	private int widthPercent = 25;
	private int marginLeftPercent = 0;

	/**
	 * Create a button for form, it need a click listener attached
	 * @param buttonName
	 * @param clickListener
	 */
	public FormButton(String buttonName, Button.ClickListener clickListener) {
		this.buttonName = buttonName;
		this.button = new Button(this.buttonName);
		if (clickListener != null)
			button.addClickListener(clickListener);
	}

	/**
	 * Create a button for form, it need a click listener attached, you can add a margin on left
	 * @param buttonName
	 * @param clickListener
	 * @param marginLeftPercent
	 */
	public FormButton(String buttonName, Button.ClickListener clickListener, int marginLeftPercent) {
		this(buttonName, clickListener);
		this.marginLeftPercent = marginLeftPercent;
	}

	@Override
	public FormComponent setupForForm(FormCreator formCreator) {
		button.setCaption(formCreator.getLabelFor(buttonName));
		return this;
	}

	@Override
	public FormType getType() {
		return FormType.BUTTON;
	}

	@Override
	public int getWidthPercent() {
		return this.widthPercent;
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
	public boolean hasAutomaticWidth() {
		return this.widthPercent == 0;
	}

	public Button getButton() {
		return button;
	}

	public String getButtonName() {
		return buttonName;
	}
}
