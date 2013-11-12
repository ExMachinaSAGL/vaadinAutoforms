package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.Button;
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
		Assert.assertEquals(75, designedComponents.get(0).get(0).getWidthPercent());
		Assert.assertEquals(25, designedComponents.get(0).get(1).getWidthPercent());
	}

	@Test
	public void testOneField() {
		LinkedList<LinkedList<FormComponent>> components = new LinkedList<LinkedList<FormComponent>>();
		components.add(new LinkedList<FormComponent>() {
			{
				add(new  FormField("test", TextField.class, 100));
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
				add(new  FormField("test", TextField.class, 50, 20));
				add(new  FormField("test2", TextField.class));
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
				add(new  FormField("test", TextField.class, 40));
				add(new  FormField("test2", TextField.class));
				add(new  FormField("test2", TextField.class, 10));
				add(new  FormField("test2", TextField.class));
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
				add(new  FormField("test", TextField.class, 100));
				add(new  FormField("test2", TextField.class));
				add(new  FormField("test2", TextField.class, 10));
				add(new  FormField("test2", TextField.class));
			}
		});
		new PercentGridDesigner().addPercents(components);
	}
}
