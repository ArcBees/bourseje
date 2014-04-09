/**
 * Copyright 2014 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.bourseje.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.gwtplatform.mvp.client.ViewImpl;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    static private boolean firstLoad = true;

    @UiField
    SimplePanel main;

    private final AnimationHelper animationHelper;

    @Inject
    ApplicationView(
            Binder binder,
            AnimationHelper animationHelper) {
        this.animationHelper = animationHelper;

        initWidget(binder.createAndBindUi(this));

        main.setWidget(animationHelper);
    }


    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == ApplicationPresenter.SLOT_MAIN) {
            if (firstLoad) {
                animationHelper.goTo(content, Animation.FADE);

                firstLoad = false;
            } else {
                animationHelper.goTo(content, Animation.SLIDE);
            }
        }
    }
}
