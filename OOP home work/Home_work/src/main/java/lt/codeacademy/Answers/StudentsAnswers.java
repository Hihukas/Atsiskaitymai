package lt.codeacademy.Answers;

import lt.codeacademy.Exams.Exam;
import lt.codeacademy.Users.User;

import java.util.List;

public class StudentsAnswers {
    private User user;
    private Exam exam;
    private List<Integer> answers;
    private String grade;
    private String time;

    public StudentsAnswers() {
    }

    public StudentsAnswers(User user, Exam exam, List<Integer> answers, String grade, String time) {
        this.user = user;
        this.exam = exam;
        this.answers = answers;
        this.grade = grade;
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
