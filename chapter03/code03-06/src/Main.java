public class Main{
    public static void main(String[] args){
        System.out.println("あなたの運勢を占います");
        int fortune = new java.util.Random().nextInt(5) + 1;
        switch (fortune) {
            case 1, 2:
                System.out.println("いいね！");   // fortuneの値が1または2の時に実行
                break;  // ここでswitch文から脱出する
            case 3:
                System.out.println("普通です");   // fortuneの値が3の時に実行
                break;
            default:
                System.out.println("う〜ん...");  // fortuneの値が上記以外の時に実行
        }
    }
}