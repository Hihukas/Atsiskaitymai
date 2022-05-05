import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Task {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println("Įveskite vardą:");
        String name = scanner.nextLine();
        System.out.println("Įveskite pavardę:");
        String surname = scanner.nextLine();
        System.out.println("Įveskite asmens kodą:");
        String id = scanner.nextLine();

        Person person = new Person(name, surname, id);

        File file = new File("Persons.json");
        if (!file.exists()) {
            file.createNewFile();
        }

        objectMapper.writeValue(file, person);

        person = objectMapper.readValue(file, Person.class);
    }
}
