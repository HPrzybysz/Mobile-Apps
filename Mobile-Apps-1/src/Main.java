import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int counter = 0;
        String first_number = null;

        try{
            File file = new File("./liczby.txt");
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String number = scanner.nextLine();
                if(number.charAt(0) == number.charAt(number.length()-1)) {
                    counter++;

                    if(first_number == null) {
                        first_number = number;
                    }
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }

        System.out.println("Number of numbers which first and last digit are the same: " + counter);
        System.out.println("First number which meet the conditions: " + first_number);

        try {
            FileWriter myWriter = new FileWriter("./wynik4.txt");
            myWriter.write(" 1) \nNumber of numbers which first and last digit are the same: " + counter +
                    '\n' + "First number which meet the conditions: " + first_number + '\n');
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}