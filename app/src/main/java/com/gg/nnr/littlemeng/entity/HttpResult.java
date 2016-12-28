package com.gg.nnr.littlemeng.entity;


/**
 * Created by liukun on 16/3/5.
 */
public class HttpResult{

    private String showapi_res_error;

    private int showapi_res_code;

    public Object getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(Object showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    private Object showapi_res_body;


    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

}
