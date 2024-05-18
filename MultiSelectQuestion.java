package com.quizapp;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MultiSelectQuestion extends ChoiceQuestion {
    private String correctAnswers;

    public MultiSelectQuestion(String questionText, String correctAnswers, List<String> options) {
        super(questionText, correctAnswers, options);
        this.correctAnswers = correctAnswers;
    }

    @Override
    public boolean checkAnswer(String answer) {
        List<String> numberList = new ArrayList<>();
        for (String number : answer.split(",")) {
            numberList.add(number);
        }
        Collections.sort(numberList);
        return correctAnswers.equalsIgnoreCase(String.join(",",numberList));
    }

    @Override
    public void displayQuestion() {
        super.displayQuestion();
        System.out.println("답을 모두 고르세요. (순서 상관 없습니다.)");
    }
}
