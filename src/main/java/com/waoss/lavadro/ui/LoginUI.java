package com.waoss.lavadro.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.LayoutEvents;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.*;
import com.waoss.lavadro.model.user.User;
import com.waoss.lavadro.model.user.UserRepository;
import com.waoss.lavadro.ui.widgets.ButtonLink;
import com.waoss.lavadro.ui.widgets.LinkButton;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;

@SpringUI(path = "/login")
@Theme("valo")
public class LoginUI extends UI {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomePageUI homePageUI;

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

        experiment(form);

        BrowserWindowOpener browserWindowOpener = new BrowserWindowOpener(
                new ExternalResource("localhost:8080/home"));
        browserWindowOpener.setFeatures("");
        browserWindowOpener.extend(loginButton);
        loginButton.addClickListener((Button.ClickListener) event -> {
            //getUI().getPage().setLocation("localhost:8080/home");
            browserWindowOpener.setWindowName("_self");
            User user = new User(username.getValue(), passwordField.getValue());
            userRepository.save(user);
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("user", user);
            Notification.show("User inserted in database");
        });
        form.addComponent(loginButton);
        VerticalLayout layout = new VerticalLayout(form);
        layout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/login", asyncSupported = true, name = "SampleUIServlet")
    @VaadinServletConfiguration(ui = LoginUI.class, productionMode = false)
    public static class SampleUIServlet extends SpringVaadinServlet {

    }

    private void experiment(FormLayout form) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        final Link link = new Link("Click", new ExternalResource("localhost:8080/home"));
        horizontalLayout.addComponent(link);
        horizontalLayout.addLayoutClickListener((LayoutEvents.LayoutClickListener) event -> {
            if(event.getClickedComponent().equals(link)) {
                Notification.show("Balle balle");
                getUI().getPage().open("http://www.google.com", "_blank");
            }
        });
        form.addComponent(horizontalLayout);
    }
}
