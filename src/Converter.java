import java.util.TreeMap;

class Converter {
    TreeMap<Character, Integer> romanMap = new TreeMap<>();
    TreeMap<Integer, String> arabianMap = new TreeMap<>();

    public Converter() {
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);

        arabianMap.put(100, "C");
        arabianMap.put(90, "XC");
        arabianMap.put(50, "L");
        arabianMap.put(40, "XL");
        arabianMap.put(10, "X");
        arabianMap.put(9, "IX");
        arabianMap.put(5, "V");
        arabianMap.put(4, "IV");
        arabianMap.put(1, "I");
    }

    public boolean isRoman(String number) {
        return romanMap.containsKey(number.charAt(0));
    }

    public String arabicToRoman(int number) {
        StringBuilder roman = new StringBuilder();
        int arabianKey;
        do {
            arabianKey = arabianMap.floorKey(number);
            roman.append(arabianMap.get(arabianKey));
            number -= arabianKey;
        } while (number != 0);
        return roman.toString();
    }

    public int romanToArabic(String romanString) {
        char[] romanArray = romanString.toCharArray();
        int[] arabicArray = new int[romanArray.length];

        for (int i = 0; i < romanArray.length; i++) {
            arabicArray[i] = romanMap.get(romanArray[i]);
        }

        int previousNumber = 0;
        int summary = 0;

        for (int number : arabicArray) {
            if (number > previousNumber) {
                summary = summary + number - (2 * previousNumber);
            } else {
                summary += number;
            }
            previousNumber = number;
        }
        return summary;
    }
}



