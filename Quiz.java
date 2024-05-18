package com.quizapp;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    private final List<Question> questions;
    private int score;

    public Quiz() {
        questions = new ArrayList<>();
        questions.add(new MultipleChoiceQuestion("서버 애플리케이션을 구축하기 위해 사용되는 자바스크립트 런타임 환경은?","2", List.of("PYTHON","node.js","JAVA","REACT")));
        questions.add(new MultiSelectQuestion("REST API에 부합하는 규칙은?","1,2,5",List.of("소문자를 사용한다.","확장자를 사용하지 않는다.","URI의 마지막에 슬래시를 포함한다.","밑줄(_)을 사용한다.","리소스명은 동사가 아닌 명사를 사용한다.")));
        questions.add(new TrueFalseQuestion("요소의 색상 변경은 리플로우를 유발한다.","X"));
        questions.add(new MultipleChoiceQuestion("각 인증 요청마다 고유하게 발급되는 한 번만 사용되는 숫자는?","5", List.of("Realm","WWW-Authenticate","OAuth","Hash","Nonce")));
        questions.add(new TrueFalseQuestion("연산 작업을 진행할때 값이 null 또는 undefined일때 대체할 수 있는 값을 지정해두는 기법을 null guarding이라 한다.","O"));
        questions.add(new MultipleChoiceQuestion("git명령어 중 이전 커밋으로 돌아가는데 돌아가는 기록이 남는 명령어는?","1", List.of("git revert","git reset","git restore","git remote","git merge")));
        questions.add(new MultiSelectQuestion("JAVA의 기본 자료형을 고르세요.","2,3",List.of("String","char","double","SomeClass","array")));

    }

    public void addQuestion(Question question) {
        questions.add(question);
        System.out.println("문제 추가 완료되었습니다!");
    }

    public void start() {
        score = 0;
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println("-".repeat(130));
            question.displayQuestion();
            System.out.print("답을 입력해주세요 : ");
            String answer = scanner.nextLine();
            if (question.checkAnswer(answer)) {
                score++;
                System.out.println("정답입니다!");
            } else {
                System.out.println("오답입니다.");
            }
        }

        displayScore();
    }

    public void displayScore() {
        System.out.println("결과 : " + questions.size() + "점 만점에 " + score + "점");
    }
}
