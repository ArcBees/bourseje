package com.arcbees.bourseje.client;

import com.google.gwt.http.client.Response;
import com.gwtplatform.dispatch.rest.shared.RestCallback;

public abstract class RestCallbackImpl<T> implements RestCallback<T> {
    private Response response;

    @Override
    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public void onFailure(Throwable throwable) {
        onError(response);
    }

    public void onError(Response response) {
    }
}
