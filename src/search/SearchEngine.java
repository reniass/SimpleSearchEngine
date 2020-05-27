package search;

import java.util.*;

public class SearchEngine {

    Scanner scanner;
    List<String> linesOfText;
    Map<String, List<Integer>> map;
    SearchStrategy searchStrategy;

    public SearchEngine(List<String> linesOfText) {
        init(linesOfText);
    }

    private void init(List<String> linesOfText) {
        this.scanner = new Scanner(System.in);
        this.linesOfText = linesOfText;
        this.map = new HashMap<>();
        String[] line;
        for (int i = 0; i < linesOfText.size(); i++) {
            line = linesOfText.get(i).split(" ");
            for (int j = 0; j < line.length; j++) {
                String key = line[j].toLowerCase();
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(i);
            }
        }
    }

    public void printAllPeople() {
        if (linesOfText.size() != 0) {
            System.out.println("=== List of people ===");
            for (int i = 0; i < linesOfText.size(); i++) {
                System.out.println(linesOfText.get(i));
            }
        }
        System.out.println();

    }

    public void searchData() {
        System.out.println("Enter a name or email to search all suitable people.");

        searchStrategy.search(scanner.nextLine().toLowerCase());

        System.out.println();
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public class SearchAll implements SearchStrategy {

        @Override
        public void search(String string) {
            String[] words = string.split(" ");


            List<String> foundPersons = new ArrayList<>();

            boolean isLineContainAllWordsFromTheQuery;
            for (int i = 0; i < linesOfText.size(); i++) {
                isLineContainAllWordsFromTheQuery = true;
                for (int j = 0; j < words.length; j++) {
                    if (!map.get(words[j]).contains(i)) {
                        isLineContainAllWordsFromTheQuery = false;
                        break;
                    }
                }

                if (isLineContainAllWordsFromTheQuery) {
                    foundPersons.add(linesOfText.get(i));
                }
            }

            int numberOfFoundPersons = foundPersons.size();
            if (numberOfFoundPersons > 0) {
                System.out.println(numberOfFoundPersons + " persons found: ");
                for (String person : foundPersons) {
                    System.out.println(person);
                }
            } else {
                System.out.println("No persons found.");
            }
        }
    }

    public class SearchAny implements SearchStrategy {

        @Override
        public void search(String string) {
            String[] words = string.split(" ");


            List<String> foundPersons = new ArrayList<>();

            boolean isLineContainAnyWordsFromTheQuery;
            for (int i = 0; i < linesOfText.size(); i++) {
                isLineContainAnyWordsFromTheQuery = false;
                for (int j = 0; j < words.length; j++) {
                    if (map.get(words[j]).contains(i)) {
                        isLineContainAnyWordsFromTheQuery = true;
                        break;
                    }
                }

                if (isLineContainAnyWordsFromTheQuery) {
                    foundPersons.add(linesOfText.get(i));
                }
            }

            int numberOfFoundPersons = foundPersons.size();
            if (numberOfFoundPersons > 0) {
                System.out.println(numberOfFoundPersons + " persons found: ");
                for (String person : foundPersons) {
                    System.out.println(person);
                }
            } else {
                System.out.println("No persons found.");
            }
        }
    }

    public class SearchNone implements SearchStrategy {

        @Override
        public void search(String string) {
            String[] words = string.split(" ");


            List<String> foundPersons = new ArrayList<>();

            boolean isLineContainNoneWordsFromTheQuery;
            for (int i = 0; i < linesOfText.size(); i++) {
                isLineContainNoneWordsFromTheQuery = true;
                for (int j = 0; j < words.length; j++) {
                    if (map.get(words[j]).contains(i)) {
                        isLineContainNoneWordsFromTheQuery = false;
                        break;
                    }
                }

                if (isLineContainNoneWordsFromTheQuery) {
                    foundPersons.add(linesOfText.get(i));
                }
            }

            int numberOfFoundPersons = foundPersons.size();
            if (numberOfFoundPersons > 0) {
                System.out.println(numberOfFoundPersons + " persons found: ");
                for (String person : foundPersons) {
                    System.out.println(person);
                }
            } else {
                System.out.println("No persons found.");
            }
        }
    }
}
