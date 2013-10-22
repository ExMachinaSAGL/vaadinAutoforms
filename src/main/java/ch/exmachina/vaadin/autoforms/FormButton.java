package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.Button;

/**
 * @autor Marco Manzi
 */
public class FormButton implements FormComponent{

	private final Button button;
	private String buttonName;

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

	public Button getButton() {
		return button;
	}

	public String getButtonName() {
		return buttonName;
	}
}
