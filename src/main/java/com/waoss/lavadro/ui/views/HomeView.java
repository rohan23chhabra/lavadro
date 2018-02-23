package com.waoss.lavadro.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.waoss.lavadro.ui.windows.ProductChooseWindow;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class HomeView extends CssLayout implements View {
    private Button addProductButton = new Button("Add Product");
    private Button viewProductsButton = new Button("View Products");
    private VerticalLayout parentLayout;

    @Autowired
    private ProductChooseWindow productChooseWindow;

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setSizeFull();
        productChooseWindow.setParentUI(UI.getCurrent());
        addProductButton.addClickListener(
                (Button.ClickListener) event -> UI.getCurrent().addWindow(productChooseWindow));
        addComponent(addProductButton);
        addComponent(viewProductsButton);
        parentLayout = new VerticalLayout(this);
        parentLayout.setComponentAlignment(this, Alignment.MIDDLE_CENTER);
        UI.getCurrent().setContent(parentLayout);
    }
}
