package net.spechtacular;

import java.util.LinkedList;
import java.util.logging.Logger;

public class QueueAssignment {

    private static LinkedList<Character> stack;
    private static LinkedList<Character> queue;
    private static Logger LOGGER;

    public QueueAssignment(Logger LOGGER) {
        this.LOGGER=LOGGER;
    }

    public static boolean checkForPalindrome(String string) {

        LOGGER.info("checkForPalindrome:"+string);
        stack = new LinkedList<Character>();
        queue = new LinkedList<Character>();
        String lowerCase = string.toLowerCase();

        for (int i = 0; i < lowerCase.length(); i++) {
            char c = lowerCase.charAt(i);
            if (c >= 'a' && c <= 'z') {
                queue.addLast(c);
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (!stack.pop().equals(queue.removeFirst())) {
                LOGGER.info("checkForPalindrome:"+string+" false");
                return false;
            }
        }
        LOGGER.info("checkForPalindrome:"+string+" true");
        return true;
    }

}
