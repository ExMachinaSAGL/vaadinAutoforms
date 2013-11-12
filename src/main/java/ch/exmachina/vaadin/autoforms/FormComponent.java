package ch.exmachina.vaadin.autoforms;

/**
 * @autor Marco Manzi
 */
public interface FormComponent {
	FormComponent setupForForm(FormCreator formCreator);
	FormType getType();
	int getWidthPercent();
	void setWidthPercent(int widthPercent);
	int getMarginLeftPercent();
	boolean hasAutomaticWidth();
}
