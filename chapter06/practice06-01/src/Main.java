import comment.Zenhan;

public class Main {
    public static void main(String[] args) {
        try {
            Zenhan.doWarusa();
            Zenhan.doTogame();
            comment.Kouhan.callDeae();
            comment.Kouhan.showMondokoro();
        } catch (Exception e){
            System.out.println("エラーが発生しました。プログラムを終了します。");
        }
    }
}