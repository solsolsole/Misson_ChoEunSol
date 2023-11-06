package com.ll;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    String cmd;
    String action;
    String queryStr;
    Map<String, String> paramsMap;

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

    String getAction() {
        return action;
    }

    int getParamAsInt(String paramName, int defultValue) {
       String paramValue = paramsMap.get(paramName);

       if (paramValue != null){
           try {
               return Integer.parseInt(paramValue);
           } catch (NumberFormatException e){}
       }
        return defultValue;
    }
}
