package com.ll.domain.quotation;

import com.ll.base.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuotationController {
    private List<Quotation> quotations;
    private int lastQutotationId;
    Scanner scanner;

    public QuotationController(Scanner scanner) {
        this.quotations = new ArrayList<>();
        this.lastQutotationId = 0;
        this.scanner = scanner;
    }

    public void actionWrite() {
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

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (int i = quotations.size() - 1; i >= 0; i--) { //
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.getId(), quotation.getAuthor(), quotation.getContent());
        }
    }

    public void actionRemove(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력하세요.");
        }

        int index = getIndexofQuoById(id);

        if (index == -1) {
            System.out.println("등록된 명언이 없습니다.");
        }

        System.out.printf("%d번 명언을 삭제합니다.\n", id);

        quotations.remove(index);
    }

    public int getIndexofQuoById(int id) {
        for (int i = 0; i < quotations.size(); i++) {
            Quotation quotation = quotations.get(i);

            if (quotation.getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        int index = getIndexofQuoById(id);

        if (id == -1) {
            System.out.println("등록된 명언이 없습니다.");
        }

        Quotation quotation = quotations.get(index);

        System.out.println("명령(기존) : " + quotation.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.println("작가(기존) : " + quotation.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        quotation.setContent(content);
        quotation.setAuthor(author);

        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }
}
