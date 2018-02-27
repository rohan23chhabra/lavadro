package com.waoss.lavadro.ui.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.waoss.lavadro.model.user.User;
import com.waoss.lavadro.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = LoginView.NAME)
public class LoginView extends FormLayout implements View {

    private TextField username = new TextField("username");
    private PasswordField passwordField = new PasswordField("password");
    private Button loginButton = new Button("Login");
    private VerticalLayout parentLayout;
    public static final String NAME = "loginView";
    @Autowired
    private UserRepository userRepository;

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        setSizeFull();
        username.setIcon(VaadinIcons.USER);
        username.setRequiredIndicatorVisible(true);

        passwordField.setIcon(VaadinIcons.PASSWORD);

        loginButton.addClickListener((Button.ClickListener) event1 -> {
            getUI().getNavigator().navigateTo(HomeView.NAME);
            User user = new User(username.getValue(), passwordField.getValue());
            user = userRepository.save(user);
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
