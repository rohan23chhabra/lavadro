package com.waoss.lavadro.ui.widgets;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Deprecated
public class LinkButton extends Button {
    Link link;

    public LinkButton(final String caption, final ExternalResource externalResource) {
        super(caption);
        link = new Link(caption, externalResource);
    }


}
