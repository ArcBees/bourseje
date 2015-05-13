/*
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

package com.arcbees.bourseje.client;

import com.google.gwt.http.client.Response;
import com.google.gwt.query.client.GQuery;

public abstract class AdminRestCallback<T> extends RestCallbackImpl<T> {
    @Override
    public void onError(Response response) {
        GQuery.console.error("Error calling API. You are probably not logged in.");
    }
}
