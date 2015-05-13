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

import com.google.common.collect.Lists;

public class Candidates {
    public static final Candidate JOHANIE = new Candidate("Johanie Gagnon", "Construction le 5e élément");
    public static final Candidate DOMINIC = new Candidate("Dominic Fillion", "DFMotion");
    public static final Candidate RAPHAEL = new Candidate("Raphaël Provost", "iWrap");
    public static final Candidate MAXIME = new Candidate("Maxime Gagnon", "La Raffinerie");
    public static final Candidate SIMON = new Candidate("Simon Valin", "Valin Confection");
    public static final Candidate VINCENT = new Candidate("Vincent Bouchard", "Venice Gym");

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
