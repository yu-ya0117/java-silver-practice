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

- 継承：既存の親クラスの変数やメソッドを新しい子クラス引き継ぎ、コードを効率よく再利用する機能。
- 親クラス：継承元となるクラス、スーパークラスともいう。
- 子クラス：親クラスを継承して新たしく定義されるクラス、サブクラスともいう。
- 継承関係：親クラスを継承して子クラスを作成するという2つのクラスの関係。
- 多重継承：複数のクラスを親として子クラスを定義することだが、Javaでは許されてはいない。
- オーバーライド：親クラスを継承して子クラスを宣言する際に、親クラスのメンバを子クラス側で上書きすること
- super：子クラスから親クラスの変数やメソッド、コンストラクタにアクセスするための予約語

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

### 1-4. 継承関係の表現方法

今回はHeroクラスを継承してSuperHeroクラスを作成した。  
このように、親クラスから子クラスを作成するといった2つのクラスの関係を継承関係という。

継承元のクラスのことを親クラス、あるいはスーパークラスなどと呼ぶ。  
そしてこの親クラスをもとに、新たに定義されるクラスのことを子クラス、あるいはサブクラスなどと呼ぶ。

継承関係を図で表現すると以下のようになる。  

[![Image from Gyazo](https://i.gyazo.com/9ca143d9edd80e98082b78d50f0581ec.png)](https://gyazo.com/9ca143d9edd80e98082b78d50f0581ec)

矢印の向きは、子クラス→親クラスになることに注意。  
なぜこの方向に矢印を向けるのかは後述する。  
今の段階では、「クラス図では、継承の矢印は直感とは逆に、子クラス→親クラスに向いていく」  
以上だけを覚えておこう。

### 1-5. 継承のバリエーション

継承は2つのクラスの関係を表すだけにとどまらない。  
1つのクラスをベースとして、  
複数の子クラスを定義することもできるし、  
孫クラスや曾孫クラスの定義も可能である。

[![Image from Gyazo](https://i.gyazo.com/fa3809c31db1db52328e2f9e1e7c0637.png)](https://gyazo.com/fa3809c31db1db52328e2f9e1e7c0637)

ただし複数のクラスを親として1つのクラスを定義することを多重継承というが、  
Javaにおいてはこれを許していない。

### 1-6. オーバーライド

chapter10/code10-02/src/SuperHero.javaではfly()とland()の2つのメソッドしか定義していなかった。  
SuperHeroクラスに限り、run()の動きを変化させたい場合は、  
以下のようにSuperHeroクラスのコードに新しいrun()を定義することができる。  
親クラスのHeroクラスにもrun()は存在するが、  
子クラスのSuperHeroクラスにもrun()を記載することで、  
SuperHeroクラスのrun()の動きを変化させることができる。

chapter10/code10-02/src/SuperHero.javaを改変
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

    public void run(){  //  親クラスでも定義してあるが、子クラスで再定義するメソッド
        System.out.println(this.name + "は撤退した");
    }
}
```
Heroクラスのrun()とSuperHeroクラスのrun()をMainクラスで呼び出してみる。

chapter10/code10-02/src/Main.javaを改変
```
public class Main {
    public static void main(String[] args) {
        Hero h = new Hero();
        h.run();
        SuperHero sh = new SuperHero();
        sh.run();
    }
}
```

実行結果は以下の通り
```
ミナトは逃げ出した！
ミナトは撤退した
```

SuperHeroクラスの1行目で、基本的にはHeroクラスをベースにSuperHeroクラスを定義すると宣言したが、  
SuperHeroクラスの14行目でrun()を異なる内容で定義し直している（内容の上書き）。

このように、親クラスを継承して子クラスを宣言する際に、  
親クラスのメンバを子クラス側で上書きすることをオーバーライドという。

以前メソッドのオーバーロードというものを取り扱ったが、  
全く異なるものなので注意！

オーバーライドの特徴として、  
継承を用いて子クラスに宣言されたメンバは以下のように扱われる。

1. 親クラスに同じメンバがいなければ、そのメンバは「追加」される。
2. 親クラスに同じメンバがあれば、そのメンバは「上書き」される。

### 1-7. 継承やオーバーライドの禁止

例えば「文字数の長さ制限があるLimitStringクラス」を作成したい場合、通常だとどうする？

```
public class LimitString extends String {
    //  処理内容
}
```

しかし上記ではコンパイルエラーになってしまう。  
なぜだろうか？

実は継承しようとしているStringクラス(java.lang.String)は、  
このクラスを継承して他のクラスを作成してはいけないという特別な指定が存在する。

ここで実際にJavaのAPIリファレンスを見てみよう。

```
public final class String extends Object ...
```
  
リファレンスには上記のように記載されている。  
Javaでは**宣言にfinalが付けられているクラスは継承できない**ことになっている。

自分たちで作成したクラスにfinalをつければ「継承禁止」にできる。  
例として、Mainクラスの継承を禁止するには、クラス宣言にfinalを追記する。

chapter10/code10-02/src/Main.javaをさらに追記
```
public final class Main {
    public static void main(String[] args) {
        Hero h = new Hero();
        h.run();
        SuperHero sh = new SuperHero();
        sh.run();
    }
}
```
不具合なく完璧に動作するクラスがあっても、  
技術力がない人がそのクラスを継承し、  
オーバーライドによってメソッド内容をメチャクチャに上書きしてしまったら、  
「異常な動作をする子クラス」が出来上がってしまう。

この「異常な動作をする子クラス」は、親クラスと似ているようで、  
その内容は全く異なる困った類似品であったり、  
バグの原因となる危険性がある。

特にStringクラスの場合は、  
プログラム内で多用される大切なクラスであるため、  
「正しく動作しないStringの類似品」が出回ると致命的な不具合になりかねない。

以上の理由からStringクラスにはfinalがつけられており、
継承を禁止して全てのメソッドはオーバーライドできないようになっている。

ではクラスの継承そのものは許可するものの、  
一部のメソッドのみオーバーライドを禁止することは可能なのか？

以下のように、オーバーライドを禁止したいメソッドにfinalを追記してあげればよい。

chapter10/code10-02/src/Hero.javaを追記
```
public class Hero{
    String name = "ミナト";
    int hp = 100;

    public void attack(Matango m){
        System.out.println(this.name + "の攻撃");
        m.hp -= 5;
        System.out.println("5ポイントのダメージを与えた！");
    }

    public final void slip(){   //  finalがついてるslipメソッドはオーバーライド禁止
        this.hp -= 5;
        System.out.println(this.name + "は転んだ！");
        System.out.println("5のダメージ");
    }

    public void run(){    // runメソッドは子クラスでオーバーライドをしている
        System.out.println(this.name + "は逃げ出した！");
    }
}
```

ここまでオーバーロードについて取り扱ってきたが、  
最後に注意点がある。  
それは**フィールドはオーバーライドさせない**ということである。

これまでのオーバーライドは全てメソッドに関する動作であり、  
フィールドは別の動きをしてしまう。  
実用上ほぼ用いられないため、説明は割愛するが、  
誤って親クラスと子クラスに同名のフィールドを宣言すると、  
意図しない動作をする可能性がある。

ここまでオーバーライドについて一言でまとめると、
- クラスにfinalをつけると継承を禁止できる
- メソッドの宣言にfinalをつけるとオーバーライドを禁止できる
- フィールドはオーバーライドさせない

## 2. インスタンスの姿

### 2-1. インスタンスの多重構造

より踏み込んで継承を使いこなすには、  
継承を用いて定義されたSuperHeroのようなクラスから生まれたインスタンスが、  
実際にどのような姿をして、どのように振る舞うのかを理解しておくことが重要である。

継承を用いて生成されたインスタンスの姿を正しくイメージできれば、  
今後の学習もスムーズに理解できるようになる。

Heroクラスを継承したSuperHeroクラスのインスタンスのイメージは以下のようになる。

[![Image from Gyazo](https://i.gyazo.com/3ffd8a35e6f288236fd73af4ffc2384c.png)](https://gyazo.com/3ffd8a35e6f288236fd73af4ffc2384c)

このインスタンスは、外から見ると1つのSuperHeroインスタンスであるが、  
内部にHeroクラスから生まれたHeroインスタンスを含んでおり、  
全体として二重構造になっている。  
（※孫クラスがある場合は三重構造になる。）

ここからは、SuperHeroで定義したメンバの部分を「子インスタンス部分」、  
Heroから受け継いだメンバの部分を「親インスタンス部分」と呼ぶことにする。  
このイメージ図を通してさまざまな呼び出しや動作の仕組みを考える。

### 2-2. メソッドの呼び出し

インスタンスの外からメソッドの実行依頼が届く（呼び出しがある）と、  
多重構造のインスタンスは、**極力子インスタンス部分のメソッドで対応しようとする。**  

例えばfly()が呼び出されれば、SuperHeroクラスで定義されたfly()が動く。  
一方、attack()への呼び出しは、まずは子インスタンス部分で対応しようとするが、  
子インスタンス部分にはattack()は存在しない。  
次に親インスタンス部分のattack()に呼び出しが届き、それが動作する。  

run()はSuoerHeroとHeroの両方のクラスで定義（オーバーライド）されており、  
SuperHeroインスタンスは「SuperHeroとしての逃げ方」と「Heroとしての逃げ方」の両方を持っている。  
この状態でrun()を呼び出された場合、子インスタンス部分のSuperHeroとしてのrun()が優先的に動作される。  
そのため、親インスタンス部分のrun()が動くことはない。

### 2-3. 親インスタンス部へのアクセス

では親インスタンス部分のrun()にはアクセスできないのか？  
そんなことはなく、親インスタンスのメソッドが役に立つことがある。  

頻度は多くないが、親インスタンス部分に属するメソッドが大活躍することがある。  
例えば次のような場合を想定してみる。  

#### SuperHeroの追加仕様
**SuperHeroは、空を飛んでいる状態でattack()すると、  
Heroでは1回だった攻撃を2回連続で繰り出すことができる。**

これを実現するには、次のようなオーバーライドを思いつくかもしれない。

chapter10/code10-03/src/SuperHero.java
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

    public void run(){
        System.out.println(this.name + "は撤退した");
    }

    public void attack(Matango m){  //  Heroクラスからオーバーライド
        System.out.println(this.name + "の攻撃！");
        m.hp -= 5;
        System.out.println("5ポイントのダメージをあたえた！");
        if(this.flying){
            System.out.println(this.name + "の攻撃！");
            m.hp -= 5;
            System.out.println("5ポイントのダメージをあたえた！");
        }
    }
}
```
しかしこの方法では、将来Heroクラスの処理が変更されたときに困った事態に陥ることになる。

例えばHeroクラスのattack()が修正され、  
1回の攻撃で敵に与えるダメージが10に変更されたとする。
SuperHeroインスタンスを生み出し、fly()を呼び出した後でattackを呼び出したらどうなるだろうか？
Mainクラスが以下のコードとする。

chapter10/code10-03/src/Main.java
```
public final class Main {
    public static void main(String[] args) {
        SuperHero sh = new SuperHero();
        Matango m = new Matango();
        sh.fly();
        sh.attack(m);
    }
}
```
実行結果は以下の通り
```
飛び上がった！
ミナトの攻撃！
5ポイントのダメージをあたえた！
ミナトの攻撃！
5ポイントのダメージをあたえた！
```
なぜ5ポイントが2回なのか？

attack()はSuperHeroクラスでオーバーライドされており、  
SuperHeroクラスの方には変更がないためである。

このような場合は、以下のように内部で親インスタンスのメソッドを呼び出せれば解決する。  

chapter10/code10-03/src/SuperHero.javaを修正
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

    public void run(){
        System.out.println(this.name + "は撤退した");
    }

    public void attack(Matango m){  //  Heroクラスからオーバーライド
        super.attack(m);  //  superで親クラスのattack()へアクセス
        if(this.flying){
            super.attack(m);
        }
    }
}
```
superとは、現在のクラスの親インスタンス部分を表す予約語である。  
これを利用すれば、親インスタンス部分のメソッドやフィールドに子インスタンス部分からアクセスできる。

親インスタンスのアクセスのまとめ
親インスタンスのフィールドを利用する方法：super.フィールド名
親インスタンスのメソッドを呼び出す方法：super.メソッド名(引数)

ここで疑問が残る。  
なぜattack()ではなくてsuper.attack()なのか？

ただ単にattack()とすると、それはthis.attack()と同じ意味になる。  
thisは「自分自身のインスタンス」を意味するが、  
より正確には「インスタンスの最も外側の部分」を指すことになり、  
自分自身のattackを呼び出す無限ループになってしまう。  
そのため、親クラスを呼び出す際は必ずsuperをつけること。

また、親の親のインスタンス部分にはアクセスはできない。  
Aクラスが親の親、Bクラスが親、Cクラスが自分という3つのクラスの継承関係にあるとき、  
Cクラスから生成されたインスタンスは三重構造になる。  
このとき、一番外側にいるCクラスについて考える。

Cクラスのメソッドは、一番外側（自身）へはthis、  
そして親インスタンス部分へはsuperでアクセスが可能である。  
しかし、親の親にあたるインスタンス部分へアクセスするする手段は準備されていない。  
以上から、CクラスのメソッドがAクラスのインスタンス部分に直接アクセスすることはできない。