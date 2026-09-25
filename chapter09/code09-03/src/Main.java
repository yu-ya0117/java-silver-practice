public class Main {
    public static void main(String[] args) {
        Hero h1 = new Hero("ミナト");
        System.out.println(h1.name + " HP：" + h1.hp);

        Hero h2 = new Hero();
        System.out.println(h2.name + " HP：" + h2.hp);
    }
}