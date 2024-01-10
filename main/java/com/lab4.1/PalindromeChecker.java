public class PalindromeChecker {

    public static boolean isPalindrome(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        int length = word.length();
        for (int i = 0; i < length / 2; i++) {
            if (word.charAt(i) != word.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String word1 = "level";
        String word2 = "hello";
        String word3 = "eye";
        String word4 = "dog";

        System.out.println(word1 + " is a palindrome: " + isPalindrome(word1));
        System.out.println(word2 + " is a palindrome: " + isPalindrome(word2));
        System.out.println(word3 + " is a palindrome: " + isPalindrome(word3));
        System.out.println(word4 + " is a palindrome: " + isPalindrome(word4));
    }
}
