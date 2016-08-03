package com.mallbiz.service;

import java.util.HashMap;
import java.util.Map;

import com.mallbiz.api.Notify;

public class NotifyImpl implements Notify {
    public Map<String, String>    ret    = new HashMap<String, String>();
    public Map<String, Throwable> errors = new HashMap<String, Throwable>();
    public void onreturn(String msg, String id) {
        System.out.println("onreturn:" + msg);
        ret.put(id, msg);
    }
    public void onthrow(Throwable ex, String id) {
    	System.out.println("onthrow:" + ex.getMessage());
        errors.put(id, ex);
    }
}