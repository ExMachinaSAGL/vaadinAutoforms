package ch.exmachina.vaadin.autoforms.showcase;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "it.alpitour.vaadinforms.showcase.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();

        layout.setMargin(true);
        setContent(layout);
		layout.addComponent(new Label("Form Creator ShowCase"));

		layout.addComponent(new FourFieldOnTwoRowsForm().getMainLayout());
		layout.addComponent(new ThreeFieldsTwoRowsWithTwoOnOneRowForm().getMainLayout());
		layout.addComponent(new OneFieldWithOkAndCancelButton().getMainLayout());
    }

}
