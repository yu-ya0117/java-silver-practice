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

- クラスローダーやクラスパスとは何かを説明すること
- パッケージを利用したコンパイル方法

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

### 3. 複数のクラスで構成されるプログラム

#### 3-1. 複数クラスのコンパイル

以下はJDKを使って開発する場合を前提とする。

分割したプログラムのコンパイル方法(code06-02の場合)

```
javac Calc.java CalcLogic.java
```

無事にコンパイルが終わると、
それぞれおのソースファイルに対応したクラスファイルが生成される。

#### 3-2. Javaプログラムの完成品

普段使っているPCのアプリケーションに慣れ親しんでいると、  
「Javaプログラムの完成品」のちょっと変わった姿を意外に思うかもしれない。  
その理由は、通常のアプリケーションは大抵1つだからである。

たとえばWindowsのメモ帳プログラムは、notepad.exeのような単体のファイルであって、  
これをこれをダブルクリックすれば起動する。

しかしJavaで開発されたプログラムは、  
「複数のクラスファイルの集まり」でありであることが多く、  
ダブルクリックで起動させるのではなく、  
javaコマンドで起動する。  
そのため、Javaプログラムを誰かに渡す、あるいは納品する場合には、  
**複数のクラスが入っているフォルダをまるごと「1つの完成品」として渡す**ことになる。

Javaプログラムの完成品をまとめると、  
- Javaプログラムの完成品は、複数のクラスファイルの集合体
- 誰かに配布する場合は、全てのクラスファイルを渡す必要がある。

### 3-3. プログラムの実行方法

クラスファイルがはいったファイルをまるごと受け取った場合は、  
クラス名を指定して実行する必要がある。

```
java クラス名
```

JVMは起動時に指定されたクラスの中にあるmainメソッドをを呼び出して、  
プログラムの実行を開始します。  
Javaプログラムを実行する人は、  
「渡された複数のクラスファイルのうち、mainメソッドが含まれているクラスの名前」を指定する必要がある。  
例えばcode06-02の計算機プログラムの場合、  
`java Calc`と入力すべきであり、  
`java CalcLogic`では正常に動作しない。

今回の計算機プログラムの場合、  
作成者がCalcの中にmainメソッドがあって、 
CalcLogicの中にはない事実を知っているので、  
`java Calc`で起動できると判断できた。

しかし他人が作ったJavaプログラムの場合は、  
mainメソッドが存在するクラスの名前がわからないと起動できないことに注意！

以上をまとめると、  
複数の完成したクラスファイルを渡す場合、  
全てのクラスファイルを渡すだけでなく、  
「mainメソッドが含まれるクラス名」も伝える必要がある。

プログラムの完成品が複数のクラスファイルになった場合、  
そのままメールで送る際などに不便が生じる。  
そこでJavaでは、「複数のクラスを1つに」まとめるファイル形式としてJAR(Java ARchive)が定められている。  
JARファイルはZIPファイルとよく似たアーカイブファイルで、  
JDKに付属するjarコマンドで作成することができる。

### 4. パッケージを利用する

#### 4-1. クラスが増えすぎたらどうする？

現段階ではまだイメージはつかないが、  
大規模開発になると、数百個ものクラスからなる1つのプログラムを開発することがある。
しかし、クラスの数が20個を超える規模になってくると管理も大変になってくる。

そこでJavaには、パッケージというグループに所属させて、  
分類・管理ができる仕組みが備わっている。

感覚としては、

mainの行数が増えたら複数メソッドに分割

↓

メソッド数が増えたら複数クラスに分割

↓

クラス数が増えたら複数パッケージに分割

というイメージである。

ここではcode06-02でも触れた計算機プログラムについて、  
パッケージを利用してみる。
クラスをパッケージに所属させるためには、  
そのクラスのソースコードの先頭にpackage文を記述する

```
package 所属させたいパッケージ名;
```

たとえば、計算機プログラムを2つのパッケージに所属させた場合は以下のように記述する

1. Calcをcalcapp.mainに所属させる
```
package calcapp.main;
public class Calc{
  // 処理内容
}
```
※ただし上記の状態ではコンパイルエラーが発生する。

2. CalcLogicをcalcapp.logicsに所属させる
```
package calcapp.logics;
public class CalcLogic{
  // 処理内容
}
```
※上記の状態ではコンパイルが通っていればOK。

パッケージの名前はJavaの識別子のルールに従っていれば自由に定めることはできるが、  
アルファベットは小文字にするのが一般的である。  
また、「calcapp.main」や「calcapp.logics」のように、  
ドットで区切ったパッケージ名も多く見られる。

なお、「calcapp.main」や「calcapp.logics」という2つのパッケージ名を見て、  
「共通のcalcappパッケージに所属するmainとlogicsという子パッケージで、同じグループである」という感覚を抱いてしまうかもしれないが、  
両者に相互にまったく関係はない、独立したパッケージである。  
パッケージの中にパッケージを入れることはできず、  
パッケージに親子関係や階層関係は存在しない。

[![Image from Gyazo](https://i.gyazo.com/5ae2bcf0f41f0a8a8b2fdb2920a7113d.png)](https://gyazo.com/5ae2bcf0f41f0a8a8b2fdb2920a7113d)

code06-01とcode06-02で作成してきたクラスにはpackage文が存在しなかった。  
どのパッケージにも所属していない状態を「無名パッケージに所属している」、  
あるいは「デフォルトパッケージに所属している」と表現する場合がある。  
このデフォルトパッケージに所属するクラスは、import文でインポートすることができない。

#### 4-2. パッケージを含むクラス名を指定する

ここまで2つのクラスを別のパッケージに所属させることができました。  
しかし、このままコンパイルするとCalc.javaの2つの行に構文エラーが発生する。

```
int total = CalcLogic.plus(a, b);
int delta = CalcLogic.minus(a, b);
```

Calcクラスにあるこの2行は、それぞれ「CalcLogic」クラスを利用しようとしている。  
しかし、この書き方では、「どのパッケージのCalcLogicクラスか」を明示していないため、  
Calcクラスは自分と同じパッケージ(calcapp.mainパッケージ)に所属するCalcLogicクラスを呼び出そうとして失敗する。

別のパッケージに所属しているクラスを利用するには、
所属パッケージ名を添えたクラス名を上記2行に指定する必要がある。

所属パッケージ名を添えたクラス名を上記2行に指定すると以下のようになる。

chapter06/code06-03/src/calcapp/main/Calc.java
```
package calcapp.main;

public class Calc {
    public static void main(String[] args) {
        int a = 10;
        int b = 2;
        int total = calcapp.logics.CalcLogic.plus(a, b);
        int delta = calcapp.logics.CalcLogic.minus(a, b);
        System.out.println("足すと" + total + "、引くと" + delta);
    }
}
```
※コンパイルは可能だが、実行方法は後述。

このように、あるクラスから別のパッケージを利用する場合、  
「パッケージ名を頭につけた完全なクラス名」を使う必要がある。  
この完全なクラス名のことを完全限定クラス名、  
または完全修飾クラス名(full qualified class name、略してFQCN)という。

完全限定クラス名(FQCN)は以下で表現する
```
パッケージ名.クラス名
```

ところで、どうやってコンパイルするの？

現在chapter06/code06-03/srcフォルダにいるとすると、コンパイル例は

```
javac -d ../bin calcapp/main/Calc.java calcapp/logics/CalcLogic.java
```
これでコンパイルすると、
```
chapter06/code06-03/bin/calcapp/main/Calc.class
chapter06/code06-03/bin/calcapp/logics/CalcLogic.class
```
この2つのクラスファイルが出来上がる。

#### 4-3. 完全指定クラス名の入力を省略する

今度はimport文を使う方法を説明する。

Calcクラスにおいて、以下の部分でFQCNを使用している。
```
int total = calcapp.logics.CalcLogic.plus(a, b);
int delta = calcapp.logics.CalcLogic.minus(a, b);
```
`calcapp.logics.CalcLogic`という長い完全限定クラス名(FQCN)を2か所使用している。  
現時点では2か所で済んでいるが、  
将来プログラムが大きくなったらこの長いFQCNを何度もコードの中に入力する必要が出てくる。
このような場合はimport文を使用してFQCNの入力の手間を軽減することができる。

FQCNの入力の手間を省くための宣言(import文の使用方法)
```
import パッケージ名.クラス名
```
※import文はソースコードの先頭に、ただしpackage文より後に記述する。

import文を記述すると、Calc.javaは以下のようになる。  
(chapter06/code06-04/src/calcapp/main/Calc.java)
```
package calcapp.main;
import calcapp.logics.CalcLogic;

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
※現段階ではコンパイルができていればOK。実行方法は後述。

ここで2行目のimport文に注目。  
この文は「このソースコードでCalcLogicという表記があったら、  
それはcalcapp.logics.CalcLogicのことだと解釈してください」という指示である。  
頻繁に利用するクラスはimoprt文でインポートしておくと、  
完全限定クラス名を毎回指定する必要がなくなる。

仮にcalcapp.logicsパッケージに所属する全てのクラスをインポートしたい場合は、  
以下のような記述も可能である。
```
package calcapp.main;
import calcapp.logics.*;

public class Calc {
  // 処理内容
}
```
ただし、`import calcapp.*;`とは記述できない。  
`calc.app.main`と`calc.app.logics`に所属する全てのクラスを一度にはインポートできない。  
なぜなら、`calc.app.main`と`calc.app.logics`、そして`calcapp`は全く異なるパッケージであり、  
親子関係にはないため。  
`import calcapp.*;`と記述した場合には、`calcapp`に所属する全てのクラスのみがインポートされる。  
`calc.app.main`と`calc.app.logics`に所属する全てのクラスをインポートする場合には、  
以下のように表現される。

```
import calcapp.main.*;
import calcapp.logics.*;
```

ここで気をつけたいのは、import宣言はあくまで「入力軽減機能」であること。  
Java以外の言語では、「include命令」(Cなど)や「require命令」(Rubyなど)で新しい機能を有効化する命令があるが、  
Javaのimport文は、これらとは全く違う働きをする。

Javaは特別な宣言をせずとも、JVMが扱える全てのクラスを最初から使うことができる。  
ただし、その利用には必ずFQCNを指定しなければいけない。  
**import文はあくまでもFQCNの記述を省略して手間を軽減するため**(開発者が楽をするため)の構文にすぎない。  
importしたからと言って利用できるクラスやメソッド、その他の機能が増えることはない。
