package lt.codeacademy.Windows;

import lt.codeacademy.Users.User;
import lt.codeacademy.Users.UserType;
import lt.codeacademy.MainModel;

import java.util.UUID;

public class RegistrationWindow extends AbstractWindow {
    private final MainModel mainModel;

    public RegistrationWindow(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void window() {
        String userName = usernameCheck();
        System.out.println("Sukurkite slaptažodį:");
        String password = mainModel.getScanner().nextLine();
        if (!passwordCheck(password)) {
            System.out.println("Paskyros sukurti nepavyko.\n");
            RegistrationWindow registrationWindow = new RegistrationWindow(mainModel);
            registrationWindow.window();
        }
        System.out.println("Įveskite savo vardą:");
        String name = mainModel.getScanner().nextLine();
        System.out.println("Įveskite savo pavardę:");
        String surname = mainModel.getScanner().nextLine();

        User user = new User()
                .setId(UUID.randomUUID())
                .setUserType(UserType.STUDENT)
                .setUsername(userName)
                .setPassword(password)
                .setName(name)
                .setSurname(surname);

        mainModel.getUsersList().add(user);
        mainModel.setUser(user);

        System.out.printf("\nSveikiname prisijungus, %s!\n", name);
        StudentWindow studentWindow = new StudentWindow(mainModel);
        studentWindow.window();
    }

    private String usernameCheck() {
        while (true) {
            System.out.println("Sukurkite vartotojo vardą:");
            String userName = mainModel.getScanner().nextLine();

            boolean userNameAlreadyExists = mainModel.getUsersList().stream()
                    .anyMatch(u -> u.getUsername().equals(userName));

            if (userNameAlreadyExists) {
                System.out.println("Toks vartotojo vardas jau egzistuoja.\n");
                continue;
            }
            return userName;
        }
    }

    private boolean passwordCheck(String password) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Pakartokite slaptažodžio įvedimą:");
            String repeatPassword = mainModel.getScanner().nextLine();
            if (!repeatPassword.equals(password)) {
                System.out.println("Slaptažodžiai nesutampa.\n");
                continue;
            }
            return true;
        }
        return false;
    }
}

