package lt.codeacademy.Windows;

import lt.codeacademy.Answers.StudentsAnswers;
import lt.codeacademy.Exams.Question;
import lt.codeacademy.MainModel;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

public class QuestionsWindow extends AbstractWindow {
    private final MainModel mainModel;
    private final List<Integer> questionsAnswers = new LinkedList<>();

    public QuestionsWindow(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void window() {
        System.out.printf("\nPasirinktas egzaminas:\nID: %s\nEgzamino tipas: %s\nPavadinimas: %s\n", mainModel.getExam().getId(), mainModel.getExam().getExamType(), mainModel.getExam().getName());

        List<Question> examQuestions = mainModel.getExam().getQuestions();

        for (int i = 0; i < mainModel.getExam().getQuestions().size(); i++) {
            Question question = examQuestions.get(i);
            try {
                printQuestion(question.getQuestion(), question.getAnswers());
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Tokio atsakymo nėra!");
                i--;
            }
        }

        StudentsAnswers studentAnswers = createStudentAnswersObject();
        List<StudentsAnswers> studentsAnswers = mainModel.getStudentsAnswersList();
        studentsAnswers.add(studentAnswers);
        mainModel.setStudentsAnswersList(studentsAnswers);

        System.out.printf("\nAčiū už atsakymus! Jie išsaugoti!\nJūsų pažymys: %s\nEgzaminą perlaikyti bus galima po 48 valandų.\n", testResult());

        ReturnAction returnAction = new ReturnAction(mainModel);
        returnAction.returnAction();
    }

    private String testResult() {
        List<Integer> correctAnswers = mainModel.getExam().getQuestions().stream()
                .map(Question::getCorrectAnswer).toList();

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < mainModel.getExam().getQuestions().size(); i++) {
            if (correctAnswers.get(i).equals(questionsAnswers.get(i))) {
                result.add(i);
            }
        }

        double grade = ((result.size() * 10D) / mainModel.getExam().getQuestions().size());

        return String.format("%.0f", grade);
    }

    private StudentsAnswers createStudentAnswersObject() {
        StudentsAnswers studentsAnswers = new StudentsAnswers();
        studentsAnswers.setUser(mainModel.getUser());
        studentsAnswers.setExam(mainModel.getExam());
        studentsAnswers.setAnswers(questionsAnswers);
        studentsAnswers.setGrade(testResult());
        LocalDateTime localDateTime = LocalDateTime.now();
        studentsAnswers.setTime(localDateTime.toString());
        return studentsAnswers;
    }

    private void printQuestion(String question, List<String> answers) {
        System.out.println("\n" + question);

        IntStream.range(0, answers.size())
                .mapToObj(i -> (i + 1) + ". " + answers.get(i))
                .forEach(System.out::println);

        System.out.println("\nĮveskite teisingą atsakymo numerį:");

        int answerInput = Integer.parseInt(mainModel.getScanner().nextLine());
        if (answerInput < 1 || answerInput > answers.size()) {
            throw new IndexOutOfBoundsException();
        }
        questionsAnswers.add(answerInput);
    }
}
