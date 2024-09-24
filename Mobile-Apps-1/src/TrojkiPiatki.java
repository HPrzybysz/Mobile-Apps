import java.io.*;
import java.util.*;

public class TrojkiPiatki {

    public static void main(String[] args) {
        List<Integer> liczby = new ArrayList<>();

        try {
            File plik = new File("Mobile-Apps-1/src/liczby.txt");
            Scanner scanner = new Scanner(plik);

            while (scanner.hasNextLine()) {
                liczby.add(Integer.parseInt(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Plik nie został znaleziony.");
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
            FileWriter writer = new FileWriter("Mobile-Apps-1/src/trojki.txt");
            for (List<Integer> trojka : dobreTrojki) {
                writer.write(trojka.get(0) + " " + trojka.get(1) + " " + trojka.get(2) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("błąd zapisu");
            e.printStackTrace();
        }

        System.out.println("Liczba dobrych trójek: " + dobreTrojki.size());
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

        return liczbaDobrychPiatek;
    }
}
