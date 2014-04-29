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

import org.vectomatic.dom.svg.ui.SVGResource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
    public interface Styles extends CssResource {
        String height();

        String clearfix();

        String wrapper();

        String inbl();

        String right();

        String btnSlider();

        String btnRafraichir();

        String btn();

        String main();

        String txtcenter();

        String vote();

        String jccq();

        String bgBleu();

        String btnVote();

        String selection();

        String btnConfirm();

        String voteHeader();

        String mainContainer();

        String logoArcbees();

        String logoJccq();
    }

    @Source("styles.css")
    Styles styles();

    SVGResource logoArcbees();

    SVGResource logoJccq();

    SVGResource voteHeader();

    ImageResource vote();

    ImageResource voteBatman();

    ImageResource voteNinja();

    ImageResource voteClock();

    ImageResource slider();

    ImageResource reload();

    ImageResource reloadHover();

    ImageResource oeil();

    ImageResource radio();

    ImageResource oeilHover();

    ImageResource voteAccent();

    @Source("brandongrotesque/brandon_bld-webfont.eot")
    DataResource brandonGrotesqueBoldEot();

    @Source("brandongrotesque/brandon_bld-webfont.svg")
    DataResource brandonGrotesqueBoldSvg();

    @Source("brandongrotesque/brandon_bld-webfont.ttf")
    DataResource brandonGrotesqueBoldTtf();

    @Source("brandongrotesque/brandon_bld-webfont.woff")
    DataResource brandonGrotesqueBoldWoff();

    @Source("brandongrotesque/brandon_light-webfont.eot")
    DataResource brandonGrotesqueLightEot();

    @Source("brandongrotesque/brandon_light-webfont.svg")
    DataResource brandonGrotesqueLightSvg();

    @Source("brandongrotesque/brandon_light-webfont.ttf")
    DataResource brandonGrotesqueLightTtf();

    @Source("brandongrotesque/brandon_light-webfont.woff")
    DataResource brandonGrotesqueLightWoff();

    @Source("brandongrotesque/brandon_reg-webfont.eot")
    DataResource brandonGrotesqueRegEot();

    @Source("brandongrotesque/brandon_reg-webfont.svg")
    DataResource brandonGrotesqueRegSvg();

    @Source("brandongrotesque/brandon_reg-webfont.ttf")
    DataResource brandonGrotesqueRegTtf();

    @Source("brandongrotesque/brandon_reg-webfont.woff")
    DataResource brandonGrotesqueRegWoff();

}
