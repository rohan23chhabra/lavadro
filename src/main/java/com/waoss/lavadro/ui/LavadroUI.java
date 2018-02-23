package com.waoss.lavadro.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.waoss.lavadro.ui.views.HomeView;
import com.waoss.lavadro.ui.views.LoginView;
import org.springframework.beans.factory.annotation.Autowired;

import static com.waoss.lavadro.ui.views.ViewConstants.HOME_VIEW;
import static com.waoss.lavadro.ui.views.ViewConstants.LOGIN_VIEW;


@SpringUI(path = "/lavadroUI")
@Theme("valo")
public class LavadroUI extends UI {

    public static Navigator navigator;

    @Autowired
    private HomeView homeView;

    @Autowired
    private LoginView loginView;

    @Override
    protected void init(final VaadinRequest request) {
        getPage().setTitle("Lavadro");
        navigator = new Navigator(this, this);
        navigator.addView(HOME_VIEW, homeView);
        navigator.addView(LOGIN_VIEW, loginView);
        navigator.navigateTo(LOGIN_VIEW);
    }
}
