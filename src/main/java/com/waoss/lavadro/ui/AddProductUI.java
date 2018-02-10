package com.waoss.lavadro.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

@SpringUI(path = "/addProduct")
@Theme("valo")
public class AddProductUI extends UI{

    @Override
    protected void init(final VaadinRequest request) {

    }

    @WebServlet(urlPatterns = "/addProduct", name = "AddProductServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AddProductUI.class, productionMode = false)
    public static class AddProductServlet extends SpringVaadinServlet {

    }
}
