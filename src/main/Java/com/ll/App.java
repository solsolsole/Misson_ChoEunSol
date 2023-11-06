package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);

    List<Quotation> quotations = new ArrayList<>();

    int lastQutotationId = 0;

    void run() {

        System.out.println("== 명언앱 ==");

        while (true) {
            System.out.print("명령) ");

            String cmd = scanner.nextLine();

            Rq rq = new Rq(cmd);
            System.out.println(rq.getAction());
            System.out.println(rq.getParamAsInt("id",0));

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {  // 5단계 끝, 이제 삭제
                actionList();
            } else if (cmd.startsWith("삭제?")) {
                actionRemove(cmd);
            } else if (cmd.startsWith("수정?")) {
                actionModify(cmd);
            }
        }
    }

    void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        lastQutotationId++;
        int id = lastQutotationId;

        System.out.printf("%d번 명언이 등록되었습니다.\n", id); // 4단계 끝, 이제 목록생성

        Quotation quotation = new Quotation(id, author, content);
        quotations.add(quotation);
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (int i = quotations.size() - 1; i >= 0; i--) { //
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.id, quotation.author, quotation.content);
        }
    }

    void actionRemove(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력하세요.");
        }
        System.out.printf("%d번 명언을 삭제합니다.\n",id);
    }

    void actionModify(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }
        System.out.printf("%d번 명언을 수정합니다.\n", id);
    }

    int getParamAsInt(String cmd, String paramName, int defultValue) {
        String[] cmdBits = cmd.split("\\?", 2);
        String action = cmdBits[0];
        String queryStr = cmdBits[1];

        String[] queryStrBits = queryStr.split("&");

        int id = 0;
        for (int i = 0; i < queryStrBits.length; i++) {
            String queryParam = queryStrBits[i];
            String[] queryParamBits = queryParam.split("=");

            String paramName_ = queryParamBits[0];
            String paramValue = queryParamBits[1];

            if (paramName_.equals("id")) {
                try {
                    return Integer.parseInt(paramValue);
                } catch (NumberFormatException e) {
                    return defultValue;
                }

            }
            System.out.printf("%d번 명령이 수정되었습니다.\n", id);

        }
        return defultValue;
    }
}