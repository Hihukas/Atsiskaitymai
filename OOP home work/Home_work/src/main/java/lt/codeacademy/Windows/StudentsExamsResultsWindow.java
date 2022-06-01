package lt.codeacademy.Windows;

import lt.codeacademy.Answers.StudentsAnswers;
import lt.codeacademy.MainModel;

import java.time.LocalDateTime;
import java.util.Comparator;

public class StudentsExamsResultsWindow extends AbstractWindow {
    private final MainModel mainModel;

    public StudentsExamsResultsWindow(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void window() {
        if (mainModel.getStudentsAnswersList().size() == 0) {
            System.out.println("Sąrašas tuščias.");

            ReturnAction returnAction = new ReturnAction(mainModel);
            returnAction.returnAction();
        } else {
            System.out.println("Sąrašas:");
            mainModel.getStudentsAnswersList().stream()
                    .sorted(Comparator.comparing((StudentsAnswers sa) -> sa.getUser().getName())
                            .thenComparing(sa -> sa.getUser().getSurname())
                            .thenComparing(sa -> LocalDateTime.parse(sa.getTime())))
                    .map(e -> "\nStudento vardas: " + e.getUser().getName() +
                            "\nStudento pavardė: " + e.getUser().getSurname() +
                            "\nEgzamino ID: " + e.getExam().getId() +
                            ", Egzamino pavadinimas: " + e.getExam().getName() +
                            ", Egzamino tipas: " + e.getExam().getExamType() +
                            "\nPažymys: " + e.getGrade() +
                            "\nEgzamino laikymo data: " + e.getTime())
                    .forEachOrdered(System.out::println);

            ReturnAction returnAction = new ReturnAction(mainModel);
            returnAction.returnAction();
        }
    }
}
