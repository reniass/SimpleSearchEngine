package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        File file = new File(args[1]);

        List<String> lines = new ArrayList<>();


        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Scanner scanner = new Scanner(System.in);
        SearchEngine searchEngine = new SearchEngine(lines);



        boolean exit = false;
        do {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    System.out.println("Bye!");
                    exit = true;
                    break;
                case "1":
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String strategy = scanner.nextLine();

                    switch (strategy) {
                        case "ALL":
                            searchEngine.setSearchStrategy(searchEngine.new SearchAll());
                            break;
                        case "ANY":
                            searchEngine.setSearchStrategy(searchEngine.new SearchAny());
                            break;
                        case "NONE":
                            searchEngine.setSearchStrategy(searchEngine.new SearchNone());
                            break;
                    }

                    searchEngine.searchData();
                    break;
                case "2":
                    searchEngine.printAllPeople();
                    break;
                default:
                    System.out.println("Incorrect option! Try again.\n");
            }

        } while (!exit);
    }

    private static void printMenu() {
        System.out.println("=== Menu ===\n1. Find a person\n2. Print all people\n0. Exit\n " );
    }

}





