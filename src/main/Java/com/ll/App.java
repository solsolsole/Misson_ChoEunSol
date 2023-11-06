package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner;
    List<Quotation> quotations;
    int lastQutotationId;

    public App() {
        scanner = new Scanner(System.in);
        quotations = new ArrayList<>();
        lastQutotationId = 0;
    }

    void run() {

        System.out.println("== 명언앱 ==");

        while (true) {
            System.out.print("명령) ");

            String cmd = scanner.nextLine();

            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                case "종료" :
                    return;
                case "등록" :
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제" :
                    actionRemove(rq);
                    break;
                case "수정" :
                    actionModify(rq);
                    break;
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

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);

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

    void actionRemove(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력하세요.");
        }

        int index = getIndexofQuoById(id);

        if (index == -1){
            System.out.println("등록된 명언이 없습니다.");
        }

        System.out.printf("%d번 명언을 삭제합니다.\n", id);

        quotations.remove(index);
    }

    int getIndexofQuoById(int id){
        for (int i= 0; i <quotations.size() ; i++) {
            Quotation quotation = quotations.get(i);

            if (quotation.id == id){
                return i;
            }
        }
        return -1;
    }

    void actionModify(Rq rq) {
        int id = rq.getParamAsInt( "id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }
        System.out.printf("%d번 명언을 수정합니다.\n", id);
    }
}