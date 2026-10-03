# 第10章

学習日: 2026-10-03 〜 （現在学習中）

## 学んだこと
- 継承の基礎
  - 似かよったクラスの開発
  - 「コピペ解決法」の問題点
  - 継承による解決
  - 継承関係の表現方法
  - 継承のバリエーション
  - オーバーライド
  - 継承やオーバーラーイドの禁止
- インスタンスの姿
  - インスタンスの多重構造
  - メソッドの呼び出し
  - 親インスタンス部へのアクセス
- 継承とコンストラクタ
  - 継承を利用したクラスのコンストラクタ
  - 親インスタンス部分が作れない状況
  - 内部インスタンスのコンストラクタ引数を指定する
- 正しい継承、間違った継承
  - is-aの原則
  - 間違った継承の例
  - 間違った継承をすべきでない理由
  - 汎化・特化の関係

## 詰まったこと

## 用語集

- 継承：既存の親クラスの変数やメソッドを新しい子クラス引き継ぎ、コードを効率よく再利用する機能

## 1. 継承の基礎

### 1-1. 似かよったクラスの開発

Javaでは大きなプログラムを作り始めると、  
似かよったクラスを作る必要に迫られることがある。
以下が代表的な例になる。

- ほとんど同じだけれどフィールドが2つ多い
- ほとんど同じだけれどメソッドが1つ多い

このようなクラスはどのようにすれば効率よく作れるだろうか？

例として、勇者クラス(Heroクラス)を取り上げてみる。
ここでは理解しやすくするために、以下のような単純なHeroクラスから始めてみる。

chapter10/code10-01/src/Hero.java
```
public class Hero{
    String name = "ミナト";
    int hp = 100;

    public void attack(Matango m){
        System.out.println(this.name + "の攻撃");
        m.hp -= 5;
        System.out.println("5ポイントのダメージを与えた！");
    }

    public void run(){
        System.out.println(this.name + "は逃げ出した！");
    }
}
```
このHeroは冒険するにつれ進化してゆき、  
次のような能力を持ったSuperHeroという職業になれる。

- スーパーヒーローはfly()で空を飛ぶことができ、land()で着地ができる
- ヒーローができる全ての動作は、スーパーヒーローもできる。

chapter10/code10-01/src/SuperHero.java
```
public class SuperHero{
    String name = "ミナト";
    int hp = 100;
    boolean flying;

    public void attack(Matango m){
        System.out.println(this.name + "の攻撃");
        m.hp -= 5;
        System.out.println("5ポイントのダメージを与えた！");
    }

    public void run(){
        System.out.println(this.name + "は逃げ出した！");
    }

    public void fly(){
        this.flying = true;
        System.out.println("飛び上がった！");
    }

    public void land(){
        this.flying = false;
        System.out.println("着地した！");
    }
}
```

お気づきだろうか。  
これはコピペを使って開発されたものであると。

### 1-2. 「コピペ解決法」の問題点

元となるコードをコピペして、それに新しい機能を追加すれば、  
簡単に元のクラスを発展させることは可能である。  
解決方法はとてもシンプルで、コードも問題なく動作する。

しかし、この方法によって作成されたSuperHeroクラスには、  
以下の2つの問題点が存在する。

#### 1. 追加・修正に手間がかかる

Heroクラスに新しいメソッドを追加する、  
またはHeroクラス内のメソッドを変更する場合、  
それをSuperHeroクラスにも行う必要がある。

なぜなら、スーパーヒーローとは「たくさんいるヒーローの中でも特に優れた一握りの者」だから。  
Heroができることは、当然SuperHeroが全てできなければならない。

#### 2. 把握・管理が難しくなる

SuperHeroクラスはHeroクラスをコピペして作っているので、 
この2つのクラスのソースコードの大半が重複している。  
これにより、プログラム全体の見通しが悪くなり、  
メンテナンスがしづらくなる。

もしかしたら今後、Heroを元にした別のクラス  
（例えばHyperHero、LegendHero、MagicalHeroなど）  
を作る必要が出てくるかもしれない。  
するとHeroクラスに何かしらの変更がある度に、  
全ての◯◯Heroクラスに対してHeroクラスと同じ修正を行う必要が生じる。  
これはとても面倒である。

### 1-3. 継承による解決

「コピペ解決法」を用いて類似したクラスを作成していくと、  
将来元となったクラスが変更されたら全ての類似クラスの修正も必要である。  

しかしJavaには、このようなコードの重複を懸念することなく、  
類似したクラスを作成する機能、継承が存在する。  
これを使えば、SuperHeroクラスは以下のコードのようにスッキリと記述できる。

chapter10/code10-02/src/SuperHero.java
```
public class SuperHero extends Hero{
    boolean flying;

    public void fly(){
        this.flying = true;
        System.out.println("飛び上がった！");
    }

    public void land(){
        this.flying = false;
        System.out.println("着地した！");
    }
}
```

ポイントは1行目のextendsにある。  
`class SuperHero extends Hero`という宣言は、  
「HeroクラスをベースにしてSuperHeroクラスを定義するので、  
Heroと同じメンバの定義は省略する（違いだけを記述する）」という意味になる。

継承を用いたクラス定義
```
public class クラス名 extends 元となるクラス名{
  //  親クラスとの差分となるメンバ
}
```

このSuperHeroクラスがインスタンス化されるときに、  
JVMは、省略されているけれども、SuperHeroクラスはHeroクラスに含まれている  
name、hp、attack()、run()も持っていると判断してくれる。  

よって、**SuperHeroクラスのソースコードにはrun()はないが、  
インスタンス化させればrun()を呼び出すことができる。**

chapter10/code10-02/src/Main.java
```
public class Main {
    public static void main(String[] args) {
        SuperHero sh = new SuperHero();
        sh.run();
    }
}
```

このようにextendsを用いて、**元となるクラスの「差分」だけを記述して新たなクラスを宣言できる。**

新たに定義するクラス(SuperHero)に着目すると、  
まるで元のクラス(Hero)から、メンバが自動的に引き継がれているように見えることから、  
「継承」という名前が付いている。