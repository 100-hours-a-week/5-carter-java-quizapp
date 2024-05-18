package com.quizapp;

import java.util.List;

public class MultipleChoiceQuestion extends ChoiceQuestion {
    private String correctAnswers;

    public MultipleChoiceQuestion(String questionText, String correctAnswers, List<String> options) {
        super(questionText, correctAnswers, options);
        this.correctAnswers = correctAnswers;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswers.equalsIgnoreCase(answer);
    }

    @Override
    public void displayQuestion() {
        super.displayQuestion();
        System.out.println("1개의 답을 고르세요.");
    }
}
