import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("Smart Display Calculator");
        System.out.println("------------------------");
        
        while (running) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.print("Enter your choice : ");
            
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input.Please enter a number between 1 and 5.");
                scanner.nextLine();
                continue;
            }
            
            if (choice == 5) {
                running = false;
                System.out.println("Goodbye!");
                continue;
            }
            
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice.Please select between 1 and 5.");
                continue;
            }
            
            List<Double> numbers = getNumbers(scanner);
            
            if (numbers.size() < 2) {
                System.out.println("At least two numbers are required for calculations.");
                continue;
            }
            
            double result = 0;
            String operation = "";
            String equation = "";
            
            switch (choice) {
                case 1:
                    result = add(numbers);
                    operation = "+";
                    equation = buildEquation(numbers, operation);
                    break;
                case 2:
                    result = subtract(numbers);
                    operation = "-";
                    equation = buildEquation(numbers, operation);
                    break;
                case 3:
                    result = multiply(numbers);
                    operation = "*";
                    equation = buildEquation(numbers, operation);
                    break;
                case 4:
                    try {
                        result = divide(numbers);
                        operation = "/";
                        equation = buildEquation(numbers, operation);
                    } catch (ArithmeticException e) {
                        System.out.println("Error: " + e.getMessage());
                        continue;
                    }
                    break;
            }
            
            if (result == (long) result) {
                System.out.printf("%s = %d%n", equation, (long) result);
            } else {
                System.out.printf("%s = %.2f%n", equation, result);
            }
        }
        
        scanner.close();
    }
    
    private static List<Double> getNumbers(Scanner scanner) {
        List<Double> numbers = new ArrayList<>();
        scanner.nextLine();
        
        System.out.println("Enter numbers separated by spaces:");
        System.out.print("Numbers: ");
        
        String input = scanner.nextLine();
        String[] numberStrings = input.split(" ");
        
        for (String numStr : numberStrings) {
            try {
                double num = Double.parseDouble(numStr);
                numbers.add(num);
            } catch (NumberFormatException e) {
                System.out.println("'" + numStr + "' is not a valid number and will be skipped.");
            }
        }
        
        return numbers;
    }
    
    private static String buildEquation(List<Double> numbers, String operation) {
        StringBuilder equation = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            // Smart display for input numbers too
            if (numbers.get(i) == (long) numbers.get(i).doubleValue()) {
                equation.append(String.format("%d", (long) numbers.get(i).doubleValue()));
            } else {
                equation.append(String.format("%.2f", numbers.get(i)));
            }
            if (i < numbers.size() - 1) {
                equation.append(" ").append(operation).append(" ");
            }
        }
        return equation.toString();
    }
    
    public static double add(List<Double> numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum;
    }
    
    public static double subtract(List<Double> numbers) {
        double result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result -= numbers.get(i);
        }
        return result;
    }
    
    public static double multiply(List<Double> numbers) {
        double product = 1;
        for (double num : numbers) {
            product *= num;
        }
        return product;
    }
    
    public static double divide(List<Double> numbers) throws ArithmeticException {
        double result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            result /= numbers.get(i);
        }
        return result;
    }
}