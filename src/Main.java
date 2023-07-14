import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        Converter converter = new Converter();
        String[] signs = {" + ", " - ", " / ", " * "};
        String[] regexSigns = {" \\+ ", " - ", " / ", " \\* "};

        int signIndex = -1;
        for (int i = 0; i < signs.length; i++) {
            if (input.contains(signs[i])) {
                signIndex = i;
                break;
            }
        }

        if (signIndex == -1) throw new Exception ("Введены символы, не соответствующие ни одной из имеющихся арифметических операций");

        String[] numbers = input.split(regexSigns[signIndex]);

        if (numbers.length > 2) throw new Exception ("Кол-во чисел не может быть больше 2-х");

        String finalRes = "";
        if (converter.isRoman(numbers[0]) == converter.isRoman(numbers[1])) {
            int a, b;

            boolean isRoman = converter.isRoman(numbers[0]);
            if (isRoman) {
                a = converter.romanToArabic(numbers[0]);
                b = converter.romanToArabic(numbers[1]);

            } else {
                a = Integer.parseInt(numbers[0]);
                b = Integer.parseInt(numbers[1]);
            }

            if ((a < 1 | b < 1) | (a > 10 | b > 10)) throw new Exception ("Введённые значения должны быть от 1 до 10.");

            int result = switch (signs[signIndex]) {
                case " + " -> a + b;
                case " - " -> a - b;
                case " * " -> a * b;
                default -> a / b;
            };
            if (isRoman) {
                if (result <= 0) throw new Exception("Результатом работы калькулятора с римскими числами могут быть только положительные числа.");
                finalRes = converter.arabicToRoman(result);
            } else {
                finalRes = String.valueOf(result);
            }
        }
        if (converter.isRoman(numbers[0]) != converter.isRoman(numbers[1])) throw new Exception("Числа должны быть в одном формате.");
        return finalRes;
    }
}