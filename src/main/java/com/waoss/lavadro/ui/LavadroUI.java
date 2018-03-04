package com.waoss.lavadro.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import com.waoss.lavadro.ui.views.LoginView;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI(path = "/lavadroUI/*")
@Theme("valo")
@PreserveOnRefresh
public class LavadroUI extends UI {

    @Autowired
    private SpringNavigator navigator;

    @Autowired
    private SpringViewProvider springViewProvider;

    @Override
    protected void init(final VaadinRequest request) {
        getPage().setTitle("Lavadro");
        navigator.init(this, this);
        navigator.addProvider(springViewProvider);
        setNavigator(navigator);
        navigator.navigateTo(LoginView.NAME);
    }


}
