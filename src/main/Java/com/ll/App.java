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

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {  // 5단계 끝, 이제 삭제
                actionList();
            } else if (cmd.startsWith("삭제?")) {
                actionRemove(cmd);
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
        String[] cmdBits = cmd.split("\\?", 2);
        String action = cmdBits[0];
        String queryStr = cmdBits[1];

        String[] queryStrBits = queryStr.split("&");

        int id = 0;
        for (int i = 0; i < queryStrBits.length; i++) {
            String[] queryParamBits = queryStr.split("=");
            String paramName = queryParamBits[0];
            String paramValue = queryParamBits[1];

            if (paramName.equals("id")) {
                id = Integer.parseInt(paramValue);
            }
            System.out.printf("%d번 명령이 삭제되었습니다.\n", id);

        }
    }
}