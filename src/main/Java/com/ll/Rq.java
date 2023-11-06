package com.ll;

import java.util.ArrayList;
import java.util.List;

public class Rq {
    String cmd;
    String action;
    String queryStr;
    List<String> paramNames;
    List<String> paramValues;

    public Rq(String cmd) {
        paramNames = new ArrayList<>();
        paramValues = new ArrayList<>();

        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?", 2);
        action = cmdBits[0].trim();
        queryStr = cmdBits[1].trim(); //여기서 자꾸 오류코드가 나는데 왜 인지 모르겠음

        String[] queryStrBits = queryStr.split("&");

        for (int i = 0; i < queryStrBits.length; i++) {
            String queryParam = queryStrBits[i];
            String[] queryParamBits = queryParam.split("=");

            String paramName = queryParamBits[0];
            String paramValue = queryParamBits[1];

            paramNames.add(paramName);
            paramValues.add(paramValue);
        }
    }

    String getAction() {
        return action;
    }

    int getParamAsInt(String paramName, int defultValue) {
        int index = paramNames.indexOf(paramName);

        if (index == -1) return defultValue;

        String paramValue = paramValues.get(index);

        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return defultValue;
        }
    }


}