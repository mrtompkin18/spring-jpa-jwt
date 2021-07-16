package com.healme.app.model.common;

import com.healme.app.common.error.ApiException;

public abstract class AbsGenericTask<Rq extends ApiRequestModel, Rs extends ApiResponseModel> {

    public abstract Rs processTask(Rq request) throws ApiException;

    protected abstract void validateBusiness(Rq request) throws ApiException;
}
