package com.healme.app.model.common;

import com.healme.app.common.error.ApiException;

public abstract class AbsGenericTask<Rq extends ApiRequestModel, Rs extends ApiResponseModel> {

    protected abstract void validateBusiness(Rq request) throws ApiException;

    protected abstract Rs processTask(Rq request) throws ApiException;

    public Rs executeTask(Rq request) throws ApiException {
        this.validateBusiness(request);
        return this.processTask(request);
    }
}
