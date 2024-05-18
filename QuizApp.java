package com.quizapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuizApp {
    private static Quiz quiz = new Quiz();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    addQuestion();
                    break;
                case 2:
                    quiz.start();
                    break;
                case 3:
                    exit = true;
                    System.out.println("이용해주셔서 감사합니다!");
                    break;
                default:
                    System.out.println("1,2,3 중에 선택해주세요.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("=== 메 뉴 ===");
        System.out.println("1. 퀴즈 추가하기");
        System.out.println("2. 퀴즈 시작하기");
        System.out.println("3. 종료하기");
        System.out.print("선택해주세요 : ");
    }

    private static int getChoice() {
        int choice;
        while (true){
            try{
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 3) {
                    throw new Exception("1,2,3 중에 입력해주세요.");
                }
                return choice;
            }
            catch (NumberFormatException e){
                System.out.println("숫자를 입력해주세요.");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getOption() {
        int option;
        while (true){
            try{
                option = Integer.parseInt(scanner.nextLine());
                if (option < 2) {
                    throw new Exception("보기는 2개 이상이어야 합니다.");
                }
                return option;
            }
            catch (NumberFormatException e){
                System.out.println("숫자를 입력해주세요.");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static void addQuestion() {
        System.out.println("1: 객관식 1개 고르기 문제");
        System.out.println("2: 객관식 모두 고르기 문제");
        System.out.println("3: O/X 문제");
        System.out.print("퀴즈 타입을 골라주세요 : ");

        int type = getChoice();
        System.out.print("문제를 내주세요 : ");
        String questionText = scanner.nextLine();

        switch (type) {
            case 1:
                addMultipleChoiceQuestion(questionText);
                break;
            case 2:
                addMultiSelectQuestion(questionText);
                break;
            case 3:
                addTrueFalseQuestion(questionText);
                break;
        }
    }

    private static void addMultipleChoiceQuestion(String questionText) {
        System.out.print("보기 개수를 입력하세요 : ");
        int numOptions = getOption();
        List<String> options = new ArrayList<>();
        for (int i = 0; i < numOptions; i++) {
            System.out.print("보기 " + (i + 1) + ": ");
            options.add(scanner.nextLine());
        }

        int correctAnswer;
        while (true){
            System.out.print("정답을 입력하세요 : ");
            try{
                correctAnswer = Integer.parseInt(scanner.nextLine());
                if (correctAnswer < 1 || correctAnswer > numOptions) throw new Exception();
                break;
            }
            catch (NumberFormatException e){
                System.out.println("숫자를 입력해주세요.");
            }
            catch (Exception e){
                System.out.println("보기의 숫자를 입력해주세요.");
            }
        }

        quiz.addQuestion(new ChoiceQuestion(questionText, String.valueOf(correctAnswer), options) {
            @Override
            public List<String> getOptions() {
                return super.getOptions();
            }
        });
    }

    private static void addMultiSelectQuestion(String questionText) {
        List<String> options = new ArrayList<>();
        System.out.print("보기 개수를 입력하세요 : ");
        int numOptions = getOption();
        for (int i = 0; i < numOptions; i++) {
            System.out.print("보기 " + (i + 1) + ": ");
            options.add(scanner.nextLine());
        }
        while (true) {
            try {
                System.out.print("정답을 입력하세요 (순서 상관없습니다.) : ");
                String correctAnswers = scanner.nextLine();
                List<String> numberList = new ArrayList<>();
                for (String number : correctAnswers.split(",")) {
                    int num = Integer.parseInt(number);
                    if (num < 1 || num > numOptions) {
                        throw new Exception("보기의 숫자를 입력해주세요.");
                    }
                    if (numberList.contains(number)){
                        throw new Exception("중복된 숫자가 있습니다.");
                    }
                    numberList.add(number);
                }
                Collections.sort(numberList);
                quiz.addQuestion(new MultiSelectQuestion(questionText, String.join(",", numberList), options));
                break;

            }
            catch (NumberFormatException e){
                System.out.println("숫자를 입력해주세요.");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static void addTrueFalseQuestion(String questionText) {
        String correctAnswer;
        while (true){
            System.out.print("정답을 입력하세요 (O/X): ");
            correctAnswer = scanner.nextLine();
            if (correctAnswer.equalsIgnoreCase("O") || correctAnswer.equalsIgnoreCase("X")){
                quiz.addQuestion(new TrueFalseQuestion(questionText, correctAnswer));
                break;
            }
            else{
                System.out.println("O/X 만 입력해주세요.");
            }
        }

    }
}
