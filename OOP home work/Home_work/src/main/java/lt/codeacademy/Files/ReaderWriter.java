package lt.codeacademy.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import lt.codeacademy.MainModel;

import java.io.File;

public class ReaderWriter {
    private final MainModel mainModel;
    private final File studentsAnswersFile = new File("src/main/java/lt/codeacademy/Files/StudentsAnswers/StudentsAnswers.json");
    private final File usersListFile = new File("src/main/java/lt/codeacademy/Files/Users/Users.json");
    private final File examsFile = new File("src/main/java/lt/codeacademy/Files/Exams/Exams.json");


    public ReaderWriter(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void fileReader() {
        mainModel.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mainModel.setStudentsAnswersList(mainModel.getObjectMapper().readValue(studentsAnswersFile, new TypeReference<>() {
            }));

            mainModel.setUsersList(mainModel.getObjectMapper().readValue(usersListFile, new TypeReference<>() {
            }));

            mainModel.setExamsList(mainModel.getObjectMapper().readValue(examsFile, new TypeReference<>() {
            }));
        } catch (Exception e) {
            System.out.println("Nepavyko nuskaityti duomenų!");
            System.exit(0);
        }
    }

    public void fileWriter() {
        mainModel.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mainModel.getObjectMapper().writeValue(studentsAnswersFile, mainModel.getStudentsAnswersList());

            mainModel.getObjectMapper().writeValue(usersListFile, mainModel.getUsersList());

            mainModel.getObjectMapper().writeValue(examsFile, mainModel.getExamsList());
        } catch (Exception e) {
            System.out.println("Nepavyko išsaugoti duomenų!");
        }
    }
}
