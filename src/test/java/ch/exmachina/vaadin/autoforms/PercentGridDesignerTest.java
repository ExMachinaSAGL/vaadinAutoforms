package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.TextField;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @autor Marco Manzi
 */
public class PercentGridDesignerTest {

	@Test
	public void testOneFieldAutomatic() {
		LinkedList<LinkedList<FormComponent>> components = new LinkedList<LinkedList<FormComponent>>();
		components.add(new LinkedList<FormComponent>() {
			{
				add(new  FormField("test", TextField.class));
			}
		});
		LinkedList<LinkedList<FormComponent>> designedComponents = new PercentGridDesigner().addPercents(components);
		Assert.assertEquals(100, designedComponents.get(0).get(0).getWidthPercent());
	}

	@Test
	public void testOneFieldWithButtonAutomatic() {
		LinkedList<LinkedList<FormComponent>> components = new LinkedList<LinkedList<FormComponent>>();
		components.add(new LinkedList<FormComponent>() {
			{
				add(new  FormField("test", TextField.class));
				add(new FormButton("test2", null));
			}
		});
		LinkedList<LinkedList<FormComponent>> designedComponents = new PercentGridDesigner().addPercents(components);
		Assert.assertEquals(50, designedComponents.get(0).get(0).getWidthPercent());
		Assert.assertEquals(50, designedComponents.get(0).get(1).getWidthPercent());
	}

	@Test
	public void testOneField() {
		LinkedList<LinkedList<FormComponent>> components = new LinkedList<LinkedList<FormComponent>>();
		components.add(new LinkedList<FormComponent>() {
			{
				add(new  FormFieldBuilder("test", TextField.class).width(100).build());
			}
		});
		LinkedList<LinkedList<FormComponent>> designedComponents = new PercentGridDesigner().addPercents(components);
		Assert.assertEquals(100, designedComponents.get(0).get(0).getWidthPercent());
	}

	@Test
	public void testTwoFieldAutomatic() {
		LinkedList<LinkedList<FormComponent>> components = new LinkedList<LinkedList<FormComponent>>();
		components.add(new LinkedList<FormComponent>() {
			{
				add(new  FormField("test", TextField.class));
				add(new  FormField("test2", TextField.class));
			}
		});
		LinkedList<LinkedList<FormComponent>> designedComponents = new PercentGridDesigner().addPercents(components);
		Assert.assertEquals(50, designedComponents.get(0).get(0).getWidthPercent());
		Assert.assertEquals(50, designedComponents.get(0).get(1).getWidthPercent());
	}

	@Test
	public void testTwoFieldOneAutomaticAndTheOtherWithMargin() {
		LinkedList<LinkedList<FormComponent>> components = new LinkedList<LinkedList<FormComponent>>();
		components.add(new LinkedList<FormComponent>() {
			{
				add(new  FormFieldBuilder("test", TextField.class).width(50).marginLeft(20).build());
				add(new  FormFieldBuilder("test2", TextField.class).build());
			}
		});
		LinkedList<LinkedList<FormComponent>> designedComponents = new PercentGridDesigner().addPercents(components);
		Assert.assertEquals(50, designedComponents.get(0).get(0).getWidthPercent());
		Assert.assertEquals(30, designedComponents.get(0).get(1).getWidthPercent());
	}

	@Test
	public void testTwoFieldOneAutomatic() {
		LinkedList<LinkedList<FormComponent>> components = new LinkedList<LinkedList<FormComponent>>();
		components.add(new LinkedList<FormComponent>() {
			{
				add(new  FormFieldBuilder("test", TextField.class).width(40).build());
				add(new  FormFieldBuilder("test2", TextField.class).build());
				add(new  FormFieldBuilder("test2", TextField.class).width(10).build());
				add(new  FormFieldBuilder("test2", TextField.class).build());
			}
		});
		LinkedList<LinkedList<FormComponent>> designedComponents = new PercentGridDesigner().addPercents(components);
		Assert.assertEquals(40, designedComponents.get(0).get(0).getWidthPercent());
		Assert.assertEquals(25, designedComponents.get(0).get(1).getWidthPercent());
		Assert.assertEquals(10, designedComponents.get(0).get(2).getWidthPercent());
		Assert.assertEquals(25, designedComponents.get(0).get(3).getWidthPercent());
	}

	@Test (expected = IllegalStateException.class)
	public void testMoreThan100OnComponents() {
		LinkedList<LinkedList<FormComponent>> components = new LinkedList<LinkedList<FormComponent>>();
		components.add(new LinkedList<FormComponent>() {
			{
				add(new  FormFieldBuilder("test", TextField.class).width(100).build());
				add(new  FormFieldBuilder("test2", TextField.class).build());
				add(new  FormFieldBuilder("test2", TextField.class).width(10).build());
				add(new  FormFieldBuilder("test2", TextField.class).build());
			}
		});
		new PercentGridDesigner().addPercents(components);
	}
}
