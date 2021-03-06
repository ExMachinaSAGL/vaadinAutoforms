package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.*;
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
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test", TextField.class).width(100).build());
			}
		}, 100);
		testFieldLabel(created, "test", 0, 1);
		testField(created, 1, 100);
	}

	@Test
	public void oneFielWithLabelOnTopdForm() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test", TextField.class).width(100).
						labelPosition(FormField.LABEL_POSITION.TOP_LEFT).build());
			}
		}, 100);
		for (int i = 0; i < 100; i++) {
			Component inGrid = created.getComponent(i, 0);
			assertTrue("Problem with label on index:" + i, inGrid instanceof VerticalLayout);
		}
		VerticalLayout fieldContainer = (VerticalLayout) created.getComponent(0, 0);
		assertTrue(fieldContainer.getComponent(0) instanceof Label);
		assertEquals(Alignment.MIDDLE_LEFT, fieldContainer.getComponentAlignment(fieldContainer.getComponent(0)));
		assertTrue(fieldContainer.getComponent(1) instanceof Field);
	}


	@Test
	public void simpleTwoFieldForm() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).width(50).build());
				add(new FormFieldBuilder("test2", TextField.class).width(50).build());
			}
		}, 100);
		testFieldLabel(created, "test1", 0, 1);
		testField(created, 1, 50);
		testFieldLabel(created, "test2", 50, 51);
		testField(created, 51, 99);
	}

	@Test
	public void testOneFieldAutomatic() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test", TextField.class).build());
			}
		}, 100);
		testFieldLabel(created, "test", 0, 1);
		testField(created, 1, 100);
	}


	@Test
	public void testTwoFieldWithFieldAutomatic() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).build());
				add(new FormFieldBuilder("test2", TextField.class).build());
			}
		}, 100);
		testFieldLabel(created, "test1", 0, 1);
		testField(created, 1, 50);
		testFieldLabel(created, "test2", 50, 51);
		testField(created, 51, 100);
	}

	@Test
	public void testOneFieldWithMargin() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).width(50).marginLeft(30).build());
			}
		}, 100);
		testFieldLabel(created, "test1", 30, 31);
		testField(created, 31, 80);
	}

	@Test
	public void testOneFieldWithMarginAndOneButton() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).width(30).marginLeft(10).build());
				add(new FormButton("testButton", null));
				add(new FormFieldBuilder("test2", TextField.class).build());
			}
		}, 100);
		testFieldLabel(created, "test1", 10, 11);
		testField(created, 11, 40);
		testButton(created, "testButton", 40, 70);
		testFieldLabel(created, "test2", 70, 71);
		testField(created, 71, 100);
	}

	@Test
	public void testTwoFieldWithMargin() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).width(50).marginLeft(30).build());
				add(new FormFieldBuilder("test2", TextField.class).width(20).build());
			}
		}, 100);
		testFieldLabel(created, "test1", 30, 31);
		testField(created, 31, 80);
		testFieldLabel(created,	"test2", 80, 81);
		testField(created, 81, 100);
	}

	@Test
	public void testTwoFieldOneAutomaticWithMargin() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).width(50).marginLeft(30).build());
				add(new FormFieldBuilder("test2", TextField.class).build());
			}
		}, 100);
		testFieldLabel(created, "test1", 30, 31);
		testField(created, 31, 80);
		testFieldLabel(created,	"test2", 80, 81);
		testField(created, 81, 100);
	}

	@Test
	public void testColumnExpandRationOnSingleField() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).build());
			}
		}, 100);
		assertEquals("Problem with label expand Ratio", 0, created.getColumnExpandRatio(0), 0);
		for (int i = 1; i < 99; i++)
			assertEquals("Problem with field expand Ratio", 1, created.getColumnExpandRatio(i), 0);
	}

	@Test
	public void testColumnExpandRationOnTwoField() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).build());
				add(new FormFieldBuilder("test2", TextField.class).build());
			}
		}, 100);
		assertEquals("Problem with label expand Ratio", 0, created.getColumnExpandRatio(0), 0);
		assertEquals("Problem with label expand Ratio", 0, created.getColumnExpandRatio(50), 0);
		for (int i = 1; i < 49; i++)
			assertEquals("Problem with field expand Ratio", 1, created.getColumnExpandRatio(i), 0);
		for (int i = 51; i < 99; i++)
			assertEquals("Problem with field expand Ratio", 1, created.getColumnExpandRatio(i), 0);
	}

	@Test
	public void testDesignerRenderIntegrationForDifferentColumns() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).build());
				add(new FormFieldBuilder("test2", TextField.class).width(13).build());
				add(new FormButtonBuilder("button", null).width(3).build());
			}
		}, 30);
		testFieldLabel(created, "test1", 0, 1);
		testField(created, 1, 14);
		testFieldLabel(created, "test2", 14, 15);
		testField(created, 15, 27);
		testButton(created, "button", 27, 30);

	}

	private GridLayout testPercentGridRendererForFormWithField(ArrayList<FormComponent> fields, int numColumns) {
		FormCreator<?> formCreator = createTestForm(fields, numColumns);
		return new PercentGridRendered(numColumns).render(formCreator);
	}

	private FormCreator<?> createTestForm(final List<FormComponent> testFields, int numColumns) {
		return new FormCreator<Void>(new PercentGridRendered(numColumns)) {
			@Override
			protected void beforeRendering() {}

			@Override
			protected void initFields() {
				FormComponent[] formComponents = new FormComponent[testFields.size()];
				int i = 0;
				for (FormComponent formComponent: testFields) {
					formComponents[i] = formComponent;
					i++;
				}
				addRow(formComponents);
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
