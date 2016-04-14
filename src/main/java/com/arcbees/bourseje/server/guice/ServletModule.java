/*
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

package com.arcbees.bourseje.server.guice;

import com.arcbees.bourseje.server.upload.Serve;
import com.arcbees.bourseje.server.upload.Upload;
import com.arcbees.guicyresteasy.GuiceRestEasyFilterDispatcher;
import com.googlecode.objectify.ObjectifyFilter;

public class ServletModule extends com.google.inject.servlet.ServletModule {
    @Override
    public void configureServlets() {
        filter("/*").through(ObjectifyFilter.class);

        filter("/api/*").through(GuiceRestEasyFilterDispatcher.class);

        serve("/image/*").with(Serve.class);
        serve("*.gupld").with(Upload.class);
    }
}
