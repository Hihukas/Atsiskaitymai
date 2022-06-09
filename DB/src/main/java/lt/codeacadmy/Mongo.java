package lt.codeacadmy;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lt.codeacadmy.MongoClient.Provider;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Mongo {
    private final Scanner scanner;
    private String username;

    public Mongo() {
        username = username;
        scanner = new Scanner(System.in);

    }

    public static void main(String[] args) {
        MongoClient objectMongoClient = Provider.getObjectMongoClient();

        MongoDatabase mongoDatabase = objectMongoClient.getDatabase("DB");

        MongoCollection<Users> users = mongoDatabase.getCollection("Users", Users.class);

        Mongo mongo = new Mongo();
        mongo.menu(users);

    }

    public void menu(MongoCollection<Users> users) {
        String action;
        do {
            System.out.println("""
                    1 - Registracija
                    2 - Išeiti
                    """);
            action = scanner.nextLine();
            selectAction(action, users);
        } while (!action.equals("3"));
    }

    private void selectAction(String action, MongoCollection<Users> users) {
        switch (action) {
            case "1" -> registration(users);
            case "2" -> System.out.println("Programa baigia darbą.");
            default -> System.out.println("Tokio veiksmo nėra. Pasirinkite iš naujo.");
        }
    }

    private void registration(MongoCollection<Users> users) {
        System.out.println("Įveskite vartotojo vardą:");

        doesUserExists(users);

        String password = passwordRepeat();
        System.out.println("Įveskite sąskaitos balansą:");
        BigDecimal balance = new BigDecimal(scanner.nextLine());

        users.insertMany(List.of(new Users(null, username, password, balance)));

    }

    private String doesUserExists(MongoCollection<Users> users) {
        username = scanner.nextLine();

        FindIterable<Users> doesUserExists = users.find(Filters.eq("username", username));

        if (doesUserExists != null) {
            System.out.println("Toks vartotojas jau yra. Bandykite dar kartą.");
            doesUserExists(users);
        }

        return username;
    }

    private String passwordRepeat() {
        System.out.println("Įveskite slaptažodį:");
        String newPassword = scanner.nextLine();
        System.out.println("Pakartokite slaptažodį:");
        String passwordRepeat = scanner.nextLine();

        if (!passwordRepeat.equals(newPassword)) {
            System.out.println("Slaptažodžiai nesutampa");
            passwordRepeat();
        }

        return newPassword;
    }

    private void moneyTransfer(MongoCollection<Users> users) {
        System.out.println("""
                Sveikiname sėkmingai prisiregistravus!
                                
                Įveskite vartotojo vardą, kuriam norite pervesti pinigus.
                """);

        username = scanner.nextLine();
        doesUserExists(users);

        System.out.println("Įveskite sumą, kurią norite pervesti:");
        BigDecimal amount = new BigDecimal(scanner.nextLine());

        FindIterable<Users> isBalanceEnough = users.find(Filters.and(Filters.gte("balance", amount), Filters.eq("username", username)));

    }
}

