public class Main{
    public static void main(String[] args) {
        Cleric c = new Cleric();
        c.name = "アユム";

        c.slip();
        c.slip();
        c.selfAid();
        c.slip();
        c.pray(3);  //  MPが3〜5回復する(5でMAXになる)
        c.selfAid();
    }
}