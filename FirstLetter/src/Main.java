import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please insert a name");
        Scanner inputFromUser = new Scanner(System.in);
        String fullName = inputFromUser.nextLine();
        String[] splittedName = fullName.split(" ");
        for (String name : splittedName) {
            System.out.print(name.toUpperCase().charAt(0));
        }
    }
}
