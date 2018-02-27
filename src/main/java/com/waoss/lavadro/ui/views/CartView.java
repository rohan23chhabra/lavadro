package com.waoss.lavadro.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.waoss.lavadro.model.product.Product;
import com.waoss.lavadro.model.transaction.cart.Cart;
import com.waoss.lavadro.model.transaction.cart.CartRepository;
import com.waoss.lavadro.model.user.User;
import com.waoss.lavadro.ui.layout.ProductLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@SpringView(name = CartView.NAME)
public class CartView extends VerticalLayout implements View {

    public static final String NAME = "cartView";
    @Autowired
    private CartRepository cartRepository;
    private Button logoutButton = new Button("Logout");

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        logoutButton.addClickListener((Button.ClickListener) event2 -> logoutActionPerformed());
        ProductLayout.setCartRepository(cartRepository);
        User user = (User) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("user");
        Cart cart = user.getCart();
        Set<Product> cartProducts = cart.getProducts();
        cartProducts.forEach(e -> addComponent(new ProductLayout(e, true)));
        VerticalLayout parentLayout = new VerticalLayout(this);
        parentLayout.addComponent(logoutButton);
        parentLayout.setComponentAlignment(this, Alignment.MIDDLE_CENTER);
        UI.getCurrent().setContent(parentLayout);
    }

    private void logoutActionPerformed() {
        getUI().getSession().close();
        VaadinService.getCurrentRequest().getWrappedSession().invalidate();
        getUI().getNavigator().navigateTo(LoginView.NAME);
    }
}
