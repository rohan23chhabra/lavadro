package com.waoss.lavadro.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

@SpringView
public class LogoutView extends CssLayout implements View {

    public static final String NAME = "logoutView";

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        addComponent(new Label("You have been logged out of Lavadro."));
        UI.getCurrent().setContent(this);
    }
}
