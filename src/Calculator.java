import java.util.Scanner;

public class Calculator {
    private static final String ADD = "+";
    private static final String SUBTRACT = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String operation = getOperation(scanner);
        double a = getNumber(scanner, "первое");
        double b = getNumber(scanner, "второе");
        scanner.close();

        try {
            double result = calculate(operation, a, b);
            printResult(result);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private String getOperation(Scanner scanner) {
        System.out.println("Введите " + ADD + " для сложения, " + SUBTRACT + " для вычитания, "
                + MULTIPLY + " для умножения, " + DIVIDE + " для деления:");
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
}
