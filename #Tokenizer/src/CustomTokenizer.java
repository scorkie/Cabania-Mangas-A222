import java.util.regex.Pattern;

public class CustomTokenizer {

    // Custom tokenizer and classifier (Phase 1)
    public static void tokenizeAndClassify(String input) {
        String[] tokens = splitByDelimiter(input, '#');
        for (String token : tokens) {
            String type = classifyToken(token);
            System.out.println("Token: \"" + token + "\" - Type: " + type);
        }
    }

    // Function to split the input string by the delimiter
    public static String[] splitByDelimiter(String input, char delimiter) {
        return input.split(String.valueOf(delimiter));  // Split based on #
    }

    // Classify token
    public static String classifyToken(String token) {
        if (token.matches("\\s+")) {  // Match any sequence of spaces
            return "Space";
        } else {
            token = token.trim();  // Trim spaces around the token for proper classification

            if (token.matches("[a-zA-Z]+")) {
                return "Word";
            } else if (token.matches("\\d+(\\.\\d+)?")) {
                return "Number";
            } else if (token.matches("[a-zA-Z0-9]+")) {
                return "Alphanumeric";
            } else if (token.matches("\\p{Punct}")) {
                return "Punctuation";
            } else if (Pattern.matches("^(http|https)://.*$", token)) {
                return "URL"; 
            } else if (token.matches("[a-zA-Z0-9\\s]+") && token.contains(" ")) {
                return "Sentence";  // Classify multi-word tokens as Sentence
            } else if (token.equals("\n")) {
                return "End of Line";
            } else {
                return "Unknown";
            }
        }
    }

    // Granular breakdown (Phase 2)
    public static void granularBreakdown(String input) {
        String[] tokens = splitByDelimiter(input, '#');
        for (String token : tokens) {
            System.out.print("Token: \"" + token + "\" -> ");
            for (char c : token.toCharArray()) {
                System.out.print("'" + c + "', ");
            }
            System.out.println();
        }
    }
}
