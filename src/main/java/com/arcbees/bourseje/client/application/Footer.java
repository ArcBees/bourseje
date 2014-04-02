package com.arcbees.bourseje.client.application;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;


public class Footer implements IsWidget {
    interface Binder extends UiBinder<HTMLPanel, Footer> {
    }

    private static Binder binder = GWT.create(Binder.class);

    private final Widget widget;

    public Footer() {
        widget = binder.createAndBindUi(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }
}
