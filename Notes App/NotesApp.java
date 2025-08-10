import java.io.*;
import java.util.*;

public class NotesApp {
    private static final String FILE_PATH = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== NOTES MANAGER ====");
            System.out.println("1. Create Note");
            System.out.println("2. View Notes");
            System.out.println("3. Update Note");
            System.out.println("4. Delete Note");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createNote(scanner);
                    break;
                case "2":
                    viewNotes();
                    break;
                case "3":
                    updateNote(scanner);
                    break;
                case "4":
                    deleteNote(scanner);
                    break;
                case "5":
                    System.out.println("Exiting application...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter 1-5");
            }
        }
    }

    private static void createNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        if (note.trim().isEmpty()) {
            System.out.println("Note cannot be empty!");
            return;
        }

        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(note + System.lineSeparator());
            System.out.println("Note created successfully!");
        } catch (IOException e) {
            System.out.println("Error creating note: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        List<String> notes = readAllNotes();

        if (notes.isEmpty()) {
            System.out.println("\nNo notes found");
            return;
        }

        System.out.println("\n==== YOUR NOTES ====");
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + ". " + notes.get(i));
        }
    }

    private static void updateNote(Scanner scanner) {
        List<String> notes = readAllNotes();

        if (notes.isEmpty()) {
            System.out.println("No notes available to update");
            return;
        }

        viewNotes();
        System.out.print("Enter note number to update: ");

        try {
            int noteNum = Integer.parseInt(scanner.nextLine());
            if (noteNum < 1 || noteNum > notes.size()) {
                System.out.println("Invalid note number!");
                return;
            }

            System.out.print("Enter updated note: ");
            String updatedNote = scanner.nextLine();

            if (updatedNote.trim().isEmpty()) {
                System.out.println("Note cannot be empty!");
                return;
            }

            notes.set(noteNum - 1, updatedNote);
            writeAllNotes(notes);
            System.out.println("Note updated successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
        }
    }

    private static void deleteNote(Scanner scanner) {
        List<String> notes = readAllNotes();

        if (notes.isEmpty()) {
            System.out.println("No notes available to delete");
            return;
        }

        viewNotes();
        System.out.print("Enter note number to delete: ");

        try {
            int noteNum = Integer.parseInt(scanner.nextLine());
            if (noteNum < 1 || noteNum > notes.size()) {
                System.out.println("Invalid note number!");
                return;
            }

            String deletedNote = notes.remove(noteNum - 1);
            writeAllNotes(notes);
            System.out.println("Deleted note: \"" + deletedNote + "\"");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
        }
    }

    private static List<String> readAllNotes() {
        List<String> notes = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return notes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    notes.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
        return notes;
    }

    private static void writeAllNotes(List<String> notes) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (String note : notes) {
                writer.write(note + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving notes: " + e.getMessage());
        }
    }
}