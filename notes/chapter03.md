# 第3章

学習日: 2026-06-26 〜　2026-06-30

## 学んだこと

- 制御構造の種類
- ブロックの書き方
- 条件式
- if-elseの条件分岐
- その他の条件分岐
  - if-else if-elseの条件分岐
  - switch文
  - 三項演算子
- 繰り返し（ループ）
  - while文 ＆ do-while文
  - for文
  - 複雑なfor文
- 制御構造の応用
  - 制御構造のネスト
  - 繰り返しの中断
  - 無限ループ

## 詰まったこと
複雑なfor文

## 用語集 & 補足

### 1. 用語

- 制御構文：条件分岐や繰り返しのような制御構造を表す文のこと
- 条件式：条件分岐やループ内で判断基準となる式のこと
  - if 文や while 文などで使用される
- ブロック：複数の文をひとまとまりにして扱うためのもの、{・・・}で囲まれた部分
- スコープ：変数が利用可能な範囲


### 2. 制御構造の種類

1. 順次：単純に次の文を実行する
2. 条件分岐：条件によって違う文を実行する
3. 繰り返し（ループ）：条件が満たされるまで同じ文を繰り返す

#### 条件分岐

ここでは if 文を紹介

```
if(条件式){
    // 条件成立時に実行する処理
} else {
    // 条件不成立時に実行する処理
}
```

#### 繰り返し処理

ここでは while 文を紹介

```
while (条件式) {
    // 繰り返し実行する処理
}
```

#### 制御構文の構成要素

- 条件式：条件分岐や繰り返しの条件を示した式
- ブロック：条件分岐や繰り返しで実行する一連の分の集まり

### 3. ブロックの書き方

#### ルール1. {}の省略

内容が1文しかない場合は{}を省略可能

```
public class Main {
    public static void main (String[] args) {
        boolean tenki = true;
        if (tenki == true) {    // 内容が2行なので省略不可能
            System.out.println("洗濯をします");
            System.out.println("散歩に行きます");
        } else  // 内容が1行なので省略可能
            System.out.println("映画を観ます");
    }
}
```

ただし、実際の現場では、プログラミングのミスの防止のため、ブロックの波カッコの省略は推奨されない。

#### ルール2. ブロック内で宣言した変数の寿命

ブロック内で宣言した変数は、そのブロックが終わると変数は消滅する。

以下例
```
int a = 10;
while (条件式) {
    int b;
}
```
変数aは while の外でも有効であるが、  
変数bは while 文のブロックでのみ有効となる。  
while 文の外で変数bを使用するとコンパイルエラーになる。

### 4. 条件式

#### 4-1. 関係演算子

| 演算子  | 意味          |
|------|-------------|
| `==` | 左辺と右辺が等しい   |
| `!=` | 左辺と右辺が異なる   |
| `>`  | 左辺が右辺よりも大きい |
| `<`  | 左辺が右辺よりも小さい |
| `>=` | 左辺が右辺以上     |
| `<=` | 左辺が右辺以下     |

条件式の例

- sw != false：変数swがfalseでなかったら
- deg - 273.15 < 0：変数degから273.15を引いた数値が0未満なら
- initial == '雅'：変数initialに入っている文字が「雅」だったら

注意点：  
条件式で等しいを意味する関係演算子は==(イコール2つ)である。  
間違っても=(イコール1つ)にしないこと！

#### 4-2. 条件式の評価

関係演算子を使ったif文やwhile文の条件式も評価されて化ける。  
条件式の場合には、結果はtrueまたはfalseのどちらかになる。

例 ：age = 40の場合、  
age >= 18 → true

これを if文に当てはめると・・・  
if (age >= 18){ → if (true) {

if文の場合、条件式の評価結果がtrueなら第1ブロックを、falseなら第2ブロックを実行する。  
while文の場合、条件式の評価結果がtrueである間はブロック内の処理を繰り返し実行する。

#### 4-3. 文字列の比較

比較対象が文字列の場合は、Stringが参照型のため、==を使わず、equals()を使用する。

```
文字列変数.equals(比較相手の文字列)
```
比較相手の文字列には文字列リテラルを利用できる。  
文字列変数と比較相手の文字列が等しい内容であれば、この条件式全体はtrueに化ける。

#### 4-4. その他の演算子を使った条件式の比較

| 演算子    | 意味                                 |
|--------|------------------------------------|
| `&&`   | かつ（左辺と右辺の両方の条件が満たされた場合にtrueになる）    |
| `\|\|` | または（左辺と右辺のいずれかの条件が満たされた場合にtrueになる） |
| `!`    | 条件式の反転（条件式がtrueの場合falseになる）        |

論理演算子と否定を使った例
```
if (age >= 18 && month == 5){・・・
if (name.equals("斉藤") || name.equals("斎藤"))
if (!(age >= 18)){・・・
```

&&の例：  
age >= 18 && month == 5 → true && false → false

||の例：  
name.equals("斉藤") || name.equals("斎藤") → true || false → true

!の例：
!(age >= 18) → !(true) → false

#### 4-5. 条件式の短縮評価

Javaは条件式を評価する際、少し賢い評価をする。  
上記で age=1 の場合、&& だと前半部分（age >= 18）を評価した時点で、  
条件式全体の結果はfalseになることが確定する。  
このため、後半部分（month == 5）については無視して評価されない。  
このようなふるまいのことを短縮評価と呼ぶ。

### 5. その他の条件分岐

条件分岐の種類は以下の通り

1. if-else文（基本形）
2. if 文のみ
3. if-else if-else文
4. switch文

このうち3,4について以下で取り扱う。

#### if-else if-else文

```
if (条件式1){
  // ブロック1
} else if (条件式2) {
  // ブロック2
} else if (条件式3){
・
・
・
} else {
  // 最終ブロック
}
```
if-else if-else文は3つ以上のルートに条件分岐をさせることができる。  
条件式1がfalseのときは条件式2を評価して、trueなら実行し、falseなら条件式3を評価する。  
これをtrueになるまで続けて、全てfalseだった時は最終ブロックの処理を実行することになる。

また、最後のelseブロックは、中身が空の場合は省略が可能。

#### switch文

else ifをたくさん使うと冗長になる。  
以下の条件を満たす場合はswitch文が使える。

1. 全ての条件式が==で、左辺と右辺が一致するか比較する式になっており、それ以外の<、>、!=などが使われていない。
2. 比較する対象が整数型(byte、short、intのいずれか)、文字列型(String)、文字(char)であり、小数や真偽値ではない。

よく見かける switch 文の例
```
public class Main{
  public static void main(String[] args){
    System.out.println("あなたの運勢を占います");
    int fortune = new java.util.Random().nextInt(5) + 1;
    switch (fortune) {
      case 1, 2:
        System.out.println("いいね！");   // fortuneの値が1または2の時に実行
        break;  // ここでswitch文から脱出する
      case 3:
        System.out.println("普通です");   // fortuneの値が3の時に実行
        break;
      default:
        System.out.println("う〜ん...");  // fortuneの値が上記以外の時に実行
    }
  }
}
``` 
このswitch文はbreak;を記述する必要がある。  
break;を書き忘れると、コンパイルエラーにはならず、  
すぐ下の別のcase文が実行されてしまい、不具合の原因になる。

->と{}を使う書き方の例
```
public class Main{
  public static void main(String[] args){
    System.out.println("あなたの運勢を占います");
    int fortune = new java util.Random().nextInt(5) + 1;
    switch (fortune) {
      case 1, 2 -> {
        System.out.println("いいね！");
      }
      case 3 -> {
        System.out.println("普通です");
      }
      default -> {
        System.out.println("う〜ん...");
      }
    }
  }
}
```
この場合はbreakを書く必要がない。

また、switch文は以下のように式としても利用できる。
```
String s = switch (fortune){
  case 1 -> "大吉";
  case 2 -> "中吉";
  case 3 -> "吉";
  default -> "凶";
};
System.out.println("運勢は" + s);
```

#### 三項演算子

分岐が2つの場合は三項演算子というものが使える。

```
String s = age >= 18 ? "成人" : "未成年";
```
age >= 18 ?は条件式、  
"成人"の部分は第1ブロック、  
"未成年"の部分は第2ブロックである。  

したかって、三項演算子は元々は以下の書き換えである。
```
String s;
if (age >= 18) {
  s = "成人";
} else {
  s = "未成年";
}
```

### 6. 繰り返し（ループ）

繰り返しには以下の種類がある。

1. while文
2. do-while文
3. for文
4. 拡張for文（配列をで取り扱う）

#### 6-1. while文とdo-while文

while 文のサンプルコード
```
public class Main{
  public static void main(String[] args){
    int temp = 30;
    while (temp > 25){
      temp--;
      System.out.println("温度を1度下げました。")
    }
  }
}
```

do-while文のサンプルコード
```
public class Main{
  public static void main(String[] args){
    int temp = 30;
    do {
      temp--;
      System.out.println("温度を1度下げました。")
    } while (temp > 25);
  }
}
```

while文は、条件式がtrueの場合のみ実行される。  
もし最初の条件式がfalseだった場合は、while文のブロックの処理は行われない。  
したがって、while文のブロックの実行回数は0回以上である。

一方do-while文は条件式が最後に存在する。  
一度は処理を実行して、その後に条件式で繰り返し判定を行う。  
よって、do-while文のブロックは最低1回以上は実行される。

#### 6-2. for文

for文は繰り返しを行う回数が決まっている場合に使用される。

for文の代表的なサンプルコード
```
public class Main{
  public static void main(String[] args){
    for(int i = 0; i < 10; i++){
      System.out.println("こんにちは");
    }
  }
}
```
条件式について：

1. int i = 0; → 初期化処理  
最初の1回だけ実行される。  
この時に使用される変数iをループ変数という。  


2. i < 10; → 繰り返し条件  
この条件がtrueの間は繰り返し処理が実行される。  
for文はwhile文の前置判定と同じであり、  
do-while文のような後置判定はできない。


3. i++; → 繰り返し時の処理
for文のブロックの最後まで処理をして、  
ブロックを閉じる{}まで到達した直後に自動的に実行される文。  


ループ変数について：

1. ループ変数の名前は自由  
ただしfor文より前に既に宣言されている変数名は使えない。

  
2. for文のブロック内でのみ利用可能  
ループ変数も通常の変数なので、ブロック内での計算や表示に使える。


3. ブロック外では利用不可能  
if文でブロックの外で変数が利用できないのと同じように、  
for文でもブロックの外ではループ変数を利用できない。  
for文を抜けるとループ変数は消失する。


#### 6-3. 複雑なfor文

```
// 1. ループ変数iを1からスタートする
for (int i = 1; i < 10; i++){・・・}

// 2. ループ変数iを2ずつ増加させる
for (int i = 0; i < 10; i += 2){・・・}

// 3. ループ変数が10から1ずつ1まで減っていく
for (int i = 10; i > 0; i--){・・・}

// 4. ループ変数を初期化しない
for (; i < 10; i++){・・・}

// 5. 繰り返し時の処理（インクリメントを行わない）
for (int i = 0; i < 10;){・・・}
```
1は純粋にループ変数が0ではなく1からスタートする。

2は0,2,4,6,8の時に繰り返し処理でiに2を加算している。  
この形式はそのもの見かけないが、  
通常のfor文の冒頭でif(i % 2 == 1) continue;をするのと変わらない。

3はカウントダウン形式、たまに見かける。

4のループを初期化しないは場合による。  
ループ処理以前にint i = 5;みたいに初期化していた場合はこのループは実行される。  
しかし、ループ変数の宣言がなかった場合はコンパイルエラーになる。

5はこのままだと無限ループになる。  
そのため、ブロック内でループ変数iを変化させる記述が必要になる。  
(例：i++;、i += 2;など)

このような複雑なfor文は、バグの原因になりやすいので極力使用を避ける。

### 7. 制御構造の応用

#### 7-1. 制御構造のネスト

プログラムを書いていると、時々「分岐の中に分岐」「繰り返しの中に分岐」のような構造が存在する。  
このような構造を入れ子またはネストという。

以下はネストされたfor文を利用して九九の表を出力するプログラム
```
public class Main{
  public static void main(String[] args){
    for (int i = 1; i < 10; i++){
      for(int j = 1; j < 10; j++){
        System.out.print(i * j);
        System.out.print(" ");
      }
      System.out.println("");
    }
  } 
}
```

#### 7-2. 繰り返しの中断

繰り返しを即座に中断して、for文から脱出したい場合はbreak、  
同じ繰り返しの次の周回に進む場合はcontinueを使用する。

breakの場合
```
public class Main{
  public static void main(String[] args){
    for(int i = 0; i < 10; i++){
      if(i == 3){
        break;
      }
      System.out.println(i);
    }
  }
}
```
出力結果
```
0
1
2
```
continueの場合
```
public class Main{
  public static void main(String[] args){
    for(int i = 0; i < 10; i++){
      if(i == 3){
        continue;
      }
      System.out.println(i);
    }
  }
}
```
出力結果
```
0
1
2
4
5
6
7
8
9
```

#### 7-3. 無限ループ

無限ループは永久に繰り返し続ける制御構造である。  
プログラミングに慣れていない間は、for文やwhile文を間違えて、  
無限ループを意図せずに作ってしまっているケースが多発しているため注意！

逆に意図的に無限ループを作成する方法は2通りある。
1. while(true){ループ処理}
2. for(;;){ループ処理}


## まとめ

- 制御構文
  - 文の実行順序は、順次、分岐、繰り返しの3つの制御構造を組み合わせてコントロールできる。
  - 分岐と繰り返しは「条件式」「ブロック」から構成されている。
  - 条件式の評価結果はtrueまたはfalseでなければならない。
  - 文字列を比較するときは「==」ではなく、「equals」を使用する。
  - ブロック内で定義した変数はブロック終了と共に消滅する。
  - 制御構文はネストできる。

- 分岐
  - if文またはswitch文を使用して分岐を実現する。
  - if文には「if-else構文」「ifのみ構文」「if-else if-else構文」の3つが存在する。
  - switch文のブロックはbreak文で抜けることができる。

- 繰り返し
  - while文、do-while文またはfor文を使用して繰り返しを実現できる。
  - while文のブロックは最低0回以上、do-while文のブロックは最低1回以上実行される。
  - for文はループ変数を使用して繰り返し回数を指定できる。
  - break文を実行すると繰り返し自体を中断し、continueを実行すると繰り返しの次の周回へ進むことができる。
  - 永久に繰り返し続ける制御構造を無限ループという。

## 練習問題

### 練習3-1

1. weight == 60
2. (age1 + age2) * 2  >60
3. age % 2 == 1
4. age.equals("湊")

### 練習3-2

A. ×：これは代入のための式であり、条件式ではない。
B. ×：整数型であるため、条件式にはならない。
C. ◯：ageが30でないならtrue、それ以外ならfalse。
D. ◯：条件分岐や繰り返しの処理のブロック内の処理は条件式がtrueである間は行われる。
E. ◯：b+5が20未満ならtrue、それ以外ならfalse。
F. ×：Aと同様代入のための式であるため、条件式ではない。

よって条件式として適切なのはC、D、E

### 練習3-3

chapter03/practice03-03/src/Main.java
```
public class Main {
    public static void main(String[] args) {
        int isHungry = 0;
        String food = "チャーハン";
        System.out.println("こんにちは");
        if (isHungry == 0) {
            System.out.println("お腹がいっぱいです");
        } else {
            System.out.println("はらぺこです");
        }
        if (isHungry == 1) {
            System.out.println(food + "をいただきます");
        }
        System.out.println("ごちそうさまでした");
    }
}
```

### 練習3-4

```
public class Main {
    public static void main(String[] args) {
        boolean tenki = false;
        if (tenki) {
            System.out.println("洗濯をします");
            System.out.println("散歩をします");
        } else
            System.out.println("映画を観ます");
            System.out.println("寝ます");  // この状態ではtrue,false関係なく表示される
    }
}
```

- 原因：7行目のelseの後に{}がなく、else後のブロックがない。  
- 結果：true、falseに関係なく「寝ます」が出力される。
- 対処法：以下のようにelseの後に{}を追加し、追加したブロック内にSystem.out.println("映画を観ます");とSystem.out.println("寝ます");を記述する。

chapter03/practice03-04/src/Main.java
```
public class Main {
    public static void main(String[] args) {
        boolean tenki = false;
        if (tenki) {
            System.out.println("洗濯をします");
            System.out.println("散歩をします");
        } else {
            System.out.println("映画を観ます");
            System.out.println("寝ます");
        }
    }
}
```

### 練習3-5

chapter03/practice03-05/src/Main.java
```
public class Main {
    public static void main(String[] args) {
        System.out.print("[メニュー]1：検索 2：登録 3：削除 4：変更＞");
        int selected = new java.util.Scanner(System.in).nextInt();
        switch (selected) {
            case 1:
                System.out.println("検索します");
                break;
            case 2:
                System.out.println("登録します");
                break;
            case 3:
                System.out.println("削除します");
                break;
            case 4:
                System.out.println("変更します");
                break;
        }
    }
}
```

### 練習3-6

chapter03/practice03-06/src/Main.java
```
public class Main {
    public static void main(String[] args) {
        System.out.println("【数あてゲーム】");
        int ans = new java.util.Random().nextInt(10);
        for (int i = 0; i < 5; i++){
            System.out.println("0〜9入力してください");
            int num = new java.util.Scanner(System.in).nextInt();
            if (num == ans){
                System.out.println("アタリ！");
                break;
            } else {
                System.out.println("違います");
            }
        }
        System.out.println("ゲームを終了します");
    }
}
```