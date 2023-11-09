package com.ll.base;

import com.ll.standard.util.Ut;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String cmd;
    private String action;
    private String queryStr;
    private Map<String, String> paramsMap;

    public Rq(String cmd) {
        paramsMap = new HashMap<>();

        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?", 2);
        action = cmdBits[0].trim();

        if (cmdBits.length == 1){
            return;
        }

        queryStr = cmdBits[1].trim();

        String[] queryStrBits = queryStr.split("&");

        for (int i = 0; i < queryStrBits.length; i++) {
            String queryParam = queryStrBits[i];
            String[] queryParamBits = queryParam.split("=");

            String paramName = queryParamBits[0];
            String paramValue = queryParamBits[1];

            paramsMap.put(paramName, paramValue);

        }
    }
    public String getAction() {
        return action;
    }

   public int getParamAsInt(String paramName, int defultValue) {
        return Ut.str.parseInt(paramsMap.get(paramName), 0 );
    }
}
