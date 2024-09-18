import java.io.*;
import java.util.*;
public class Prime_Factors {
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i*i<=n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        int divider = 2;
        while (n > 1){
            while (n % divider == 0){
                factors.add(divider);
                n /= divider;
            }
            divider++;
        }
        return factors;
    }

    public static void main(String[] args) {
        int MostFactors = 0;
        int MostUniqueFactors = 0;

        int maxFactors = 0;
        int maxUniqueFactors = 0;

        try{
            File file = new File("src/liczby.txt");
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                int number = Integer.parseInt(scanner.nextLine());
                List<Integer> factors = primeFactors(number);
                Set<Integer> uniqueFactors = new HashSet<>(factors);

                if(factors.size() > maxFactors){
                    maxFactors = factors.size();
                    MostFactors = number;
                }

                if(uniqueFactors.size() > maxUniqueFactors){
                    maxUniqueFactors = uniqueFactors.size();
                    MostUniqueFactors = number;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        System.out.println("Number with the most factors: " + MostFactors + " number of factors: " + maxFactors);
        System.out.println("Number with the most unique factors: " + MostUniqueFactors + " number of unique factors: " + maxUniqueFactors);
    }
}
