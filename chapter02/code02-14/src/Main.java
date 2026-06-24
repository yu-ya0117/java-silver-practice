// import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        System.out.println("あなたの名前を入力してください。");
        // Scanner sc = new Scanner(System.in);
        // String name = sc.nextLine();
        String name = new java.util.Scanner(System.in).nextLine(); // 本来はjava.util.Scannerをimportする
        System.out.println("あなたの年齢を入力してください。");
        // int age = sc.nextInt();
        int age = new java.util.Scanner(System.in).nextInt();
        System.out.println("ようこそ、" + age + "歳の" + name + "さん");
    }
}