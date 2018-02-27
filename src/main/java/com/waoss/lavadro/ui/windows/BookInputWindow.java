package com.waoss.lavadro.ui.windows;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.waoss.lavadro.model.product.Book;
import com.waoss.lavadro.model.product.BookRepository;
import com.waoss.lavadro.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SpringComponent
@UIScope
public class BookInputWindow extends Window {

    FormLayout layout = new FormLayout();
    TextField name = new TextField("Name");
    TextField author = new TextField("Author");
    TextField pages = new TextField("Pages");
    TextField subject = new TextField("Subject");
    TextField price = new TextField("Price");
    Button submit = new Button("Submit");

    private final BookRepository bookRepository;

    @Autowired
    public BookInputWindow(final BookRepository bookRepository) {
        super("Add Book");
        this.bookRepository = bookRepository;
        center();
        VerticalLayout outerLayout = new VerticalLayout(layout);
        outerLayout.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
        layout.addComponent(name);
        layout.addComponent(author);
        layout.addComponent(pages);
        layout.addComponent(subject);
        layout.addComponent(price);
        layout.addComponent(submit);
        setContent(outerLayout);

        submit.addClickListener((Button.ClickListener) event -> {
            Binder<Book> bookBinder = new Binder<>(Book.class);
            bookBinder.forField(pages)
                    .withConverter(new StringToIntegerConverter("Must enter a number"))
                    .bind(Book::getPages, Book::setPages);
            bookBinder.forField(price)
                    .withConverter(new StringToLongConverter("Must enter a (Long) integer"))
                    .bind(Book::getPrice, Book::setPrice);
            bookBinder.bindInstanceFields(BookInputWindow.this);
            Book book = new Book();
            try {
                bookBinder.writeBean(book);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
            book.setUser((User) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("user"));
            book.setUploadTime(LocalDateTime.now());
            bookRepository.save(book);
            Notification.show("Book added");
        });
    }
}
