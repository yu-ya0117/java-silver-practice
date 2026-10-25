public class Hero {
    String name;
    int hp;

    public Hero() {
        this("ダミー");
    }

    public Hero(String name) {  // 以前から存在していたコンストラクタ
        this.hp = 100;
        this.name = name;
    }
}