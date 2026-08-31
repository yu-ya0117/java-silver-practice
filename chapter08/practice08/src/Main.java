public class Main{
    public static void main(String[] args) {
        Cleric c = new Cleric();
        c.name = "アユム";
        c.hp = 100;

        c.slip();
        c.slip();
        c.selfAid();
        c.slip();
        c.selfAid();
        c.pray(5);
    }
}