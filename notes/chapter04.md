# 第3章

学習日: 2026-07-04 〜　（現在学習中）

## 学んだこと

- 配列のメリット
  - 配列を使わない問題点
  - 配列とは
- 配列の書き方
  - 配列の作成方法
  - 配列の利用
  - 配列の初期化
  - 配列の省略記法
- 配列とfor文
  - パターン1：ループによる全要素の利用
  - パターン2：ループによる集計
  - パターン3：添え字に対応した情報の利用
  - 拡張for文
- 配列の舞台裏
  - メモリと変数
  - メモリと配列
  - 配列を複数の変数で参照する
- ガベージコレクション
- null
  - NullPointerException
- 多次元配列

## 詰まったこと
配列とはというのを文字だけで説明するのが難しいと思ったので今回は図を挿入した。

## 用語集 & 補足

### 1. 用語

- 配列：一つの種類の複数のデータを並び順で格納するデータ構造のこと。
- 要素：配列の中に連続して並んでいる変数のような箱のうちのひとつ。
- 添え字：配列の各要素に付けられている番号のこと。インデックスともいう。
- new演算子：メモリ上に配列の実体を確保し、指定した要素数の箱を使用する準備をするための宣言。
- 例外：Javaにおいてはコンパイルではなく、実行時に発生するエラーのこと。
- 拡張for文：配列の要素を一つずつ取り出し繰り返し処理を行う特殊なループ。
- アドレス：メモリ上の位置を表す参照値のこと。
- 参照：配列の先頭およびオブジェクトの実体がメモリ上のどこにあるかを示すこと。
  - 参照型：メモリ上の番地を代入する変数型。
  - 基本型：intやbooleanなどの変数を格納する型、プリミティブ型ともいう。
- ガベージコレクション：実行中のプログラムが生み出したメモリ上のゴミを自動的に探し出して片付ける仕組み。
- null：何もない状態を表す値。
- 多次元配列：配列の要素としてさらに配列が格納されているデータ構造、配列の配列。

### 2.配列のメリット

#### 2-1. 配列を使わない問題点

chapter04/code04-01/src/Main.java
```
public class Main {
    public static void main(String[] args) {
        int sansu = 20;
        int kokugo = 30;
        int rika = 40;
        int eigo = 50;
        int shakai = 80;
        int sum = sansu + kokugo + rika + eigo + shakai;

        int avg = sum / 5;
        System.out.println("合計点：" + sum);
        System.out.println("平均点：" + avg);
    }
}
```
このコードの不便なところ

1. テスト科目が増えるたびに追加しなければならない
2. まとめて処理できない

そこで使われるのか、今回の配列である。

#### 2-2. 配列とは

[![Image from Gyazo](https://i.gyazo.com/5e9b9ffd0b7dde0b8758fd0894cf663a.png)](https://gyazo.com/5e9b9ffd0b7dde0b8758fd0894cf663a)

配列には変数ような箱が連続して並んでいて、そのうちの1つを要素という。  
その要素に付けられている番号を添え字またはインデックスという。  
ポイントにも書いてある通り、インデックスは0から始まる。

また各要素には同じ種類のデータしか保存できないのも特徴。  
例えば配列をintと決めた場合、この配列の要素は全てint型になる。

### 3. 配列の書き方

#### 3-1. 配列の作成方法

配列の書き方は以下の3つのステップからなる。
1. 配列の作成
2. 要素の作成
3. 要素を配列変数に代入

#### STEP1. 配列変数を宣言

要素の型[] 配列変数名;

int型の要素を配列に代入する場合の配列変数の宣言例
```
int[] scores;
```
ここで使われているint[]は、intとよく似ているが別物である点に注意。
配列は参照型である。

#### STEP2. 要素の作成

int型の要素を5個作成する場合
```
new int[5];
```
ここで初めてnew演算子が出てくる。  
new演算子は指定された型の要素を[]内に指定された数だけ作成する。  


#### STEP3. 要素を配列変数に代入

上記STEP2で作成した要素を、=を使って配列変数に代入すると以下のようになる。
```
scores = new int[5];
```
ここは以前学習した変数の代入と変わらない。

まとめると以下のようになる。
```
public class Main {
    public static void main(String[] args) {
        int[] scores;
        scores = new int[5];
    }
}
```

配列変数の宣言から要素の代入までをひとまとめにすることもできる。
```
public class Main {
    public static void main(String[] args) {
        int[] scores = new int[5];
    }
}
```
こっちの方がよく見かける形かもしれない。

#### 配列の要素数の調べ方

配列変数名.length

配列の要素数を調べる時はlength、カッコはつけない。  
文字列の長さを調べる時はlength()、こちらはカッコをつける。  
紛らわしいので注意！

chapter04/code04-02/src/Main.java
```
public class Main {
    public static void main (String[] args){
        int[] scores = new int[5];
        int num = scores.length;
        System.out.println("要素の数：" + num);
    }
}
```

#### 3-2. 配列の利用

以下は配列scoresの2番目の要素に30を代入して出力するプログラム  
(chapter04/code04-03/src/Main.java)
```
public class Main {
    public static void main(String[] args) {
        int[] scores;
        scores = new int[5];
        scores[1] = 30;
        System.out.println(scores[1]);
    }
}
```
配列の最初のインデックスは0であるため、  
配列scoresの2番目の要素と言われた場合はscores[1]になる。

#### 3-3. 配列の初期化

配列の要素は自動的に初期化される。  
いきなり利用してもコンパイルエラーにはならない。  
以下の例では、5つの配列の要素を全て0で初期化している。  
(chapter04/code04-04/src/Main.java)
```
public class Main {
    public static void main(String[] args) {
        int[] scores = new int[5];  // ここで全ての要素が0で初期化される
        System.out.println(scores[0]);
    }
}
```

初期化される値は、要素の方によって決まる。
・intやdoubleなどの数値型 → 0
・boolean → false
・String型 → null


#### 3-4. 配列の省略記法

配列は以下の2パターンで省略できる。

1. 要素の型[] 配列変数名 = new 要素の型[] {値1, 値2, 値3, ・・・};
2. 要素の型[] 配列変数名 = {値1, 値2, 値3, ・・・};

それぞれの省略例
```
int[] scores = new int[] {20, 30, 40, 50, 80};
int[] scores = {20, 30, 40, 50, 80};
```
2のパターンの方が省略形としてよく見られる。

### 4.配列と例外

配列でよくある間違い

```
public class Main {
    public static void main(String[] args) {
        int[] scores = {20, 30, 40, 50, 80};
        int sum = scores[1] + scores[2] + scores[3] + scores[4] + scores[5];    // 例外発生
        int avg = sum / socres.length;
        System.out.println("合計点：" + sum);
        System.out.println("平均点：" + avg);
    }
}
```
プログラムを実行するとどうなるの？：  
存在しない要素をコード内で使っていても、コンパイルは通る。  
しかし、このプログラムを実行すると、その行を処理しようとした際に、  
ArrayIndexOutOfBonusExceptionという例外のエラーメッセージが表示される。
するとプログラムは中断されてしまう。

なぜこれでエラーが発生するの？：  
scoresの配列はsocres[4]までしか作成していないにも関わらず、  
存在しない要素であるscores[5]を使用しようとしている。

### 4. 配列とfor文

chapter03でループを学んできたが、  
このループを使えば配列をよりスマートに扱える。  
まずは配列をつかったループの基本形  
(chapter04/code04-05/src/Main.java)
```
public class Main {
    public static void main(String[] args) {
        int[] scores = {20, 30, 40, 50, 80};
        for(int i = 0; i < scores.length; i++){
            System.out.println(scores[i]);
        }
    }
}
```

以下覚えておきたい配列活用の定石  
パターン1：ループによる全要素の利用  
パターン2：ループによる集計  
パターン3：添え字に対応した情報の利用

#### 4-1. パターン1：ループによる全要素の利用

配列の最初から最後まで、全要素を順番にアクセスするやり方。  
上記のサンプルコードはこのパターンにあたる。  
インデックスはループ変数を使用しているため、  
ループのたびにo→1→2→3→4と変化して、  
先頭のscores[0]から最後のscores[4]まで順にアクセスしている。  
もし科目が増減して要素が変わったとしても、for文の記述には一切影響はない。

俗にいう「配列を」回すの基本形とも言える。

forループで配列を回す方法：
```
for(int i = 0; i < scores.length; i++){
  // 配列変数名[i]を使った処理
}
```

#### 4-2. パターン2：ループによる集計

点数管理プログラム
(chapter04/code04-01/src/Main.javaの改良版)
(chapter04/code04-06/src/Main.java)
```
public class Main {
    public static void main(String[] args) {
        int[] scores = {20, 30, 40, 50, 80};
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
        }
        int avg = sum / scores.length;
        System.out.println("合計点：" + sum);
        System.out.println("平均点：" + avg);
    }
}
```

50点以上の科目の数を調べるプログラム
```
public class Main {
    public static void main(String[] args) {
        int[] scores = {20, 30, 40, 50, 80};
        int count = 0;
        for(int i = 0; i < scores.length; i++){
            if (scores[i] >= 50 ){
                count++;
            }
        }
        System.out.println("50点以上の科目の数は：" + count);
    }
}
```

#### 4-3. パターン3：添え字に対応した情報の利用

【ここで問題】

0〜3のの整数がランダムに格納された10個の要素を持つ配列seqがあるとする。  
各要素の整数は、実はDNAを構成する4種類の塩基を意味していて、  
画面には0,1,2,3という数字ではなく、  
それぞれの整数に対応させたA,T,G,Cという塩基記号で表示するにはどうすればよいか。

まずは通常の方法の場合

```
public class Main {
    public static void main(String[] args) {
        int[] seq = new int[10];

        // 塩基配列をランダムに生成
        for (int i = 0; i < 10; i++) {
            seq[i] = new java.util.Random().nextInt(4);
        }

        // 生成した塩基配列の記号を表示
        for (int i = 0; i < 10; i++) {
            switch (seq[i]){
                case 0 -> {
                    System.out.print("A ");
                }
                case 1 -> {
                    System.out.print("T ");
                }
                case 2 -> {
                    System.out.print("G ");
                }
                case 3 -> {
                    System.out.print("C ");
                }
            }
        }
    }
}
```
しかし、この書き方は冗長である。

そこで、生成した塩基配列の記号を表示する部分を以下のように書き換える。
```
char[] base = {'A', 'T', 'G', 'C'};
System.out.print(base[seq[i]] + " ");
```

書き直した2行目の処理は、分解すると3つの処理に分けることができる。

```
int baseType = seq[i];  // i番目の数値を取得する
char baseChar = base[baseType]; // 数値に対応する記号を取得する
System.out.print(baseChar + " "); // 記号を画面に出力する
```

#### 4-4. 拡張for文

全ての要素を最初から最後まで順番に回すとき、   
通常のループの回し方の他に以下の方法もある。
```
for (要素の型 任意の変数名 ： 配列変数名){
  // ループ処理の内容
}
```

実際に以下のサンプルコードで比較してみる。

従来のfor文の場合
(chapter04/code04-05/src/Main.java)
```
public class Main {
    public static void main(String[] args) {
        int[] scores = {20, 30, 40, 50, 80};
        for(int i = 0; i < scores.length; i++){
            System.out.println(scores[i]);
        }
    }
}
```

拡張for文の場合
```
public class Main {
    public static void main(String[] args) {
        int[] scores = {20, 30, 40, 50, 80};
        for(int score : scores){
            System.out.println(score);
        }
    }
}
```

拡張for文の場合はループ変数やインデックスを記述する必要がなくなるので、  
バグ混入の可能性を低く抑え、スッキリとしたコードを書くことができる。


### 5. 配列の舞台裏

以下のサンプルコードを実行すると面白い結果になる。  
(chapter04/code04-10/src/Main.java)
```
public class Main {
    public static void main(String[] args) {
        int[] arrayA = {1, 2, 3};
        int[] arrayB;
        arrayB = arrayA;
        arrayB[0] = 100;
        System.out.println(arrayA[0]);
    }
}
```

実行結果
```
100
```

実行結果は1にならなかった。   
なぜなのか、以下の観点から紐解いていく。

#### 5-1. メモリと変数

コンピュータは使用するデータをメモリ上に記憶する。  
メモリは以下の図のように区画整理されており、  
各区画にはアドレスが振られている。
変数を宣言すると、空いている任意の区画を確保するためにメモリを確保する。  
そして変数に値を代入すると、確保しておいた区画に値が記憶される。

例えばint型の変数を宣言して代入した場合、以下の図のようになる。

[![Image from Gyazo](https://i.gyazo.com/0c8804611666393d75d104bee4f0976a.png)](https://gyazo.com/0c8804611666393d75d104bee4f0976a)

int型は4バイトなので、変数宣言時にメモリ上の4つの区画を確保し、  
値を代入する時に区画がメモリに記憶される。

#### 5-2. メモリと配列

配列変数の宣言によりint[]型の変数が、new演算子によって配列の実体（要素の集まり）が、  
それぞれメモリ上の区画に作成される。
配列変数には、5つの変数まるごとではなく、**「最初の要素のアドレス」** が代入される。

[![Image from Gyazo](https://i.gyazo.com/a34d535cfc4d7210c3bfa29316325711.png)](https://gyazo.com/a34d535cfc4d7210c3bfa29316325711)



int[] numbers = new int[5];実行した場合

1. int型の要素を5つ持つ配列がメモリ上に作られる
2. int[]型の配列変数numbersがメモリ上に作成される
3. 配列変数numbersに配列の先頭アドレスが代入されている。

配列変数numbersに格納されているのは値そのものではない。  
参照先の番地である。

プログラムからnumbers[n]と指定された場合、

1. numbersから番地(上記の場合は8832)を取り出し、配列の先頭要素を見つける。
2. 見つけた配列の先頭要素からn個後ろの要素の区画を読み書きする。

この配列変数numbersが「配列の実体は8832番地にあります」と指し示す動作のことを参照という。


#### 5-3. 配列を複数の変数で参照する

chapter04/code04-10/src/Main.java
```
public class Main {
    public static void main(String[] args) {
        int[] arrayA = {1, 2, 3};
        int[] arrayB;
        arrayB = arrayA;
        arrayB[0] = 100;
        System.out.println(arrayA[0]);
    }
}
```

なぜこのプログラムの出力結果が「100」になったのか？

5行目で代入されているのはarrayAに入っている先頭番地である。  
arrayAに8832番地が入っているならば、arrayBに代入されるのは8832番地である。  
よって、arrayAとarrayBは**同じ配列を参照している**ことになる。

この状態でarrayB[0] = 100;とするのは、arrayA[0] = 100;とするのと同じである。  
したがって、このプログラムの出力が「100」となる。

[![Image from Gyazo](https://i.gyazo.com/3e2674edbbaea17840df4bd3ee203fe5.png)](https://gyazo.com/3e2674edbbaea17840df4bd3ee203fe5)