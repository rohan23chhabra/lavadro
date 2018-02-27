package com.waoss.lavadro.ui.windows;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.waoss.lavadro.model.product.Cycle;
import com.waoss.lavadro.model.product.CycleRepository;
import com.waoss.lavadro.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SpringComponent
@UIScope
public class CycleInputWindow extends Window {
    FormLayout formLayout = new FormLayout();
    TextField name = new TextField("Name");
    TextField price = new TextField("Price");
    TextField model = new TextField("Model");
    TextField company = new TextField("Company");
    Button submit = new Button("Submit");

    private final CycleRepository cycleRepository;

    @Autowired
    public CycleInputWindow(final CycleRepository cycleRepository) {
        super("Add Cycle");
        this.cycleRepository = cycleRepository;
        center();
        final VerticalLayout verticalLayout = new VerticalLayout(formLayout);
        verticalLayout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
        formLayout.addComponent(name);
        formLayout.addComponent(price);
        formLayout.addComponent(model);
        formLayout.addComponent(company);
        formLayout.addComponent(submit);
        setContent(verticalLayout);

        submit.addClickListener((Button.ClickListener) event -> {
            Binder<Cycle> cycleBinder = new Binder<>(Cycle.class);
            cycleBinder.forField(price)
                    .withConverter(new StringToLongConverter("Only enter (long) integer"))
                    .bind(Cycle::getPrice, Cycle::setPrice);
            cycleBinder.bindInstanceFields(CycleInputWindow.this);
            Cycle cycle = new Cycle();
            try {
                cycleBinder.writeBean(cycle);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
            cycle.setUser((User) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("user"));
            cycle.setUploadTime(LocalDateTime.now());
            cycleRepository.save(cycle);
            Notification.show("Cycle added");
        });
    }
}
