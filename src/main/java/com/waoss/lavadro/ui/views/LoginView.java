package com.waoss.lavadro.ui.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.waoss.lavadro.model.user.User;
import com.waoss.lavadro.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static com.waoss.lavadro.ui.LavadroUI.navigator;
import static com.waoss.lavadro.ui.views.ViewConstants.HOME_VIEW;

@SpringComponent
@UIScope
public class LoginView extends FormLayout implements View {

    private TextField username = new TextField("username");
    private PasswordField passwordField = new PasswordField("password");
    private Button loginButton = new Button("Login");
    private VerticalLayout parentLayout;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        setSizeFull();
        username.setIcon(VaadinIcons.USER);
        username.setRequiredIndicatorVisible(true);

        passwordField.setIcon(VaadinIcons.PASSWORD);

        loginButton.addClickListener((Button.ClickListener) event1 -> {
            navigator.navigateTo(HOME_VIEW);
            User user = new User(username.getValue(), passwordField.getValue());
            userRepository.save(user);
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("user", user);
            Notification.show("User inserted in database");
        });
        parentLayout = new VerticalLayout(this);
        parentLayout.setComponentAlignment(this, Alignment.MIDDLE_CENTER);
        addComponent(username);
        addComponent(passwordField);
        addComponent(loginButton);
        UI.getCurrent().setContent(parentLayout);
    }
}
