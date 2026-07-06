package utilities;
import java.util.Random;

public class Random_Email {

    public static String getRandomEmail() {
        Random rand = new Random();
        return "autotest" + rand.nextInt(99999) + "@gmail.com";
    }
}
