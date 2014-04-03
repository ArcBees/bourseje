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

package com.arcbees.bourseje.server.guice;

import java.util.Calendar;

import javax.inject.Named;
import javax.inject.Singleton;

import com.arcbees.bourseje.server.api.ApiModule;
import com.arcbees.bourseje.server.exception.ExceptionModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.googlecode.objectify.ObjectifyFilter;

public class ServerModule extends AbstractModule {
    public static final String POLL_DATE = "pollDate";

    @Override
    protected void configure() {
        bind(ObjectifyFilter.class).in(Singleton.class);

        install(new ApiModule());
        install(new ExceptionModule());
    }

    @Named(POLL_DATE)
    @Provides
    public Calendar pollDate() {
        Calendar pollDateTime = Calendar.getInstance();
        pollDateTime.set(2014, Calendar.APRIL, 4, 17, 0);

        return pollDateTime;
    }
}
