// import java.util.Random;

public class Main{
    public static void main(String[] args) {
        // Random random = new Random();
        // int r = random.nextInt(90);
        int r = new java.util.Random().nextInt(90); // 本来はjava.util.Randomをimportする
        System.out.println(r);
    }
}