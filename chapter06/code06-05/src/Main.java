import java.util.Arrays;    // 今回はArraysクラスをインポート

public class Main {
    public static void main(String[] args) {
        int[] heights = {172, 149, 152, 191, 155};
        Arrays.sort(heights);   // 昇順に並び替え
        for(int h : heights){
            System.out.println(h);
        }
    }
}