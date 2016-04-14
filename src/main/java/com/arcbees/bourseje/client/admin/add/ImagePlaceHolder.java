package com.arcbees.bourseje.client.admin.add;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

public class ImagePlaceHolder extends FocusPanel {
    interface Binder extends UiBinder<Widget, ImagePlaceHolder> {
    }

    private static final Binder uiBinder = GWT.create(Binder.class);

    @UiField
    ImageElement image;

    public ImagePlaceHolder() {
        setWidget(uiBinder.createAndBindUi(this));
    }

    public void setImageSource(String imageUrl) {
        image.setSrc(imageUrl);
    }
}
