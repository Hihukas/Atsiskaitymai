package lt.codeacademy.Windows;

import lt.codeacademy.Answers.StudentsAnswers;
import lt.codeacademy.MainModel;

import java.util.*;
import java.util.stream.IntStream;

public class OneStudentExamsResultsWindow extends AbstractWindow {
    private final MainModel mainModel;

    public OneStudentExamsResultsWindow(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void window() {
        List<StudentsAnswers> oneStudentsAnswersList = fillOneStudentsAnswersList();

        if (oneStudentsAnswersList.size() == 0) {
            System.out.println("Sąrašas tuščias.");

            ReturnAction returnAction = new ReturnAction(mainModel);
            returnAction.returnAction();
        } else {
            System.out.println("Egzaminų sąrašas:");

            IntStream.range(0, oneStudentsAnswersList.size())
                    .mapToObj(i -> {
                        StudentsAnswers e = oneStudentsAnswersList.get(i);
                        return (i + 1) + " Egzamino ID: " + e.getExam().getId() +
                                ", Egzamino pavadinimas: " + e.getExam().getName() +
                                ", Egzamino tipas: " + e.getExam().getExamType() +
                                ", Pažymys: " + e.getGrade() +
                                ", Egzamino laikymo data: " + e.getTime();
                    })
                    .forEach(System.out::println);

            ReturnAction returnAction = new ReturnAction(mainModel);
            returnAction.returnAction();
        }
    }

    public List<StudentsAnswers> fillOneStudentsAnswersList() {
        List<StudentsAnswers> filledOneStudentsAnswersList = new ArrayList<>();

        for (StudentsAnswers a : mainModel.getStudentsAnswersList()) {
            if (a.getUser().getId().equals(mainModel.getUser().getId())) {
                filledOneStudentsAnswersList.add(a);
            }
        }
        return filledOneStudentsAnswersList;
    }
}

