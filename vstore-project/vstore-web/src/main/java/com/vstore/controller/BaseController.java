package com.vstore.controller;


import com.vstore.framework.base.util.Response;

/**
 * @author vstore
 */
public class BaseController {

    public Response ok() {
        return new Response<>().ok();
    }

    public Response ok(Object data) {
      return new Response<>().ok(data);
    }

}
