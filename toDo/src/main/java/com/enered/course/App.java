package com.enered.course;

import com.enered.course.exception.ItemAlreadyExistsException;
import com.enered.course.model.BaseToDo;
import com.enered.course.model.HighPriorityToDo;
import com.enered.course.model.User;
import com.enered.course.thread.DeadlineCheckerRunnable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String delimiter = "==================================================";
    private static final String logo;
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    // static block used to load the application logo
    static {
        InputStream inputStream = App.class.getResourceAsStream("/logo.txt");
        StringBuilder builder = new StringBuilder();

        byte[] buffer = new byte[1024];
        try {
            while (inputStream.read(buffer) != -1) {
                builder.append(new String(buffer));
            }
        } catch (IOException e) {
            System.err.println("Failed to load logo.");
            System.out.println(e.getMessage());
        }

        logo = builder.toString();
    }

    public static void main(String[] args) {
        User user = new User("Andrei");

        // Printing application logo
        System.out.println(logo);
        handleUserInput(user);

        System.out.println("App will shutdown.");
        executorService.shutdown();
        scanner.close();
    }

    private static void handleUserInput(User user) {
        System.out.println("Hello, " + user.getName() + "! Welcome to the ToDoManagerApp (TDMA)");
        String menu = "Available commands:\n" +
                "1. Display\n" +
                "2. Add\n" +
                "3. Remove\n" +
                "4. Update\n" +
                "5. Generate file\n" +
                "6. Import file\n" +
                "7. Exit";

        String command = "";
        do {
            System.out.println(menu);
            command = scanner.nextLine();
            switch (command) {
                case "1":
                    displayItems(user);
                    break;
                case "2":
                    try {
                        addItem(user);
                    } catch (ItemAlreadyExistsException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "3":
                    try {
                        removeItem(user);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        updateItem(user);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "5":
                    try {
                        generateToDoFile(user);
                        System.out.println("Generated a new export file.");
                    } catch (IOException e) {
                        System.err.println("An error occurred while trying to generate the file");
                    }
                    break;
                case "6":
                    try {
                        importToDoList(user);
                    } catch (IOException e) {
                        System.err.println("An error occurred while trying to import the file");
                    }
                    break;
                case "7":
                    System.out.println("Thank you for using our app! See you next time!");
                    break;
                default:
                    System.out.println("Invalid command. Try again!");
            }
            System.out.println(delimiter);
        } while (!command.equals("7"));
    }

    private static void addItem(User user) throws ItemAlreadyExistsException {
        // ask the user for some content for a to-do item
        System.out.println("Which type of item you want to create?");

        System.out.println("1. basic to do item");
        System.out.println("2. high priority to do item");
        String typeOfTodo = scanner.nextLine();

        System.out.println("Type the content of the item:");
        String content = scanner.nextLine();
        BaseToDo item = null;
        if (typeOfTodo.equals("1")) {
            item = new BaseToDo(content);
        } else if (typeOfTodo.equals("2")) {
            System.out.println("Type the number of seconds you must complete this task");
            int numberOfSeconds = Integer.parseInt(scanner.nextLine());
            item = new HighPriorityToDo(content, LocalDateTime.now().plusSeconds(numberOfSeconds));
            executorService.submit(new DeadlineCheckerRunnable((HighPriorityToDo) item));
        }

        if (user.getItems().contains(item)) {
            throw new ItemAlreadyExistsException("This item is already created in your list!");
        } else {
            //  The instance is added to the list of items the user reference we have received has.
            user.getItems().add(item);
        }
    }

    private static void removeItem(User user) throws NumberFormatException, IndexOutOfBoundsException {
        displayItems(user);
        System.out.println("Enter the number of the item you want to remove:");
        int itemNumber = Integer.parseInt(scanner.nextLine());
        user.getItems().remove(itemNumber - 1); // We remove the index, not the number!!!
    }

    private static void updateItem(User user) throws NumberFormatException, IndexOutOfBoundsException {
        displayItems(user);
        System.out.println("Enter the number of the item you want to remove together with the content");
        String input = scanner.nextLine();
        int itemNumber = Integer.parseInt(input.substring(0, 1));
        String newContent = input.substring(2);

        // An index out of bounds exception can be throwed here, if the user inputs a value out of range
        BaseToDo updatedItem = user.getItems().get(itemNumber - 1);
        updatedItem.setContent(newContent);
        System.out.println("Successfully updated the content!");
    }

    private static void displayItems(User user) {
        System.out.println("Your current items are:");
        List<BaseToDo> items = user.getItems();

        int counter = 1;
        for (BaseToDo item : items) {
            // Displaying each item, one by one, adhering to the format
            // described in the requirement
            // 1. First item content
            // 2. Second item content
            // 3. Third item content etc.
            System.out.format("%d. %s%n", counter, item.getContent());
            counter++;
        }
    }

    private static void generateToDoFile(User user) throws IOException {
        String filePath = "todo-items/" + user.getName() + ".txt";
        Path generatedFile = Paths.get(filePath);

        if (Files.exists(generatedFile)) {
            System.out.println("Found an old file. Overwriting old data.");
            Files.delete(generatedFile);
        }
        Files.createFile(generatedFile);

        // Writing the header
        String header = "TODO items for " + user.getName() + ":\n";
        Files.write(generatedFile, header.getBytes());

        List<BaseToDo> items = user.getItems();
        // Writing each to-do content
        int counter = 1;
        for (BaseToDo item : items) {
            String line = counter + ". " + item.getContent() + ".\n";
            Files.write(generatedFile, line.getBytes(), StandardOpenOption.APPEND);
            counter++;
        }
    }

    private static void importToDoList(User user) throws IOException {
        System.out.println("Choose a file as import source:");

        File[] files = new File("todo-items").listFiles();
        int counter = 1;
        for (File file : files) {
            System.out.format("%d. %s%n", counter, file.getName());
        }

        int fileNumber = Integer.parseInt(scanner.nextLine());
        Path chosenFile = files[fileNumber - 1].toPath();
        List<String> lines = Files.readAllLines(chosenFile);

        if (isSourceValid(lines)) {
            List<BaseToDo> generatedContent = createBaseToDoList(lines);
            user.getItems().addAll(generatedContent);
        }
    }

    private static boolean isSourceValid(List<String> chosenFileContent) throws IOException {

        String firstLine = chosenFileContent.get(0);
        if (!(firstLine.startsWith("TODO items for") &&
                firstLine.endsWith(":"))) {
            return false;
        }

        for (int i = 1; i < chosenFileContent.size(); i++) {
            String currentLine = chosenFileContent.get(i);
            if (!(currentLine.startsWith(i + ".")
                    && currentLine.endsWith("."))) {
                return false;
            }
        }

        return true;
    }

    private static List<BaseToDo> createBaseToDoList(List<String> rawData) {
        // TODO items for {username}:
        // 1. content
        // 2. content
        // 3. content
        List<BaseToDo> items = new ArrayList<>();
        // Skipping the first line (TO DO items for {username})
        for (int i = 1; i < rawData.size(); i++) {
            String line = rawData.get(i);
            items.add(new BaseToDo(line.substring(2))); // Ignoring the number each line has.
            // We only want to read the content, so we substring starting with index 2
        }

        return items;
    }
}
