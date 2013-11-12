package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @autor Marco Manzi
 */
public class PercentGridRenderTest {
	@Test
	public void simpleOneFieldForm() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormField>() {
			{
				add(new FormField("test", TextField.class, 100));
			}
		});
		testFieldLabel(created, "test", 0, 15);
		testField(created, 15, 100);
	}


	@Test
	public void simpleTwoFieldForm() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormField>() {
			{
				add(new FormField("test1", TextField.class, 50));
				add(new FormField("test2", TextField.class, 50));
			}
		});
		testFieldLabel(created, "test1", 0, 15);
		testField(created, 15, 50);
		testFieldLabel(created, "test2", 50, 65);
		testField(created, 65, 99);
	}

	@Test
	public void testOneFieldAutomatic() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormField>() {
			{
				add(new FormField("test", TextField.class));
			}
		});
		testFieldLabel(created, "test", 0, 15);
		testField(created, 15, 100);
	}


	@Test
	public void testTwoFieldWithFieldAutomatic() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormField>() {
			{
				add(new FormField("test1", TextField.class));
				add(new FormField("test2", TextField.class));
			}
		});
		testFieldLabel(created, "test1", 0, 15);
		testField(created, 15, 50);
		testFieldLabel(created, "test2", 50, 65);
		testField(created, 65, 100);
	}

	@Test
	public void testOneFieldWithMargin() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormField>() {
			{
				add(new FormField("test1", TextField.class, 50, 30));
			}
		});
		testFieldLabel(created, "test1", 30, 45);
		testField(created, 45, 80);
	}

	@Test
	public void testOneFieldWithMarginAndOneButton() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList() {
			{
				add(new FormField("test1", TextField.class, 30, 10));
				add(new FormButton("testButton", null, 10));
				add(new FormField("test2", TextField.class));
			}
		});
		testFieldLabel(created, "test1", 10, 25);
		testField(created, 25, 40);
		testButton(created, "testButton", 50, 75);
		testFieldLabel(created, "test2", 75, 90);
		testField(created, 90, 100);
	}

	@Test
	public void testTwoFieldWithMargin() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormField>() {
			{
				add(new FormField("test1", TextField.class, 50, 30));
				add(new FormField("test2", TextField.class, 20));
			}
		});
		testFieldLabel(created, "test1", 30, 45);
		testField(created, 45, 80);
		testFieldLabel(created,	"test2", 90, 95);
		testField(created, 95, 100);
	}

	@Test
	public void testTwoFieldOneAutomaticWithMargin() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormField>() {
			{
				add(new FormField("test1", TextField.class, 50, 30));
				add(new FormField("test2", TextField.class));
			}
		});
		testFieldLabel(created, "test1", 30, 45);
		testField(created, 45, 80);
		testFieldLabel(created,	"test2", 90, 95);
		testField(created, 95, 100);
	}


	private GridLayout testPercentGridRendererForFormWithField(ArrayList<FormField> fields) {
		FormCreator formCreator = createTestForm(fields);
		return new PercentGridRendered().render(formCreator);
	}

	private FormCreator createTestForm(final List<FormField> testFields) {
		return new FormCreator() {
			@Override
			protected void beforeRendering() {}

			@Override
			protected void initFields() {
				addRow(testFields.toArray(new FormComponent[testFields.size()]));
			}
		};
	}

	private void testField(GridLayout created, int startCol, int endCol) {
		for (int i = startCol; i < endCol; i++) {
			Component inGrid = created.getComponent(i, 0);
			assertTrue("Problem with field on with index:" + i, inGrid instanceof TextField);
		}
	}

	private void testButton(GridLayout created, String testButton, int startCol, int endCol) {
		for (int i = startCol; i < endCol; i++) {
			Component inGrid = created.getComponent(i, 0);
			assertTrue("Problem with button with index:" + i, inGrid instanceof Button);
			assertEquals("Problem with button name with index:" + i, testButton, ((Button)inGrid).getCaption());
		}
	}

	private void testFieldLabel(GridLayout created, String labelValue, int startCol, int endCol) {
		for (int i = startCol; i < endCol; i++) {
			Component inGrid = created.getComponent(i, 0);
			assertTrue(inGrid instanceof Label);
			assertEquals("Problem with label on index:" + i, labelValue, ((Label) inGrid).getValue());
		}
	}




}
