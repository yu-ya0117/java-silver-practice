public class Hero {
    String name;
    int hp;
    Sword sword;

    public Hero() {
        this("ダミー");
    }

    public Hero(String name) {  // 以前から存在していたコンストラクタ
        this.hp = 100;
        this.name = name;
    }

    public void attack() {
        System.out.println(this.name + "は" + this.sword.name + "で攻撃した！");
        System.out.println("敵に" + this.sword.damage + "ポイントのダメージをあたえた！");
    }
}