package com.waoss.lavadro.ui.widgets;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;

@Deprecated
public class ButtonLink extends Label {
    public ButtonLink(final String caption, final ExternalResource externalResource, String width) {
        super("<a href='"+externalResource.getURL()+"'>" +
                        // The following lines are copy pasted from rendered Vaadin v6.1 buttons.
                        "<div class='v-button' tabindex='0'>" +
                        "<span class='v-button-wrap'>" +
                        "<span class='v-button-caption'>"+
                        caption +
                        "</span>"+
                        "</span>"+
                        "</div>"+
                        "</a>",
                ContentMode.HTML);
        setWidth(width);
    }
}
