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

import com.arcbees.bourseje.client.resources.Resources;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.plugins.effects.PropertiesAnimation.EasingCurve;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.gwtplatform.mvp.client.ViewImpl;

import static com.google.gwt.query.client.GQuery.$;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    private final SimplePanel main;

    @Inject
    ApplicationView(Resources resources) {
        main = new SimplePanel();
        main.setStyleName(resources.styles().container());

        initWidget(main);
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == ApplicationPresenter.SLOT_MAIN) {
            show(content);
        }
    }

    private void show(final IsWidget content) {
        Window.scrollTo(0, 0);

        $(content).hide();

        if (main.getWidget() == null) {
            setAndShow(content);
        } else {
            $(main.getWidget()).animate("opacity: 'hide'", 150, EasingCurve.easeInOut, new Function() {
                @Override
                public void f() {
                    $(main).scrollTop(0);
                    setAndShow(content);
                }
            });
        }
    }

    private void setAndShow(IsWidget w) {
        main.setWidget(w);
        $(w).animate("opacity: 'show'", 150, EasingCurve.easeInOut);
    }
}
