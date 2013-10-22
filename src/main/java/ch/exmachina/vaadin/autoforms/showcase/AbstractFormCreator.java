package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.FormCreator;

/**
 * @autor Marco Manzi
 */
public abstract class AbstractFormCreator<T> extends FormCreator<T>{

	protected AbstractFormCreator(T bean) {
		super(bean);
	}

	@Override
	protected void beforeRendering() {}
}
