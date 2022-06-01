package lt.codeacademy.Windows;

import lt.codeacademy.MainModel;

public class LectorWindow extends AbstractWindow {
    private final MainModel mainModel;

    public LectorWindow(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void window() {
        System.out.println("""
                                
                Pasirinkite norimą atlikti veiksmą:
                [1] - Egzaminų rezultatai.
                [2] - Sukurti testo egzaminą.
                [3] - Testo egzaminų papildymas klausimais.
                [4] - Atsijungti ir grįžti į pagrindinį meniu.
                """);
        String input = mainModel.getScanner().nextLine();
        switch (input) {
            case "1" -> {
                StudentsExamsResultsWindow studentsExamsResultsWindow = new StudentsExamsResultsWindow(mainModel);
                studentsExamsResultsWindow.window();
            }
            case "2" -> {
                ExamsCreateWindow examsCreateWindow = new ExamsCreateWindow(mainModel);
                examsCreateWindow.window();
            }
            case "3" -> {
                ExamsWindow examsWindow = new ExamsWindow(mainModel);
                examsWindow.window();
            }
            case "4" -> {
                MainWindow mainWindow = new MainWindow(mainModel);
                mainWindow.window();
            }
            default -> {
                System.out.println("Tokio veiksmo nėra. Pasirinkite iš naujo.");
                LectorWindow lectorWindow = new LectorWindow(mainModel);
                lectorWindow.window();
            }
        }
    }
}

