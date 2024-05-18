package com.quizapp;

import java.util.List;

public abstract class ChoiceQuestion extends Question {
    private List<String> options;

    public ChoiceQuestion(String questionText, String correctAnswer, List<String> options) {
        super(questionText, correctAnswer);
        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public void displayQuestion() {
        System.out.println(getQuestionText());
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ": " + options.get(i));
        }
    }
}
