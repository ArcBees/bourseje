/**
 * Copyright 2016 ArcBees Inc.
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

package com.arcbees.bourseje.client.resources;

import com.arcbees.gsss.animation.client.AnimationResources;
import com.google.gwt.resources.client.ClientBundle;

public interface PageAddEditResources extends ClientBundle {
    interface Style extends AnimationResources.Animation {

        String addedit();

        String addedit__label();

        String addedit__input();

        String addedit__photo();

        String addedit__buttons();

        String addedit__button();

        String addedit__photo__placeholder();
    }

    @Source({"com/arcbees/gsss/animation/client/animationsettings.gss",
            "com/arcbees/gsss/animation/client/animations.gss",
            "com/arcbees/gsss/mixin/client/mixins.gss",
            "css/colors.gss",
            "fonts/fonts.gss",
            "css/pages/addedit.gss"})
    Style style();
}
