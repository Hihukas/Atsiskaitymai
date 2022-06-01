package lt.codeacademy.Windows;

import lt.codeacademy.Users.User;
import lt.codeacademy.MainModel;

public class LoginWindow extends AbstractWindow {
    private final MainModel mainModel;

    public LoginWindow(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void window() {
        System.out.println("Įveskite vartotojo vardą: ");
        String userName = mainModel.getScanner().nextLine();
        System.out.println("Įveskite slaptažodį:");
        String password = mainModel.getScanner().nextLine();

        mainModel.setUser(login(userName, password));

        if (mainModel.getUser() == null) {
            System.out.println("Tokio vartotojo vardo arba slaptažodžio nėra. Bandykite iš naujo.\n");
            window();
        } else {
            switch (mainModel.getUser().getUserType()) {
                case STUDENT -> {
                    System.out.printf("\nSveikiname prisijungus, %s!\n", mainModel.getUser().getName());
                    StudentWindow studentWindow = new StudentWindow(mainModel);
                    studentWindow.window();
                }
                case LECTOR -> {
                    System.out.printf("\nSveikiname prisijungus, %s!\n", mainModel.getUser().getName());
                    LectorWindow lectorWindow = new LectorWindow(mainModel);
                    lectorWindow.window();
                }
            }
        }
    }

    private User login(String userName, String password) {
        return mainModel.getUsersList().stream()
                .filter(u -> u.getUsername().equals(userName) && u.getPassword().equals(password))
                .findAny()
                .orElse(null);
    }
}



