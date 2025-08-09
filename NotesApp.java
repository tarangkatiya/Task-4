import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("Welcome to the Notes App!");
            System.out.println("1. Write a note");
            System.out.println("2. Read notes");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    writeNote(scanner);
                    break;
                case "2":
                    readNotes();
                    break;
                case "3":
                    System.out.println("Exiting the Notes App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!choice.equals("3"));

        scanner.close();
    }

    private static void writeNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true); // Append mode
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(note);
            bufferedWriter.newLine(); // Add a new line after the note
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the note: " + e.getMessage());
        }
    }

    private static void readNotes() {
        System.out.println("Reading notes from " + FILE_NAME + ":");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. Please write a note first.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the notes: " + e.getMessage());
        }
    }
}
