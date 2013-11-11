package ch.exmachina.vaadin.autoforms;

import ch.exmachina.vaadin.autoforms.showcase.OneFieldOnFirstRow;
import ch.exmachina.vaadin.autoforms.showcase.TestModel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @autor Marco Manzi
 */
public class FormValidationTests {

	@Test
	public void testOneFieldValidation() {
		OneFieldOnFirstRow form = new OneFieldOnFirstRow();
		form.initWithBean(new TestModel());
		Assert.assertNotNull(form.commit());
	}
}
