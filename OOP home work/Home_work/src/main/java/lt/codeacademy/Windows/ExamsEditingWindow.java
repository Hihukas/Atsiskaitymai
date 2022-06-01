package lt.codeacademy.Windows;

import lt.codeacademy.Exams.Exam;
import lt.codeacademy.Exams.Question;
import lt.codeacademy.MainModel;

import java.util.ArrayList;
import java.util.List;

public class ExamsEditingWindow extends AbstractWindow {
    private final MainModel mainModel;
    private final int index;

    public ExamsEditingWindow(int index, MainModel mainModel) {
        this.index = index;
        this.mainModel = mainModel;
    }

    @Override
    public void window() {
        List<Exam> exams = mainModel.getExamsList();

        List<Question> oldQuestions = mainModel.getExam().getQuestions().stream().toList();

        List<Question> questions = new ArrayList<>(oldQuestions);

        ExamsCreateWindow examsCreateWindow = new ExamsCreateWindow(mainModel);

        examsCreateWindow.questionsCreate(questions);

        exams.set(index, new Exam(mainModel.getExam().getId(), mainModel.getExam().getExamType(), mainModel.getExam().getName(), questions));

        mainModel.setExamsList(exams);

        System.out.println("\nInformacija išsaugota.");

        ReturnAction returnAction = new ReturnAction(mainModel);
        returnAction.returnAction();
    }
}

