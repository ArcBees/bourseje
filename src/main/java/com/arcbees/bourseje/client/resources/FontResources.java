/**
 * Copyright 2015 ArcBees Inc.
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

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;

public interface FontResources extends ClientBundle {
    String MIME_TYPE_TTF = "application/font-sfnt";
    String MIME_TYPE_EOT = "application/vnd.ms-fontobject";
    String MIME_TYPE_WOFF = "application/font-woff";
    String MIME_TYPE_SVG = "image/svg+xml";

    @DataResource.MimeType(MIME_TYPE_TTF)
    @Source("fonts/brandon_bld-webfont.ttf")
    DataResource brandonGrotesqueBoldTtf();

    @DataResource.MimeType(MIME_TYPE_EOT)
    @Source("fonts/brandon_bld-webfont.eot")
    DataResource brandonGrotesqueBoldEot();

    @DataResource.MimeType(MIME_TYPE_SVG)
    @Source("fonts/brandon_bld-webfont.svg")
    DataResource brandonGrotesqueBoldSvg();

    @DataResource.MimeType(MIME_TYPE_WOFF)
    @Source("fonts/brandon_bld-webfont.woff")
    DataResource brandonGrotesqueBoldWoff();

    @DataResource.MimeType(MIME_TYPE_TTF)
    @Source("fonts/brandon_light-webfont.ttf")
    DataResource brandonGrotesqueLightTtf();

    @DataResource.MimeType(MIME_TYPE_EOT)
    @Source("fonts/brandon_light-webfont.eot")
    DataResource brandonGrotesqueLightEot();

    @DataResource.MimeType(MIME_TYPE_SVG)
    @Source("fonts/brandon_light-webfont.svg")
    DataResource brandonGrotesqueLightSvg();

    @DataResource.MimeType(MIME_TYPE_WOFF)
    @Source("fonts/brandon_light-webfont.woff")
    DataResource brandonGrotesqueLightWoff();

    @DataResource.MimeType(MIME_TYPE_TTF)
    @Source("fonts/brandon_reg-webfont.ttf")
    DataResource brandonGrotesqueRegTtf();

    @DataResource.MimeType(MIME_TYPE_EOT)
    @Source("fonts/brandon_reg-webfont.eot")
    DataResource brandonGrotesqueRegEot();

    @DataResource.MimeType(MIME_TYPE_SVG)
    @Source("fonts/brandon_reg-webfont.svg")
    DataResource brandonGrotesqueRegSvg();

    @DataResource.MimeType(MIME_TYPE_WOFF)
    @Source("fonts/brandon_reg-webfont.woff")
    DataResource brandonGrotesqueRegWoff();
}
