package com.waoss.lavadro.ui.layout;

import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.waoss.lavadro.model.product.*;
import com.waoss.lavadro.model.transaction.cart.Cart;
import com.waoss.lavadro.model.transaction.cart.CartRepository;
import com.waoss.lavadro.model.user.User;
import com.waoss.lavadro.model.user.UserRepository;
import com.waoss.lavadro.ui.views.CartView;

import java.time.LocalDateTime;

public class ProductLayout extends VerticalLayout {
    private static CartRepository cartRepository;
    private static UserRepository userRepository;
    private final Product product;
    private final HorizontalLayout rootLayout = new HorizontalLayout();
    private final VerticalLayout upperLayout = new VerticalLayout();
    private final VerticalLayout lowerLayout = new VerticalLayout();
    private final FormLayout leftPane = new FormLayout();
    private final FormLayout rightPane = new FormLayout();
    private final Label productTypeLabel = new Label();
    private final Label offeredByField = new Label();
    private final Label uploadTimeField = new Label();
    private final Label name = new Label();
    private final Label price = new Label();
    private final Label author = new Label();
    private final Label pages = new Label();
    private final Label subject = new Label();
    private final Label model = new Label();
    private final Label company = new Label();
    private final Button addToCartButton = new Button();
    private final boolean isCartView;

    public ProductLayout(final Product product, final boolean isCartView) {
        this.product = product;
        this.isCartView = isCartView;
        initLayout();
        if (product.getClass().isAssignableFrom(Book.class)) {
            productTypeLabel.setValue("Book");
            Book book = (Book) product;
            author.setValue("Author : " + book.getAuthor());
            pages.setValue("Pages : " + book.getPages());
            subject.setValue("Subject : " + book.getSubject());
            rightPane.addComponents(author, pages, subject);

        } else if (product.getClass().isAssignableFrom(Cycle.class)) {
            productTypeLabel.setValue("Cycle");
            Cycle cycle = (Cycle) product;
            model.setValue("Model : " + cycle.getModel());
            company.setValue("Company : " + cycle.getCompany());
            rightPane.addComponents(model, company);
        } else {

        }

    }


    private void initLayout() {
        rootLayout.addComponents(upperLayout, lowerLayout);
        upperLayout.addComponents(leftPane, rightPane);
        upperLayout.setComponentAlignment(leftPane, Alignment.MIDDLE_CENTER);
        upperLayout.setComponentAlignment(rightPane, Alignment.MIDDLE_CENTER);
        configureButtons();
        lowerLayout.addComponent(addToCartButton);
        leftPane.addComponents(productTypeLabel, offeredByField, uploadTimeField);
        rightPane.addComponents(name, price);
        name.setValue(product.getName());
        price.setValue(product.getPrice() + "");
        offeredByField.setValue("Offered By : " + product.getUser().getUsername());
        LocalDateTime localDateTime = product.getUploadTime();
        uploadTimeField.setValue("Uploaded at\n  Time : " + localDateTime.getHour() +
                ":" + localDateTime.getMinute()
                + ":" + localDateTime.getSecond() + "\n Day : " + localDateTime.getDayOfWeek() + "\n Year : " +
                localDateTime.getYear());

        addComponent(rootLayout);
    }

    private void configureButtons() {
        User user = (User) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("user");
        Cart cart = user.getCart();

        if (isCartView) {
            addToCartButton.setCaption("Remove from Cart");
            addToCartButton.addClickListener((Button.ClickListener) event -> removeFromCartButtonAction(user, cart));
        } else {
            addToCartButton.setCaption("Add to Cart");
            addToCartButton.addClickListener((Button.ClickListener) event -> addToCartButtonAction(user, cart));
        }
    }

    private void removeFromCartButtonAction(User user, Cart cart) {
        cartRepository.deleteProductFromCart(cart.getId());
        cart.getProducts().remove(product);
        user.setCart(cart);
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("user", user);
        Notification notification = new Notification("Removed from cart");
        notification.setDelayMsec(3000);
        notification.show(UI.getCurrent().getPage());
        getUI().getNavigator().navigateTo(CartView.NAME);
    }

    private void addToCartButtonAction(User user, Cart cart) {

        if (cart == null) {
            cart = new Cart();
            cart = cartRepository.save(cart);
            cartRepository.updateUserIdByCartId(user.getId(), cart.getId());
            userRepository.updateCartIdByUserId(cart.getId(), user.getId());
            cart.setUser(user);
        }
        cart.getProducts().add(product);
        cartRepository.insertIntoCartProducts(cart.getId(), product.getId());
        user.setCart(cart);
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("user", user);
        Notification notification = new Notification("Added to cart");
        notification.setDelayMsec(3000);
        notification.show(UI.getCurrent().getPage());
    }

    public static CartRepository getCartRepository() {
        return cartRepository;
    }

    public static void setCartRepository(final CartRepository cartRepository) {
        ProductLayout.cartRepository = cartRepository;
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static void setUserRepository(final UserRepository userRepository) {
        ProductLayout.userRepository = userRepository;
    }
}
