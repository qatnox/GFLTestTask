import java.io.*;
import java.util.Scanner;

public class GFLTestTask {
    private static Scanner sc = new Scanner(System.in);
    private static String equation;
    private static File file;

    public static void main(String[] args) {

        while (true) {

            System.out.print("Enter the equation: ");
            equation = sc.nextLine();
            if (equation != null) {
                createFile();

                if (checkBrackets(equation) && equationValidity(equation)) {
                    writeEquation();
                } else {
                    System.out.print("\t> Incorrect equation is entered!\n\n");
                }
            }
        }
    }

    private static void createFile() {
        try {
            String fileName = "equations.txt";
            file = new File(fileName);

            if(file.createNewFile()){
                System.out.printf("\t> File" + " '" + fileName + "' " + "is created.\n");
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static void writeEquation() {
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(equation);
            bw.newLine();
            bw.close();

            System.out.printf("\t> Equation:" + " '" + equation + "' " + "is correct and wrote to the file.\n");
            System.out.printf("\t> Amount of numbers in:" + " '" + equation + "' " + "is: " + countNumbers(equation) + "\n\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean checkBrackets(String equation) {
        int count = 0;

        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);

            if (ch == '(') {
                count++;
            } else if (ch == ')') {
                count--;
            }

            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    private static boolean equationValidity(String equation) {
        String operators = "+-*/=";

        for (int i = 0; i < equation.length() - 1; i++) {
            char current = equation.charAt(i);
            char next = equation.charAt(i + 1);

            if (operators.contains(String.valueOf(current)) && operators.contains(String.valueOf(next))) {
                return false;
            }
        }
        return true;
    }

    private static int countNumbers(String equation) {
        int count = 0;
        boolean isNumber = false;

        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                if (!isNumber) {
                    count++;
                    isNumber = true;
                }
            } else {
                isNumber = false;
            }
        }
        return count;
    }
}
