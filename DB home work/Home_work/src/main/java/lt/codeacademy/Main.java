package lt.codeacademy;

import lt.codeacademy.Files.ReaderWriter;
import lt.codeacademy.Windows.*;

public class Main {

    public static void main(String[] args) {
        MainModel mainModel = new MainModel();

        ReaderWriter readerWriter = new ReaderWriter(mainModel);
        readerWriter.fileReader();

        MainWindow mainWindow = new MainWindow(mainModel);
        mainWindow.window();
    }
}
