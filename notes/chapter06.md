# 第6章

学習日: 2026-07-24 〜 （現在学習中）

## 学んだこと

- ソースファイルを分割する
  - 1つのソースファイルによる開発の限界
  - 計算機プログラムを分割しよう
- 複数のクラス構成されるプログラム
  - 複数クラスのコンパイル
  - Javaプログラムの完成品
  - プログラムの実行方法
- パッケージを利用する
  - クラスが増えすぎたらどうする？
  - パッケージを含むクラス名を指定する
  - 完全指定クラス名の入力を省略する
- パッケージに属したクラスの実行方法
  - クラス名だけでクラスファイルを探し出す仕組み
  - クラスパスの指定方法
  - クラスパスで指定できる対象
  - クラスファイルの正しい配置
- 名前空間
  - パッケージを使うもう1つのメリット
  - パッケージ名の衝突を避ける方法
- Java APIについて学ぶ
  - 世界中の人々の協力で完成していたHelloWorld
  - APIで提供されるパッケージ
  - APIリファレンスの読み方

## 詰まったこと

クラスローダーやクラスパスとは何かを説明すること

## 用語集 & 補足

### 1. 用語

- JARファイル:JARはJava ARchiveの略。Javaにおける、複数のクラスファイルを1つにまとめるファイル形式。
  - ZIPファイルによく似たアーカイブファイル。
- パッケージ：クラスファイルをグループに所属させて、分類・管理できる仕組み。
  - デフォルトパッケージ：package文がなく、どのパッケージにも所属していない状態。無名パッケージとも呼ばれる。
- 完全限定クラス名(FQCN)：Full qualified class nameの略。
  - パッケージ名を頭につけた完全なクラス名のこと。
  - 完全就職クラス名とも呼ばれる。
- import文:FQCNの入力の手間を軽減するための宣言文
- クラスローダー：JVMがプログラム実行時に必要なクラスファイルをメモリに読み込む仕組み。
- クラスパス：Javaプログラムを実行する際に、JVMがクラスファイルを参照するための場所を指定したもの。
  - クラスローダーがクラスファイルを探す際に見に行くべきフォルダの場所。
- 名前の衝突：内容が異なる別々のクラスで同じ名前を取り合うこと。
- 名前空間：使うことができる名前の総量。
- API：Application Programming Interfaceの略で、外部のソフトウェアが持つ機能を共有できる仕組み。
  - JavaにおけるAPIは、初めからJavaに存在するクラスをまとめた集まり。
- APIリファレンス：APIの説明書。JDK25では以下のリファレンスを参照。
  https://docs.oracle.com/javase/jp/25/docs/api/index.html

### 2. ソースファイルを分割する

#### 2-1. 1つのソースファイルによる開発の限界

chapter05では、長く複雑になってしまったmainメソッドを複数のメソッドに分割した。  
しかし、1つのソースファイルの中に含まれるメソッド数が増えると、  
ソースコードの全体を把握することは難しくなり、開発がしにくい。  

Javaでは、1つのソースファイルに全てのメソッドを書くのではなく、  
複数のソースファイルに分割して記述できる仕組みが存在する。  
複数のソースファイルに分けて開発するのは、  
複数のクラスに分けて開発するとも捉えることができる。

たくさんのメソッドを複数のソースファイルに分けて記述すると、  
整理されてわかりやすくなるだけでなく、  
ファイルごとに開発を分担し、それぞれが並行して開発を進められる、  
つまり分業しやすくなるというメリットもある。

#### 2-2. 計算機プログラムを分割する

まず以下のような計算機プログラムが存在する。

chapter06/code06-01/src/Calc.java
```
public class Calc {
    public static void main(String[] args) {
        int a = 10;
        int b = 2;
        int total = plus(a, b);
        int delta = minus(a, b);
        System.out.println("足すと" + total + "、引くと" + delta);
    }

    public static int plus(int a, int b) {
        return (a + b);
    }

    public static int minus(int a, int b) {
        return (a - b);
    }
}
```
ここで、plus()とminus()の2つは数学的な計算処理をするメソッドであり、  
main()はplus()とminus()を呼び出して画面に表示する役割を持つ、全体を司るメソッドである。  
よって、まずmain()とそれ以外のメソッドの2つのクラスに分割する。

#### STEP1 計算処理メソッドを記述するためのソースファイルを作成する

まず、plus()やminus()といった計算ロジックのメソッドを入れるソースファイルを作成する。  
新たなファイル名は、CalcLogic.javaにする。  
そして、CalcLogic.javaの書きはじめは`public class CalcLogic`にする。   
これは、ソースファイル名とクラス名を同じにする必要があるためである。

#### STEP2 plus()とminus()を移動する

現在Calc.javaの中にあるplus()とminus()を、  
新たに作成したCalcLogic.javaへ移動する。

chapter06/code06-02/CalcLogic.java
```
public class CalcLogic {
    public int plus(int a, int b) {
        return (a + b);
    }

    public int minus(int a, int b) {
        return (a - b);
    }
}
```
#### STEP3 メインメソッド内の呼び出しを修正する

上記までの状態の場合は、以下のようになる。

```
public class Calc {
    public static void main(String[] args) {
        int a = 10;
        int b = 2;
        int total = plus(a, b);
        int delta = minus(a, b);
        System.out.println("足すと" + total + "、引くと" + delta);
    }
}
```
5行目でplus()を、6行目でminus()を呼び出すが、  
このままではコンパイルエラーを引き起こす。
上記のCalc.javaの状態では、Calcクラス内にplus()、minus()が存在しないためである。

これまでは、main()の中で`plus(a, b)`と記述すればplus()が呼び出せていたのは、
**plus()やminus()がmain()と同じCalcクラスに属していたから**である。  
しかし、ソースファイルの分割によって、plus()やminus()はCalcLogicクラスに属するようになったため、
main()から呼び出すときには「CalcLogicのplus()とminus()」のように明示的に所属を示す必要がある。  
これはmain()の中で以下のように呼び出して対応する。

```
int total = CalcLogic.plus(a, b);
int delta = CalcLogic.minus(a, b);
```

これで無事に分割できるようになった。  
以下が修正したCalc.javaとなる。

chapter06/code06-02/src/Calc.java
```
public class Calc {
    public static void main(String[] args) {
        int a = 10;
        int b = 2;
        int total = CalcLogic.plus(a, b);
        int delta = CalcLogic.minus(a, b);
        System.out.println("足すと" + total + "、引くと" + delta);
    }
}
```