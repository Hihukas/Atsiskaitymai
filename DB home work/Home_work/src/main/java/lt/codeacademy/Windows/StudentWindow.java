package lt.codeacademy.Windows;

import lt.codeacademy.MainModel;

public class StudentWindow extends AbstractWindow {
    private final MainModel mainModel;

    public StudentWindow(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void window() {
        System.out.println("""
                                
                Pasirinkite norimą atlikti veiksmą:
                [1] - Laikyti egzaminą.
                [2] - Egzaminų rezultatai.
                [3] - Atsijungti ir grįžti į pagrindinį meniu.
                """);
        String input = mainModel.getScanner().nextLine();
        switch (input) {
            case "1" -> {
                ExamsWindow examsWindow = new ExamsWindow(mainModel);
                examsWindow.window();
            }
            case "2" -> {
                OneStudentExamsResultsWindow oneStudentExamsResultsWindow = new OneStudentExamsResultsWindow(mainModel);
                oneStudentExamsResultsWindow.window();
            }
            case "3" -> {
                MainWindow mainWindow = new MainWindow(mainModel);
                mainWindow.window();
            }
            default -> {
                System.out.println("Tokio veiksmo nėra. Pasirinkite iš naujo.");
                StudentWindow studentWindow = new StudentWindow(mainModel);
                studentWindow.window();
            }
        }
    }
}
