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

package com.arcbees.bourseje.server.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.LoadType;

import static com.arcbees.bourseje.server.dao.OfyService.ofy;

public abstract class BaseDao<T> {
    protected final Class<T> clazz;

    protected BaseDao(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public void delete(long id) {
        ofy().delete().type(clazz).id(id).now();
    }

    public List<T> getAll() {
        return query().list();
    }

    public T put(T object) {
        ofy().save().entity(object).now();
        return object;
    }

    public Collection<T> put(Iterable<T> entities) {
        return ofy().save().entities(entities).now().values();
    }

    public T get(Key<T> key) {
        return ofy().load().key(key).now();
    }

    public List<T> get(List<Key<T>> keys) {
        return Lists.newArrayList(ofy().load().keys(keys).values());
    }

    public T get(Long id) {
        return query().id(id).now();
    }

    public T get(String id) {
        return query().id(id).now();
    }

    public List<T> getSubset(Iterable<Long> ids) {
        return new ArrayList<T>(query().ids(ids).values());
    }

    public Map<Long, T> getSubsetMap(Iterable<Long> ids) {
        return new HashMap<>(query().ids(ids));
    }

    public void delete(T object) {
        ofy().delete().entity(object);
    }

    public void delete(List<T> objects) {
        ofy().delete().entities(objects);
    }

    public void delete(Object parent,
                       Long id) {
        ofy().delete().type(clazz).parent(parent).id(id).now();
    }

    protected LoadType<T> query() {
        return ofy().load().type(clazz);
    }

    public void deleteAll() {
        List<Key<T>> keys = ofy().load().type(clazz).keys().list();
        ofy().delete().entities(keys).now();
    }
}
