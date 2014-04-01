package com.arcbees.bourseje.client.application;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;


public class Header implements IsWidget {
    interface Binder extends UiBinder<HTMLPanel, Header> {
    }

    private static Binder binder = GWT.create(Binder.class);

    private final Widget widget;

    public Header() {
        widget = binder.createAndBindUi(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }
}