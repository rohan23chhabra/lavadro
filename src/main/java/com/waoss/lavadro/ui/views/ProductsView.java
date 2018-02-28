package com.waoss.lavadro.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.waoss.lavadro.model.product.Product;
import com.waoss.lavadro.model.product.ProductRepository;
import com.waoss.lavadro.model.transaction.cart.CartRepository;
import com.waoss.lavadro.model.user.User;
import com.waoss.lavadro.model.user.UserRepository;
import com.waoss.lavadro.ui.layout.ProductLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@SpringView(name = ProductsView.NAME)
public class ProductsView extends CssLayout implements View {
    public static final String NAME = "productsView";
    private List<ProductLayout> productLayouts = new ArrayList<>();
    @Autowired
    private ProductRepository<? extends Product> productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    private Button viewCartButton = new Button("View Cart");
    private Button logoutButton = new Button("Logout");

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        ProductLayout.setCartRepository(cartRepository);
        ProductLayout.setUserRepository(userRepository);
        User user = (User) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("user");
        productRepository.findNotByUser(user.getId()).forEach(e -> productLayouts.add(new ProductLayout(e, false)));
        productLayouts.forEach(ProductsView.this::addComponent);
        VerticalLayout parentLayout = new VerticalLayout(this);
        parentLayout.setCaption("Products Offered By Other Users");
        parentLayout.addComponent(viewCartButton);
        parentLayout.addComponent(logoutButton);
        viewCartButton
                .addClickListener((Button.ClickListener) event1 -> getUI().getNavigator().navigateTo(CartView.NAME));
        logoutButton.addClickListener((Button.ClickListener) event2 -> logoutActionPerformed());
        parentLayout.setComponentAlignment(viewCartButton, Alignment.MIDDLE_CENTER);
        parentLayout.setComponentAlignment(this, Alignment.MIDDLE_CENTER);
        UI.getCurrent().setContent(parentLayout);
    }

    private void logoutActionPerformed() {
        getUI().getNavigator().navigateTo(LoginView.NAME);
        VaadinService.getCurrentRequest().getWrappedSession().invalidate();
        getUI().getSession().close();
    }
}
