import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    private static final String ADD = "+";
    private static final String SUBTRACT = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";
    private static final String READ = "r";
    private static final String CLEAR = "c";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String operation = getOperation(scanner);

        switch (operation) {
            case READ: {
                scanner.close();
                readHistoryFromFile();
                break;
            }
            case CLEAR: {
                scanner.close();
                clearHistoryFromFile();
                break;
            }
            default: {
                double a = getNumber(scanner, "первое");
                double b = getNumber(scanner, "второе");
                scanner.close();

                try {
                    double result = calculate(operation, a, b);
                    printResult(result);
                    saveHistoryToFile(operation, a, b, result);
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            }
        }
    }

    private String getOperation(Scanner scanner) {
        System.out.println("Введите " + ADD + " для сложения, " + SUBTRACT + " для вычитания, "
                + MULTIPLY + " для умножения, " + DIVIDE + " для деления." +
                "\nИли " + READ + " для просмотра истории операций, "
                + CLEAR + " для очистки истории операций:");
        return scanner.next();
    }

    private double getNumber(Scanner scanner, String order) {
        System.out.println("Введите " + order + " число:");
        return scanner.nextDouble();
    }

    private double calculate(String operation, double a, double b) {
        switch (operation) {
            case ADD: return a + b;
            case SUBTRACT: return a - b;
            case MULTIPLY: return a * b;
            case DIVIDE:
                if (b == 0) throw new IllegalArgumentException("Деление на ноль!");
                return a / b;
            default: throw new IllegalArgumentException("Неверная операция!");
        }
    }

    private void printResult(double result) {
        System.out.println("Результат: " + result);
    }

    private void saveHistoryToFile(String operation, double a, double b, double result){
        try (FileWriter writer = new FileWriter("history.txt", true)) {
            writer.write(a + operation + b + "=" + result + "\n");
        } catch (IOException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
        }
    }

    private void readHistoryFromFile(){
        try {
            List<String> lines = Files.readAllLines(Paths.get("history.txt"));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private void clearHistoryFromFile(){
        try (FileWriter writer = new FileWriter("history.txt")) {
            //очистка файла
        } catch (IOException e) {
            System.err.println("Ошибка при очистке файла: " + e.getMessage());
        }
    }

}
