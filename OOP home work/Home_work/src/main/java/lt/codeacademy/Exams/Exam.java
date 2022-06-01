package lt.codeacademy.Exams;

import java.util.List;
import java.util.UUID;

public class Exam{
    private UUID id;
    private ExamType examType;
    private String name;
    private List<Question> questions;

    public Exam() {
    }

    public Exam(UUID id, ExamType examType, String name, List<Question> questions) {
        this.id = id;
        this.examType = examType;
        this.name = name;
        this.questions = questions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
