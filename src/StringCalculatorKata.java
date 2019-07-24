import java.util.ArrayList;

public class StringCalculatorKata {

	public static String add(String number) {

		String result = "";
		String delimiter = "";
		if (number.equals("")) {
			result = "0";
		} else {
			delimiter = getDelimiter(number);
			number = stripDelimiterLine(number);
			result = addNumbers(number, delimiter);
		}
		return result;
	}

	private static String stripDelimiterLine(String number) {

		if (number.startsWith("//")) {
			int pos = number.indexOf("\n");
			number = number.substring(pos + 1);
		}
		return number;
	}

	static String getDelimiter(String number) {

		String delimiter = ",";
		if (number.startsWith("//")) {
			int pos = number.indexOf("\n");
			delimiter = number.substring(2, pos);
		}
		return delimiter;
	}

	private static String addNumbers(String number, String delimiter) {

		String result;
		if (number.substring(number.length() - 1).equals(delimiter)) {
			result = "Number expected but EOF found.";
		} else {
			int sum = 0;
			// https://stackoverflow.com/questions/21524642/splitting-string-with-pipe-character
			// | is a metacharacter in regex. You'd need to escape it:
			String[] arr = number.replace("\n", delimiter).split((delimiter.equals("|") ? "\\|" : delimiter));
			ArrayList<Integer> negativeNumbers = new ArrayList<>();
			String unexpectedChars = "";
			for (int i = 0; i < arr.length; i++) {
				try {
					int num = Integer.parseInt(arr[i]);
					if (num < 0) {
						negativeNumbers.add(num);
					} else {
						sum += num;
					}
				} catch (NumberFormatException e) {
					unexpectedChars = arr[i].replaceAll("[0-9]", "");
				}
			}
			if (!unexpectedChars.equals("")) {
				result = "'" + delimiter + "' is expected, other unexpected characters found: " + unexpectedChars;
			} else if (negativeNumbers.size() > 0) {
				result = "Negative not allowed : ";
				for (int i = 0; i < negativeNumbers.size(); i++) {
					result += negativeNumbers.get(i) + ((i < negativeNumbers.size() - 1) ? ", " : "");
				}
			} else
				result = Integer.toString(sum);
		}
		return result;
	}
}
