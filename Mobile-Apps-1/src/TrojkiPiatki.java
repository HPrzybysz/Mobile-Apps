import java.io.*;
import java.util.*;

public class TrojkiPiatki {


    public static void main(String[] args) {
        List<Integer> liczby = new ArrayList<>();

        try {
            File plik = new File("src/liczby.txt");
            Scanner scanner = new Scanner(plik);

            while (scanner.hasNextLine()) {
                liczby.add(Integer.parseInt(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        dobreTrojki(liczby);

        int liczbaDobrychPiatek = dobrePiatki(liczby);
        System.out.println("Liczba dobrych piątek: " + liczbaDobrychPiatek);
    }

    public static void dobreTrojki(List<Integer> liczby) {
        List<List<Integer>> dobreTrojki = new ArrayList<>();

        for (int x : liczby) {
            for (int y : liczby) {
                if (y != x && y % x == 0) {
                    for (int z : liczby) {
                        if (z != y && z != x && z % y == 0) {
                            List<Integer> trojka = Arrays.asList(x, y, z);
                            dobreTrojki.add(trojka);
                        }
                    }
                }
            }
        }

        try {
            FileWriter writer = new FileWriter("src/trojki.txt");
            for (List<Integer> trojka : dobreTrojki) {
                writer.write(trojka.get(0) + " " + trojka.get(1) + " " + trojka.get(2) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occured.");
            e.printStackTrace();
        }

        System.out.println("Liczba dobrych trójek: " + dobreTrojki.size());

        try {
            FileWriter myWriter = new FileWriter("src/wynik4.txt");
            myWriter.write("\n 3) Liczba dobrych trójek: " + dobreTrojki.size());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static int dobrePiatki(List<Integer> liczby) {
        int liczbaDobrychPiatek = 0;

        for (int u : liczby) {
            for (int w : liczby) {
                if (w != u && w % u == 0) {
                    for (int x : liczby) {
                        if (x != w && x != u && x % w == 0) {
                            for (int y : liczby) {
                                if (y != x && y != w && y != u && y % x == 0) {
                                    for (int z : liczby) {
                                        if (z != y && z != x && z != w && z != u && z % y == 0) {
                                            liczbaDobrychPiatek++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        try {
            FileWriter myWriter = new FileWriter("src/wynik4.txt");
            myWriter.write("\n Liczba dobrych piątek: " + liczbaDobrychPiatek);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return liczbaDobrychPiatek;



    }

}


