package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.Button;

/**
 * @autor Marco Manzi
 */
public class FormButtonBuilder {
	private FormButton formButton;

	public FormButtonBuilder(String name, Button.ClickListener clickListener) {
		formButton = new FormButton(name, clickListener);
	}

	public FormButtonBuilder width(int width) {
		formButton.setWidthPercent(width);
		return this;
	}

	public FormButtonBuilder marginLeft(int marginLeft) {
		formButton.setMarginLeftPercent(marginLeft);
		return this;
	}

	public FormButton build() {
		return formButton;
	}
}
