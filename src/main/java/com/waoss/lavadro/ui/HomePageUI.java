package com.waoss.lavadro.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

@SpringUI(path = "/home")
@Theme(value = "valo")
public class HomePageUI extends UI {

    @Override
    protected void init(final VaadinRequest request) {
        CssLayout cssLayout = new CssLayout();
        Button addProductButton = new Button("Add Product");
        Button viewProductsButton = new Button("View Products");
        cssLayout.addComponent(addProductButton);
        cssLayout.addComponent(viewProductsButton);
        setContent(cssLayout);
    }


    @WebServlet(urlPatterns = "/home", name = "HomePageServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = HomePageUI.class, productionMode = false)
    public static class HomePageServlet extends SpringVaadinServlet {
    }
}
