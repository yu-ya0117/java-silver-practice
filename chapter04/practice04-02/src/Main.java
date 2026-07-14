public class Main {
    public static void main(String[] args) {
        int[] moneyList = {121902, 8302, 55100}; // ①
        for (int i = 0; i < moneyList.length; i++) {    // ②for文
            System.out.println(moneyList[i]);
        }
        for (int money : moneyList) {   // ③拡張for文
            System.out.println(money);
        }
    }
}