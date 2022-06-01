package lt.codeacademy.Windows;

import lt.codeacademy.Exams.Exam;
import lt.codeacademy.Exams.ExamType;
import lt.codeacademy.Exams.Question;
import lt.codeacademy.MainModel;

import java.util.*;

public class ExamsCreateWindow extends AbstractWindow {
    private final MainModel mainModel;

    public ExamsCreateWindow(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void window() {
        List<Exam> exams = mainModel.getExamsList();

        List<Question> questions = new ArrayList<>();

        System.out.println("Įveskite testo egzamino temą:");
        String examTheme = mainModel.getScanner().nextLine();

        questionsCreate(questions);

        exams.add(new Exam(UUID.randomUUID(), ExamType.TEST, examTheme, questions));

        mainModel.setExamsList(exams);

        System.out.println("\nInformacija išsaugota.");

        ReturnAction returnAction = new ReturnAction(mainModel);
        returnAction.returnAction();
    }

    public void questionsCreate(List<Question> questions) {
        int examQuestionsQuantity = 0;

        for (int a = 0; a < 1; a++) {
            System.out.println("Įveskite norimą klausimų kiekį:");
            try {
                examQuestionsQuantity = Integer.parseInt(mainModel.getScanner().nextLine()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Galimi tik skaičiai!\n");
                a--;
            }
        }

        for (int b = 0; b <= examQuestionsQuantity; b++) {
            System.out.println("Sukurkite klausimą:");
            String question = (mainModel.getScanner().nextLine() + "\nAtsakymų variantai:");

            int examQuestionsAnswersQuantity = 0;
            for (int c = 0; c < 1; c++) {
                System.out.println("Įveskite norimą atsakymų kiekį:");
                try {
                    examQuestionsAnswersQuantity = Integer.parseInt(mainModel.getScanner().nextLine()) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Galimi tik skaičiai!\n");
                    c--;
                }
            }

            List<String> answers = new ArrayList<>();

            for (int d = 0; d <= examQuestionsAnswersQuantity; d++) {
                System.out.println("Įrašykite atsakymą:");
                String answer = mainModel.getScanner().nextLine();
                answers.add(answer);
            }

            int correctAnswer = 0;
            for (int f = 0; f < 1; f++) {
                System.out.println("Nurodykite teisingą atsakymą:");
                try {
                    correctAnswer = Integer.parseInt(mainModel.getScanner().nextLine());
                    if (correctAnswer <= 0 || correctAnswer > (examQuestionsAnswersQuantity + 1)) {
                        throw new IndexOutOfBoundsException();
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Tokio atsakymo nėra!\n");
                    f--;
                }
            }

            Question questionObject = new Question();
            questionObject.setQuestion(question);
            questionObject.setAnswers(answers);
            questionObject.setCorrectAnswer(correctAnswer);

            questions.add(questionObject);
        }
    }
}


