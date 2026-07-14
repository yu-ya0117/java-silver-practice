public class Main {
    public static void main(String[] args) {
        int[] numbers = {3, 4, 9};  // ①
        System.out.println("1桁の数字を入力してください");   // ②
        int input = new java.util.Scanner(System.in).nextInt(); // ③
        for(int num : numbers) {    // ④
            if(input == num){
                System.out.println("アタリ！");
            }
        }
    }
}