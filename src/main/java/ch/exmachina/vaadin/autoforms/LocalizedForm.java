package ch.exmachina.vaadin.autoforms;

/**
 * @autor Marco Manzi
 */
public interface LocalizedForm {
	String getMessageFor(String label);
	String getItemCaptionFor(String itemId);
}
