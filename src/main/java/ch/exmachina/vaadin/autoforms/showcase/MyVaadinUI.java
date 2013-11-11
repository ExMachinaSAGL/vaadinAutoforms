package ch.exmachina.vaadin.autoforms.showcase;

import ch.exmachina.vaadin.autoforms.FormCreator;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

	private List<FormCreator> formsInShowcase = new ArrayList<FormCreator>() {
		{
			add(new OneFieldOnFirstRow());
//			add(new FourFieldOnTwoRowsForm());
//			add(new ThreeFieldsTwoRowsWithTwoOnOneRowForm());
//			add(new OneFieldWithOkAndCancelButton());
		}
	};

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "ch.exmachina.vaadin.autoforms.showcase.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();

        layout.setMargin(true);
        setContent(layout);
		layout.addComponent(new Label("Form Creator ShowCase"));

		for (FormCreator form: formsInShowcase) {
			layout.addComponent(form.getMainLayout());
		}
		Button commitButton = new Button("Commit");
		commitButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				for (FormCreator form: formsInShowcase) {
					form.commit();
				}
			}
		});
		layout.addComponent(commitButton);
    }

}
