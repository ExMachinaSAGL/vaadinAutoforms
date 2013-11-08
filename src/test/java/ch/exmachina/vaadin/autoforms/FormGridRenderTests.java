package ch.exmachina.vaadin.autoforms;


import com.vaadin.ui.*;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @autor Marco Manzi
 */
public class FormGridRenderTests {
	EnhancedFormGridRender formGridRender;

	FormComponent oneField;

	@Before
	public void before() {
		formGridRender = new EnhancedFormGridRender();
		oneField = new FormField("Test", TextField.class);
	}

	@Test
	public void testGridColumnNumForOneFormFieldWithLabel() {
		assertEquals(10, formGridRender.numOfGridColumn(Arrays.asList(oneField)));
	}

	@Test
	public void testGridColumnNumForOneFormFieldWithButton() {
		FormComponent button = new FormButton("Test", null);
		assertEquals(10, formGridRender.numOfGridColumn(Arrays.asList(oneField, button)));
	}

	@Test
	public void testGridColumnNumForTwoFormFieldWithLabel() {
		FormComponent secondField = new FormField("Test", TextField.class);
		assertEquals(20, formGridRender.numOfGridColumn(Arrays.asList(oneField, secondField)));
	}

	@Test
	public void testGridCreationForOneFormFieldWildLabel() {
//		EnhancedFormGridRender enhancedFormGridRender = new EnhancedFormGridRender(new LinkedList<FormComponent>(Arrays.asList(oneField)));
//		GridLayout layout = enhancedFormGridRender.render();
//		assertEquals(10, layout.getColumns());
//		assertEquals(layout.getComponent(0, 0), layout.getComponent(0, 1));
//		assertEquals(((FormField) oneField).getFieldLabel(), layout.getComponent(0, 0));
	}

}
