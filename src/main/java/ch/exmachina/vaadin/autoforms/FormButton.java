package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.Button;

/**
 * @autor Marco Manzi
 */
public class FormButton implements FormComponent{

	private final Button button;
	private String buttonName;
	private int marginLeftPercent = 0;
	private int widthPercent = 0;

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

	public Button getButton() {
		return button;
	}

	public String getButtonName() {
		return buttonName;
	}
}
