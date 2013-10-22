package ch.exmachina.vaadin.autoforms;

/**
 * @autor Marco Manzi
 */
public interface FormComponent {
	FormComponent setupForForm(FormCreator formCreator);
	FormType getType();
}
