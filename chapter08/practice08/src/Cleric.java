import java.util.Random;

public class Cleric{
    String name;
    int hp = 50;
    final int MAX_HP = 50;
    int mp = 10;
    final int MAX_MP = 10;

    public void selfAid(){
        System.out.println(this.name + "はセルフエイドを唱えた！");
        this.hp = this.MAX_HP;
        this.mp -= 5;
        System.out.println("HPが最大まで回復した");
    }

    public int pray(int sec){
        System.out.println(this.name + "は" + sec + "秒間天に祈った！");

        // MP回復量を算出
        int recover = new Random().nextInt(3) + sec;

        // 実際のMP回復量を算出
        int recoverActual = Math.min(this.MAX_MP - this.mp, recover);

        this.mp += recoverActual;
        System.out.println("MPが" + recoverActual + "回復した");
        return recoverActual;
    }

    public void slip(){
        this.hp -= 5;
        System.out.println(this.name + "は、転んだ");
        System.out.println("5のダメージ！");
    };
}