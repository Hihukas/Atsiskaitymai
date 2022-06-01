package lt.codeacademy.Windows;

import lt.codeacademy.Answers.StudentsAnswers;
import lt.codeacademy.Exams.Exam;
import lt.codeacademy.Users.UserType;
import lt.codeacademy.MainModel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

public class ExamsWindow extends AbstractWindow {
    private final MainModel mainModel;

    public ExamsWindow(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void window() {
        List<Exam> examsToTake = getExams();

        if (examsToTake.size() == 0) {
            System.out.println("Egzaminų sąrašas tuščias.");
            ReturnAction returnAction = new ReturnAction(mainModel);
            returnAction.returnAction();
        }

        System.out.println("Egzaminų sąrašas:");

        IntStream.range(0, examsToTake.size())
                .mapToObj(i -> {
                    Exam e = examsToTake.get(i);
                    try {
                        return "[" + (i + 1) + "] Egzamino ID: " + e.getId() +
                                ", Egzamino pavadinimas: " + e.getName() +
                                ", Egzamino tipas: " + e.getExamType();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .forEach(System.out::println);

        for (int i = 0; i < examsToTake.size(); i++) {
            try {
                examChoose(examsToTake);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Tokio egzamino nėra! Rinkites dar kartą.");
                i--;
            }
        }
    }

    private void examChoose(List<Exam> examsToTake) {
        System.out.println("\nPasirinkite egzaminą:");
        int index = Integer.parseInt(mainModel.getScanner().nextLine()) - 1;
        mainModel.setExam(examsToTake.get(index));
        if (mainModel.getUser().getUserType() == UserType.LECTOR) {
            ExamsEditingWindow examsEditingWindow = new ExamsEditingWindow(index, mainModel);
            examsEditingWindow.window();
        } else {
            QuestionsWindow questionsWindow = new QuestionsWindow(mainModel);
            questionsWindow.window();
        }
    }

    private List<Exam> getExams() {
        return (mainModel.getUser().getUserType() == UserType.LECTOR) ? mainModel.getExamsList() : getUsersExamsToTake();
    }

    private List<Exam> getUsersExamsToTake() {
        OneStudentExamsResultsWindow oneStudentExamsResultsWindow = new OneStudentExamsResultsWindow(mainModel);
        List<StudentsAnswers> oneStudentsAnswerList = oneStudentExamsResultsWindow.fillOneStudentsAnswersList();

        List<Exam> restrictedExams = oneStudentsAnswerList.stream()
                .filter(a -> {
                    LocalDateTime time = LocalDateTime.parse(a.getTime());
                    LocalDateTime currentTime = LocalDateTime.now();
                    long examPassedHours = Duration.between(time, currentTime).toHours();
                    return examPassedHours < 48;
                })
                .map(StudentsAnswers::getExam)
                .toList();

        return mainModel.getExamsList().stream()
                .filter(e -> restrictedExams.stream().noneMatch(re -> re.getId().equals(e.getId())))
                .toList();
    }
}

