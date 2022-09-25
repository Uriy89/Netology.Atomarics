package task;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static AtomicInteger count3 = new AtomicInteger();
    private static AtomicInteger count4 = new AtomicInteger();
    private static AtomicInteger count5 = new AtomicInteger();
    private final static int MAX_TEXT = 100_000;

    public static void main(String[] args) throws InterruptedException {
        String[] arr = randomVerbs();

        new Thread(() -> {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length() == 3) {
                    char first = arr[i].charAt(0);
                    if (first == arr[i].charAt(2) || first == arr[i].charAt(1) && first == arr[i].charAt(2)){
                        count3.incrementAndGet();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length() == 4) {
                    var chars = arr[i].toCharArray();
                    var left = 0;
                    var right = chars.length - 1;
                    while (left < right) {
                        if (chars[left] == chars[right]) {
                            count4.incrementAndGet();
                        }
                        left++;
                        right--;
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length() == 5) {
                    var chars = arr[i].toCharArray();
                    var left = 0;
                    var right = chars.length - 1;
                    while (left < right) {
                        if (chars[left] == chars[right]) {
                            count5.incrementAndGet();
                        }
                        left++;
                        right--;
                    }
                }
            }
        }).start();

        System.out.println("Красивых слов с длиной 3: " + count3 + " шт");
        System.out.println("Красивых слов с длиной 4: " + count4 + " шт");
        System.out.println("Красивых слов с длиной 5: " + count5 + " шт");
    }



    private static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static String[] randomVerbs() {
        Random random = new Random();
        String[] texts = new String[MAX_TEXT];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        Arrays.sort(texts);
        return texts;
    }


}
