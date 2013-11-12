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
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test", TextField.class).width(100).build());
			}
		});
		testFieldLabel(created, "test", 0, 15);
		testField(created, 15, 100);
	}

	@Test
	public void oneFielWithLabelOnTopdForm() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test", TextField.class).width(100).
						labelPosition(FormField.LABEL_POSITION.TOP_LEFT).build());
			}
		});
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
		});
		testFieldLabel(created, "test1", 0, 15);
		testField(created, 15, 50);
		testFieldLabel(created, "test2", 50, 65);
		testField(created, 65, 99);
	}

	@Test
	public void testOneFieldAutomatic() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test", TextField.class).build());
			}
		});
		testFieldLabel(created, "test", 0, 15);
		testField(created, 15, 100);
	}


	@Test
	public void testTwoFieldWithFieldAutomatic() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).build());
				add(new FormFieldBuilder("test2", TextField.class).build());
			}
		});
		testFieldLabel(created, "test1", 0, 15);
		testField(created, 15, 50);
		testFieldLabel(created, "test2", 50, 65);
		testField(created, 65, 100);
	}

	@Test
	public void testOneFieldWithMargin() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).width(50).marginLeft(30).build());
			}
		});
		testFieldLabel(created, "test1", 30, 45);
		testField(created, 45, 80);
	}

	@Test
	public void testOneFieldWithMarginAndOneButton() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList() {
			{
				add(new FormFieldBuilder("test1", TextField.class).width(30).marginLeft(10).build());
				add(new FormButton("testButton", null, 10));
				add(new FormFieldBuilder("test2", TextField.class).build());
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
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).width(50).marginLeft(30).build());
				add(new FormFieldBuilder("test2", TextField.class).width(20).build());
			}
		});
		testFieldLabel(created, "test1", 30, 45);
		testField(created, 45, 80);
		testFieldLabel(created,	"test2", 90, 95);
		testField(created, 95, 100);
	}

	@Test
	public void testTwoFieldOneAutomaticWithMargin() {
		GridLayout created = testPercentGridRendererForFormWithField(new ArrayList<FormComponent>() {
			{
				add(new FormFieldBuilder("test1", TextField.class).width(50).marginLeft(30).build());
				add(new FormFieldBuilder("test2", TextField.class).build());
			}
		});
		testFieldLabel(created, "test1", 30, 45);
		testField(created, 45, 80);
		testFieldLabel(created,	"test2", 90, 95);
		testField(created, 95, 100);
	}

	@Test
	public void testLabelWidth() {

		FormCreator formCreator = new FormCreator(new PercentGridRendered(3)) {
			@Override
			protected void beforeRendering() {}

			@Override
			protected void initFields() {
				addRow(new FormFieldBuilder("test1", TextField.class).width(10).build());
			}
		};
		GridLayout created = new PercentGridRendered(3).render(formCreator);
		testFieldLabel(created, "test1", 0, 3);
		testField(created, 3, 10);
	}


	private GridLayout testPercentGridRendererForFormWithField(ArrayList<FormComponent> fields) {
		FormCreator formCreator = createTestForm(fields);
		return new PercentGridRendered().render(formCreator);
	}

	private FormCreator createTestForm(final List<FormComponent> testFields) {
		return new FormCreator(new PercentGridRendered()) {
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
//				addRow(testFields.toArray(new FormComponent[testFields.size()]));
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
