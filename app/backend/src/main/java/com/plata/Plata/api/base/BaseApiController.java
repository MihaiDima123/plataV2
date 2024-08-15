package com.plata.Plata.api.base;

import com.plata.Plata.core.configuration.provider.ApiBuilderProvider;

public abstract class BaseApiController {
    protected ApiBuilderProvider apiBuilderProvider;

    public BaseApiController(ApiBuilderProvider apiBuilderProvider) {
        this.apiBuilderProvider = apiBuilderProvider;
    }
}
