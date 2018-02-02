package com.waoss.lavadro.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.*;
import com.waoss.lavadro.model.user.User;
import com.waoss.lavadro.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;

@SpringUI(path = "/login")
@Theme("valo")
public class LoginUI extends UI {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void init(final VaadinRequest request) {
        FormLayout form = new FormLayout();
        TextField username = new TextField("Name");
        username.setIcon(VaadinIcons.USER);
        username.setRequiredIndicatorVisible(true);
        form.addComponent(username);

        PasswordField passwordField = new PasswordField("Password");
        passwordField.setIcon(VaadinIcons.PASSWORD);
        form.addComponent(passwordField);

        Button loginButton = new Button("Login");

        BrowserWindowOpener browserWindowOpener = new BrowserWindowOpener(HomePageUI.class);
        browserWindowOpener.extend(loginButton);
        loginButton.addClickListener((Button.ClickListener) event -> {
            browserWindowOpener.setWindowName("_self");
            User user = new User(username.getValue(), passwordField.getValue());
            userRepository.save(user);
            Notification.show("User inserted in database");
        });
        form.addComponent(loginButton);

        setContent(form);
    }

    @WebServlet(urlPatterns = "/login", asyncSupported = true, name = "SampleUIServlet")
    @VaadinServletConfiguration(ui = LoginUI.class, productionMode = false)
    public static class SampleUIServlet extends SpringVaadinServlet {

    }
}
