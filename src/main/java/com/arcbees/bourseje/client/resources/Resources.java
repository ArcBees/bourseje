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

        String btnVotez();

        String btnRafraichir();

        String btn();

        String main();

        String txtcenter();

        String vote();

        String jccq();

        String btnVote();

        String selection();

        String btnConfirm();

        String voteHeader();

        String mainContainer();

        String logoArcbees();

        String logoJccq();

        String mainSection();
    }

    @Source("img/logoArcbees.svg")
    SVGResource logoArcbees();

    @Source("img/logoJccq.svg")
    SVGResource logoJccq();

    @Source("img/voteHeader.svg")
    SVGResource voteHeader();

    @Source("img/vote.png")
    ImageResource vote();

    @Source("img/voteBatman.png")
    ImageResource voteBatman();

    @Source("img/voteNinja.png")
    ImageResource voteNinja();

    @Source("img/voteClock.png")
    ImageResource voteClock();

    @Source("img/reload.png")
    ImageResource reload();

    @Source("img/reloadHover.png")
    ImageResource reloadHover();

    @Source("img/oeil.png")
    ImageResource oeil();

    @Source("img/radio.png")
    ImageResource radio();

    @Source("img/oeilHover.png")
    ImageResource oeilHover();

    @Source("img/voteAccent.png")
    ImageResource voteAccent();

    @Source("css/normalize.gss")
    Normalize normalize();

    @Source({"com/arcbees/gsss/mixin/client/mixins.gss",
            "fonts/brandon.gss",
            "css/colors.gss",
            "css/styles.gss"})
    Styles styles();

    @Source({"com/arcbees/bourseje/client/resources/css/gridSettings.gss",
            "com/arcbees/gsss/grid/client/grid.gss"})
    GridResources.Grid grid();
}
