package com.quizapp;

public abstract class Question {
    private String questionText;
    private String correctAnswer;

    public Question(String questionText,String correctAnswer){
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText(){
        return questionText;
    }

    public boolean checkAnswer(String answer){
        return correctAnswer.equalsIgnoreCase(answer);
    }

    public abstract void displayQuestion();
}
