package lt.codeacademy.Windows;

import lt.codeacademy.MainModel;

public class ReturnAction {
    private final MainModel mainModel;

    public ReturnAction(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void returnAction() {
        System.out.println("""    
                                               
                [1] Grįžti į pagrindinį meniu.
                """);
        String input = mainModel.getScanner().nextLine();
        if ("1".equals(input)) {
            switch (mainModel.getUser().getUserType()) {
                case LECTOR -> {
                    LectorWindow lectorWindow = new LectorWindow(mainModel);
                    lectorWindow.window();
                }
                case STUDENT -> {
                    StudentWindow studentWindow = new StudentWindow(mainModel);
                    studentWindow.window();
                }
            }
        } else {
            System.out.println("Tokio veiksmo nėra. Pasirinkite iš naujo");
            ReturnAction returnAction = new ReturnAction(mainModel);
            returnAction.returnAction();
        }
    }
}
