package com.waoss.lavadro.ui.windows;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

@EqualsAndHashCode(callSuper = true)
@SpringComponent
@UIScope
@Data
public class ProductChooseWindow extends Window {

    private UI parentUI;

    private final BookInputWindow bookInputWindow;
    private final CycleInputWindow cycleInputWindow;

    @Autowired
    public ProductChooseWindow(BookInputWindow bookInputWindow, CycleInputWindow cycleInputWindow) {
        super("Choose Product");
        this.bookInputWindow = bookInputWindow;
        this.cycleInputWindow = cycleInputWindow;
        center();
        final FormLayout formLayout = new FormLayout();
        final VerticalLayout layout = new VerticalLayout(formLayout);
        layout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
        final Button bookButton = new Button("Book");
        formLayout.addComponent(bookButton);
        final Button cycleButton = new Button("Cycle");
        formLayout.addComponent(cycleButton);
        setContent(layout);
        bookButton.addClickListener((Button.ClickListener) event -> parentUI.addWindow(bookInputWindow));
        cycleButton.addClickListener((Button.ClickListener) event -> parentUI.addWindow(cycleInputWindow));
    }
}
