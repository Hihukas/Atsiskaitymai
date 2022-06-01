package lt.codeacademy.Windows;

import lt.codeacademy.Files.ReaderWriter;
import lt.codeacademy.MainModel;

public class MainWindow extends AbstractWindow {
    private final MainModel mainModel;

    public MainWindow(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void window() {
        System.out.println("""
                Pasirinkite norimą atlikti veiksmą:
                [1] - Prisijungimas.
                [2] - Naujų studentų registracija.
                [3] - Išeiti.
                """);
        String input = mainModel.getScanner().nextLine();
        switch (input) {
            case "1" -> {
                LoginWindow loginWindow = new LoginWindow(mainModel);
                loginWindow.window();
            }
            case "2" -> {
                RegistrationWindow registrationWindow = new RegistrationWindow(mainModel);
                registrationWindow.window();
            }
            case "3" -> {
                System.out.println("Programa baigia darbą.");
                ReaderWriter readerWriter = new ReaderWriter(mainModel);
                readerWriter.fileWriter();
                System.exit(0);
            }
            default -> {
                System.out.println("Tokio veiksmo nėra. Pasirinkite iš naujo.\n");
                MainWindow mainWindow = new MainWindow(mainModel);
                mainWindow.window();
            }
        }
    }
}



