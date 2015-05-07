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

package com.arcbees.bourseje.client.resources;
import com.arcbees.gsss.grid.client.GridResources;

import org.vectomatic.dom.svg.ui.SVGResource;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends FontResources {
    interface Normalize extends CssResource {
    }

    public interface Styles extends CssResource {
        String clearfix();

        String wrapper();

        String btn();

        String txtcenter();

        String jccq();

        String btn_wide();

        String container();

        String logoArcbees();

        String logoJccq();

        String logo_vote();

        String wrapper_desktop();

        String text_step();

        String text_main();

        String main_content();

        String main_section();

        String logo_vote_desktop();

        String main_content_desktop();
    }

    @Source("img/logoArcbees.svg")
    SVGResource logoArcbees();

    @Source("img/logoJccq.svg")
    SVGResource logoJccq();

    @Source("img/icon_app.png")
    ImageResource iconApp();

    @Source("img/icon_winner.png")
    ImageResource iconWinner();

    @Source("img/icon_vote.png")
    ImageResource iconVote();

    @Source("img/voteAccent.png")
    ImageResource voteAccent();

    @Source("img/DominicFillion.jpg")
    ImageResource DominicFillion();

    @Source("img/JohanieGagnon.jpg")
    ImageResource JohanieGagnon();

    @Source("img/MaximeGagnon.jpg")
    ImageResource MaximeGagnon();

    @Source("img/RaphaelProvost.jpg")
    ImageResource RaphaelProvost();

    @Source("img/SimonValin.jpg")
    ImageResource SimonValin();

    @Source("img/VincentBouchard.jpg")
    ImageResource VincentBouchard();

    @Source("css/normalize.gss")
    Normalize normalize();

    @Source({"com/arcbees/gsss/mixin/client/mixins.gss",
            "fonts/brandon.gss",
            "css/colors.gss",
            "fonts/fonts.gss",
            "css/styles.gss"})
    Styles styles();

    @Source({"com/arcbees/bourseje/client/resources/css/gridSettings.gss",
            "com/arcbees/gsss/grid/client/grid.gss"})
    GridResources.Grid grid();
}
