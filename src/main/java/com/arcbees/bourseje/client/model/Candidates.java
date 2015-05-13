/*
 *
 *  * Copyright 2015 ArcBees Inc.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License. You may obtain a copy of
 *  * the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 *
 */

package com.arcbees.bourseje.client.model;

import java.util.List;

import com.arcbees.bourseje.client.resources.Resources;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;

public class Candidates {
    private static final Resources resources = GWT.create(Resources.class);

    public static final Candidate JOHANIE = new Candidate("Johanie Gagnon", "Construction le 5e élément", resources.JohanieGagnon());
    public static final Candidate DOMINIC = new Candidate("Dominic Fillion", "DFMotion", resources.DominicFillion());
    public static final Candidate RAPHAEL = new Candidate("Raphaël Provost", "iWrap", resources.RaphaelProvost());
    public static final Candidate MAXIME = new Candidate("Maxime Gagnon", "La Raffinerie", resources.MaximeGagnon());
    public static final Candidate SIMON = new Candidate("Simon Valin", "Valin Confection", resources.SimonValin());
    public static final Candidate VINCENT = new Candidate("Vincent Bouchard", "Venice Gym", resources.VincentBouchard());

    public static List<Candidate> getAll() {
        return Lists.newArrayList(
                JOHANIE,
                DOMINIC,
                RAPHAEL,
                MAXIME,
                SIMON,
                VINCENT);
    }
}
