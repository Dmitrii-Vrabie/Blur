package hfad.com.cribforpascal.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mephisto on 3/4/2017.
 */

public class CribUtil {
    public static void DataForCrib(SQLiteDatabase db) {
        if (db == null) {
            return;
        }
        List<ContentValues> list = new ArrayList<ContentValues>();

        /*IF...THEN...ELSE*/
        ContentValues cv = new ContentValues();
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT, "IF...THEN...ELSE");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_DESCRIPTION, "Условный оператор позволяет изменять порядок выполнения операторов" +
                " в зависимости от некоторого условия, представляющего собой логическое выражение типа Boolean. Если значение равно" +
                " True, то выполняется одна группа операторов, если False, то другая группа операторов или не выполняется ничего.");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STEP_BY_STEP, "If  then begin\n" +
                "\t[операторы 1;]\n" +
                "end\n//" +
                "Else if уссловие 2 then begin\n//" +
                "\t[операторы2;]\n" +
                "End\n//" +
                "   .\n" +
                "   .\n" +
                "   .\n" +
                "else if условиеN\n//" +
                "\t[операторыN;]\n" +
                "end\n//" +
                "else begin\n" +
                "   .\n" +
                "   .\n" +
                "   .\n" +
                "end;");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_EXERICISE, "program p_IF_THEN_ELSE;\n" +
                "var a, b, c: integer;\n" +
                "\n" +
                "begin\n" +
                "\ta = 10;\n" +
                "\tb = 11;\n" +
                "\tc = 6;\n" +
                "\tif (a < b+c) and (b < a+c) and (c < a+b) then\n" +
                "\t\twriteln ('Можно построить треугольник.')\n" +
                "\telse\n" +
                "\t\twriteln ('Нельзя построить треугольник.');\n" +
                "end." +
                "Что выведет на экран программа?");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_ANSWER, "Можно построить треугольник");
        list.add(cv);

        /*REPEAT*/
        cv = new ContentValues();
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT, "REPEAT {...}");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_DESCRIPTION, "Оператор REPEAT работает следующим образом: сначала выполняются операторы тела цикла, после чего результат проверяется логического условия. Если результат ложь, то осуществляется возврат к выполнению операторов очередного тела цикла. Если результат истина, то оператор завершает работу.\n" +
                "\n" +
                "Оператор Repeat имеет следующие особенности:\n" +
                "\n" +
                "в Repeat проверяется условие завершения цикла и если условие выполняется, то цикл прекращает работу;\n" +
                "тело цикла всегда выполняется хотя бы один раз;\n" +
                "параметр для проверки условия изменяется в теле цикла;\n" +
                "операторы тела цикла не надо заключать в операторские скобки (BEGIN-END)," +
                " при этом роль операторных скобок выполняют Repeat и Until.");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STEP_BY_STEP, "repeat {повторяй}\n" +
                "  {операторы операторы}\n" +
                "until <условие>; {до тех пор, пока условие не будет истинным}");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_EXERICISE,
                "Program p_FOR_ex\n" +
                        "var\n" +
                        "  i, N: integer;\n" +
                        " \n" +
                        "begin\n" +
                        "  i := 1;\n" +
                        "  N := 10;\n" +
                        "  repeat \n" +
                        "    write(i, ' '); \n" +
                        "    Inc(i);\n" +
                        "  until i = N + 1; \n" +
                        "end.\n" +
                        "\n" +
                        "При каком значении i цикл прекратится?");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_ANSWER, "11");
        list.add(cv);

        /*FOR*/
        cv = new ContentValues();
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT, "FOR {...}");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_DESCRIPTION, "For — это  цикл, в котором тело выполняется заданное количество раз.\n" +
                "\n" +
                "Существует две формы записи этого цикла:\n" +
                "\n" +
                "Первая форма\n" +
                "\n" +
                "for <счетчик1> := <значение1> to <конечное_значение> do <оператор1>;\n" +
                "После каждой итерации значение <счетчик1> будет увеличиваться на 1.\n" +
                "\n" +
                "<значение1> — это начальное значение счетчика. Это может быть переменная или число.\n" +
                "<конечное_значение> : как только значение <счетчик1> станет больше <конечное_значение>, выполнение цикла прекратится.\n" +
                "\n" +
                "Если требуется написать несколько операторов в теле цикла, используем begin и end.\n" +
                "\n" +
                "И <счетчик1>, и <конечное_значение>, и <значение1> —  переменные целого типа.\n" +
                "\n" +
                "Чаще всего в качестве счетчика используется переменная i.");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STEP_BY_STEP, "program example_for;\n" +
                " \n" +
                "var\n" +
                "  i, N: integer;\n" +
                " \n" +
                "begin\n" +
                "  read(N); {предположим, что мы ввели 10}\n" +
                "  for i := 1 to N do write(i, ' '); {количество итераций - 10 - 1 + 1 = 10}\n" +
                "end.");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_EXERICISE, "program example_for;" +
                " \n" +
                "var\n" +
                "  K, N, i: integer;\n" +
                " \n" +
                "begin\n" +
                "  K:= 5;\n" +
                "  N:=3;\n" +
                "  for i := 1 to N do write(K, ' ');\n" +
                "end." +
                "Что выведет на экран программа?");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_ANSWER, "5 5 5 ");
        list.add(cv);

        /*WHILE*/
        cv = new ContentValues();
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT, "WHILE {...}");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_DESCRIPTION, "Оператор цикла WHILE содержит в себе выражение, которое управляет" +
                " повторным выполнением оператора (который может быть составным оператором). Выражение, с помощью которого производится управление" +
                " повторением оператора, должно иметь булевский тип. Вычисление его производится до того, как внутренний оператор будет выполнен." +
                " Внутренний оператор выполняется повторно до тех пор, пока выражение принимает значение True." +
                " Если выражение с самого начала принимает значение False, то оператор, содержащийся внутри оператора" +
                " цикла while, не выполняется ни разу.");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STEP_BY_STEP, "program example_while; \n" +
                " \n" +
                "var\n" +
                "  i, N: integer; { объявляем переменные }\n" +
                " \n//" +
                "begin\n" +
                "  i := 1; { Присваиваем i значение 1 }\n//" +
                "  readln(N); { Считываем последнее число }\n//" +
                "  while i <= N do {Как только i станет больше N, цикл прекратится (можно было бы написать просто <, но пришлось бы добавлять 1 к N) }\n//" +
                "  begin {Открываем операторные скобки}\n//" +
                "    write(i, ' '); {Выводим i}\n//" +
                "    Inc(i);  {увеличиваем i на один.}\n//" +
                "  end; { закрываем скобки }\n" +
                "end.");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_EXERICISE, "program p_WHILE_ex;\n" +
                "var\n" +
                "  b, sum: integer;\n" +
                "\n" +
                "begin\n" +
                "  b := 1;\n" +
                "  sum := 0; // начальная сумма\n" +
                "  while b <= 3 do \n" +
                "  begin\n" +
                "    sum := sum + b*b*b;\n" +
                "    inc(b)\n" +
                "  end;\n" +
                "  writeln('sum = ', sum); // выводим результат\n" +
                "  readln\n" +
                "end." +
                "Что выведет на экран программа?");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_ANSWER, "36");

        /*CASE*/
        list.add(cv);
        cv = new ContentValues();
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STATEMENT, "CASE(...){...}");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_DESCRIPTION, "Для ситуаций, где имеется несколько (три и более) альтернатив," +
                " больше подходит оператор case. Этот оператор называется оператором выбора. Оператор case состоит из выражения (селектора)" +
                " и списка операторов, каждому из которых предшествует одна или более констант (они называются константами выбора) или" +
                " ключевое слово else. Селектор должен иметь порядковый тип размера байт или слово. Таким образом, строковый тип и тип" +
                " LongInt являются недопустимыми типами селектора. Все константы выбора должны быть уникальными и иметь порядковый тип," +
                " совместимый с типом селектора.");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_STEP_BY_STEP, "\n" +
                "case Выражение of\n//" +
                "значение1 : оператор (группа операторов);\n" +
                "значение2 : оператор (группа операторов);\n//" +
                ". . . . . . . . . . . . . . . . . . . . . .\n" +
                "значениеN : оператор (группа операторов)\n//" +
                "else оператор (группа операторов);\n" +
                "end;");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_EXERICISE, "Program p_CASE_ex;\n" +
                "Var\n" +
                "  a : integer; \n" +
                "Begin\n" +
                "  a := 6;\n" +
                "  case a of\n" +
                "    0 : writeln (‘шесть‘);\n" +
                "    1 : writeln (‘один‘);\n" +
                "    2 : writeln (‘два‘);\n" +
                "    3 : writeln (‘три‘);\n" +
                "    4 : writeln (‘восемь‘);\n" +
                "    5 : writeln (‘семь‘);\n" +
                "    6 : writeln (‘ноль‘);\n" +
                "    7 : writeln (‘пять‘);\n" +
                "    8 : writeln (‘четыре‘);\n" +
                "    9 : writeln (‘девять‘)\n" +
                "  else writeln (‘Это число не является цифрой‘);\n" +
                "  end;\n" +
                "  readln;\n" +
                "End." +
                "Что выведет на экран программа?");
        cv.put(CribForPascalContract.CribForPascalEntry.COLUMN_ANSWER, "ноль");
        list.add(cv);

        try {
            db.beginTransaction();
            db.delete(CribForPascalContract.CribForPascalEntry.TABLE_NAME, null, null);
            for (ContentValues c : list) {
                db.insert(CribForPascalContract.CribForPascalEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            db.endTransaction();
        }
    }


}
