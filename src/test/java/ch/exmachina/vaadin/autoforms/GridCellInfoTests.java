package ch.exmachina.vaadin.autoforms;

import com.vaadin.ui.TextField;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * @autor Marco Manzi
 */
public class GridCellInfoTests {

	@Test
	public void gridInfoForOneFieldWithLabelOnRow() {
		FormComponent one = new FormField("test", TextField.class);
		GridCellInfoBuilder builder = new GridCellInfoBuilder(Arrays.asList(one));
		assertEquals(1, builder.build().size());
	}

	@Test
	public void gridInfoForTwoFieldWithLabelOnRow() {
		FormComponent one = new FormField("test", TextField.class);
		FormComponent two = new FormField("test", TextField.class);
		GridCellInfoBuilder builder = new GridCellInfoBuilder(Arrays.asList(one, two));
		assertEquals(2, builder.build().size());
	}

	@Test
	public void gridInfoForTwoFieldOneWithoutLabeOnRow() {
		FormComponent one = new FormField("test", TextField.class);
		FormComponent two = new FormField("", TextField.class);
		GridCellInfoBuilder builder = new GridCellInfoBuilder(Arrays.asList(one, two));
		assertEquals(1, builder.build().size());
		assertEquals(2, builder.build().get(0).numberOfComponentsInCell());
	}

	@Test
	public void gridInfoForOneFieldAndOneButtonOnRow() {
		FormComponent one = new FormField("test", TextField.class);
		FormComponent two = new FormButton("save", null);
		GridCellInfoBuilder builder = new GridCellInfoBuilder(Arrays.asList(one, two));
		assertEquals(1, builder.build().size());
		assertEquals(2, builder.build().get(0).numberOfComponentsInCell());
	}
}
