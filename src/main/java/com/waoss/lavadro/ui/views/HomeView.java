package com.waoss.lavadro.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.waoss.lavadro.ui.windows.ProductChooseWindow;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = HomeView.NAME)
public class HomeView extends CssLayout implements View {
    private Button addProductButton = new Button("Add Product");
    private Button viewProductsButton = new Button("View Products");
    public static final String NAME = "homeView";
    private VerticalLayout parentLayout;
    private Button logoutButton = new Button("Logout");



    @Autowired
    private ProductChooseWindow productChooseWindow;

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setSizeFull();
        productChooseWindow.setParentUI(UI.getCurrent());
        addProductButton.addClickListener(
                (Button.ClickListener) event -> UI.getCurrent().addWindow(productChooseWindow));
        viewProductsButton
                .addClickListener((Button.ClickListener) event -> getUI().getNavigator().navigateTo(ProductsView.NAME));
        logoutButton.addClickListener((Button.ClickListener) event -> logoutActionPerformed());
        addComponent(addProductButton);
        addComponent(viewProductsButton);
        addComponent(logoutButton);
        parentLayout = new VerticalLayout(this);
        parentLayout.setComponentAlignment(this, Alignment.MIDDLE_CENTER);
        UI.getCurrent().setContent(parentLayout);
    }

    private void logoutActionPerformed() {
        getUI().getNavigator().navigateTo(LoginView.NAME);
        getUI().getSession().close();
        VaadinService.getCurrentRequest().getWrappedSession().invalidate();

    }
}
