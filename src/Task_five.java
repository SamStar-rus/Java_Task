import java.math.BigInteger;
        import java.nio.charset.StandardCharsets;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
import java.util.Objects;


public class Task_five {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //для первого:
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(Arrays.toString(encrypt("Sunshine")));
        //Для второго:
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        //Для третьего:
        System.out.println(canComplete("butl", "bbeautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));
        //Для четвёртого:
        System.out.println(sumDigProd(new int[]{16, 28}));
        System.out.println(sumDigProd(new int[]{0}));
        System.out.println(sumDigProd(new int[]{1, 2, 3, 4, 5, 6}));
        //Для пятого:
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"})));
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"})));
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"})));
        //Для шестого:
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));
        //Для седьмого:
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        //Для восьмого:
        System.out.println(toHexString(getSHA("password123")));
        System.out.println(toHexString(getSHA("Fluffy@home")));
        System.out.println(toHexString(getSHA("Hey dude!")));
        //Для девятого:
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        //Для десятого:
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));


    }

    /*
    Создать две функции, одна кодирует, другая декодирует сообщение.
    Первый символ- символьный код этой буквы, след. элементы- это различия между символами.
    */
    /*
    encrypt- шифратор:
    Иищем ascii первого символа, потом считаем разницу и добавляем её в список
    decrypt- дешифратор:
    первый символ задает букву по ascii, потом прибавляется значение следующего символа,
    это значение переводится в char и добавляется в строку
     */
    public static Object[] encrypt(String string) {
        List<Integer> list_int = new ArrayList<Integer>(); //Создаём расширяемый список
        int dif = 0;
        for (int i = 0; i < string.length(); i++) {
            //добавляем в список и переводим в аски
            if (i == 0) list_int.add((int) string.charAt(i));
            else {
                //Переводим в аски текущ. элем. и предидущ. элем. вычит одно из другого
                dif = (int) string.charAt(i) - (int) string.charAt(i - 1);
                list_int.add(dif);//добавляем разницу в список
            }
        }
        return list_int.toArray();
    }

    public static String decrypt(int[] int_arr) {
        String ret_string = "";
        int dif = 0;
        for (int i = 0; i < int_arr.length; i++) {
            dif += int_arr[i];
                ret_string += (char) dif;//Декодирование (перевод из цифры в букву)
            }
        return ret_string;
    }

    /*Принимает имя шахмотной фигуры, её позицию и целевую позицию.
    Проверить может ли фигура двигаться к обозначенной цели
    */
    /*
    Пешка - pawn, Конь - knight, Слон - bishop, Ладья - rook , Ферзь - Queen, Король - King,
    Вычисляем на сколько фигура пошла по горизонтали и вертикали
    подставляем в ифы, вывыодим возможен ли такой ход
     */

    public static boolean canMove(String fig, String sc, String ec) {
        int dif_char = 0;
        int dif_int = 0;

        dif_char = (int) sc.charAt(0) - (int) ec.charAt(0); //сколько клеток пройдёт фигура по вертикали
        dif_int = sc.charAt(1) - ec.charAt(1); // колько фигура пройдёт клеток по горизонтали
        //Сравниваем две строки, игнорируя различия в нижнем и верхнем регистре
        if (fig.equalsIgnoreCase("pawn") && dif_char == 0 && dif_int == 1) return true;
        if (fig.equalsIgnoreCase("knight") && ((Math.abs(dif_char) == 2 && Math.abs(dif_int) == 1) ||
                ((Math.abs(dif_char) == 1 && Math.abs(dif_int) == 2)))) return true;
        if (fig.equalsIgnoreCase("bishop") && (Math.abs(dif_char) == Math.abs(dif_int))) return true;
        if (fig.equalsIgnoreCase("rook") && (dif_char == 0 || dif_int == 0)) return true;
        if (fig.equalsIgnoreCase("queen") && ((Math.abs(dif_char) == Math.abs(dif_int)) ||
                (dif_char == 0 || dif_int == 0))) return true;
        if (fig.equalsIgnoreCase("king") && (Math.abs(dif_char) <= 1) && (Math.abs(dif_int) <= 1)) return true;
        return false;
    }

    /*
    На входе две строки. Проверить является ли первая- частью второй, которую можно
    превратить во вторую добавлением букв, но не их удалением. Порядок букв в первой
    должен быть такой же как и во второй. Определить может ли быть слово завершено.
    */
    /*
     Перебор 2ух строк
     если символы совпадают, то символ записывается в новую строку, второе слово обрезается
     После всего сравнивается новое слово с первым словом
     */
    public static boolean canComplete(String sword, String eword) {
        String sub_sword = "";
        for (int i = 0; i < sword.length(); i++) {
            for (int n = 0; n < eword.length(); n++) {
                //сравниваем одинаковые ли буквы
                if (sword.charAt(i) == eword.charAt(n)) {
                    sub_sword += eword.charAt(n); //обавляем в созданный список эту букву
                    eword = eword.substring(n + 1);//Чтобы не было повторений, удаляем сравниваемую букву
                    break;
                }
            }
        }

        return sub_sword.equals(sword); //сравниваем одинаковые ли слова получились
    }
    /*
    Функция складывает входные числа, в получившимся числе цифры перемножаются до тех пор
    пока ответ не будет однозначным числом.
     */
    /*
    Сначала числа в списке перебираются и складываются
    потом переходит в метод перемножение multNum
    если число меньше 10, то есть состоит из одной цифры, то оно возращается,
    иначе перемножаются все числа и число на выходе снова подается
    на вспомогательный метод (multNum)
     */
    public static int sumDigProd(int[] int_list) { //Складываем
        int sum_num = 0;
        for (int i : int_list) {
            sum_num += i;
        }
        return multNum(sum_num);

    }

    //Перемножаем
    public static int multNum(int orig_num) {
        int multip_num = 1;
        if (orig_num < 10) return orig_num;//проверяем пока не стало меньше 10
        else {
            while (orig_num > 0) {
                multip_num *= orig_num % 10; //Умножаем на последнюю цифру числа
                orig_num = orig_num / 10; //удаляем последнюю цифру числа
            }
            return multNum(multip_num);
        }
    }
    /*
    Функция должна выбрать все слова имеющие те же гласные что и первое слово,
    колличество и порядо не важны. Первое слово включительно.
     */
    /*
    Сначала ищем все гласные в первом слове
    Потом выписываем их и сортируем
    Ищем все гласные в других словах, сортируем,
    сравниваем строки, если true, то добавляем в список возрата
     */
    public static String[] sameVowelGroup(String[] word_list) {
        ArrayList<String> ret_list = new ArrayList<String>();

        String orig_vowel = vowelsWord(word_list[0]);
        char[] orig_chars = orig_vowel.toCharArray(); // первод из строки в массив
        Arrays.sort(orig_chars); // сортируем
        orig_vowel = new String(orig_chars);


        for (String word : word_list) { //перебор остальных строк в списке
            String sec_vowel = vowelsWord(word);
            char[] sec_char = sec_vowel.toCharArray();
            Arrays.sort(sec_char);
            sec_vowel = new String(sec_char);
            if (orig_vowel.equals(sec_vowel)) ret_list.add(word);//если первая строка равна второй строке- добав. в список вывода
        }

        return ret_list.toArray(new String[0]); //выводится список
    }
// Определяем гласные в слове
    public static String vowelsWord(String word) { //начинаем отсюда
        String new_str = "";
        for (int i = 0; i < word.length(); i++) {
            if (("eyuioa".indexOf(word.charAt(i)) != -1) && (new_str.indexOf(word.charAt(i)) == -1)) {
                new_str += word.charAt(i);
            }
        }
        return new_str;
    }

    /*
    Проверяем является ли заданное число номером кредитной карты. Номер должен быть
    от 14 до 19 цифр и проити тест Луна. - Посленяя цифра удаляется, -число переворачивается,
    -Удваивается значение каждой цифры в нечётных позициях, усли удвоилось больше одной цифры-
    складываем их, -Прибавить все цифры, -Вычесть последнюю цифру суммы. Результат должен быть
    равен последней цифре исходного номера.
     */
    /*
    Вычисляем длину числа
    Если длина не входит в заданый промежуток, то возращаем false
    Задаем контрольную цифру (последнюю)
    с помощью вспомогательного метода переворачиваем число
    Умножаем каждый нечетный элемент на 2 и складываем 2 цифры числа, если оно больше 9
    Считаем Сумму вспех цифр, вычитаем из 10 последнюю цифру этой суммы и сравниваем с контрольной цифрой
     */
    public static boolean validateCard(Long num_card) {
        double num_of_dig = Math.ceil(Math.log10(num_card + 0.5)); //длина числа
        if (num_of_dig < 14 || num_of_dig > 19) return false;

        int check_number = (int) (num_card % 10); //Берём последнюю цифру
        int sum_num = 0;
        num_card = num_card / 10;//удаляем последнюю цифру
        Integer[] num_list = rollNumbersInArrays(num_card);// все числа переводятся в массив

        for (int i = 0; i < num_list.length; i++) {
            if (i % 2 != 0) { //проверка на чётность
                num_list[i] = num_list[i] * 2; //удваваем
                if (num_list[i] > 9) num_list[i] = sumTwoDigits(num_list[i]);//Если больше 9- складываем цифры числа
            }
            sum_num += num_list[i];
        }
        return (10 - sum_num % 10) == check_number;//конечный вывод

    }
// Для перевода чисел в массив
    public static Integer[] rollNumbersInArrays(Long num) {
        ArrayList<Integer> ret_list = new ArrayList<Integer>(); //Новый список
        int reversed = 0;
        while (num != 0) {
            ret_list.add((int) (num % 10));
            num = num / 10;
        }
        return ret_list.toArray(new Integer[0]);
    }
//Складываем две цифры числа
    public static int sumTwoDigits(int num) {
        return num % 10 + num / 10;
    }

    /*
    Функция принимает число от 0 до 999 включительно и возвращает
    это число в виде слова на английском языке.
     */
    /*
    Выписываем уникальные варианты, потом перебираем число, от большего к меньшему
    Сначала Сотни, потом десятки и единицы
    И добавляем в выводимую строку
     */

    public static String numToEng(int num) {
        String ret_str = "";
        int iter = 0;
        if (num == 0) return "zero";
        if (num == 10) return "ten";
        if (num == 11) return "eleven";
        if (num == 12) return "twelve";
        String one = "one", two = "two", three = "three", four = "four", five = "five", six = "six",
                seven = "seven", eight = "eight", nine = "nine", fift = "fift", thirt = "thirt", twent = "twent";
        if (num > 99 && num < 1000) {
            switch (num / 100) {
                case (1) -> ret_str += one;
                case (2) -> ret_str += two;
                case (3) -> ret_str += three;
                case (4) -> ret_str += four;
                case (5) -> ret_str += five;
                case (6) -> ret_str += six;
                case (7) -> ret_str += seven;
                case (8) -> ret_str += eight;
                case (9) -> ret_str += nine;
            }
            ret_str += " hundred ";
            num = num - (num / 100 * 100);
        }
        if (num > 19 && num < 100) {
            switch (num / 10) {
                case (2) -> ret_str += twent;
                case (3) -> ret_str += thirt;
                case (4) -> ret_str += four;
                case (5) -> ret_str += fift;
                case (6) -> ret_str += six;
                case (7) -> ret_str += seven;
                case (8) -> ret_str += eight;
                case (9) -> ret_str += nine;
            }
            if (ret_str.charAt(ret_str.length() - 1) == 't') ret_str += "y ";
            else ret_str += "ty ";
            num = num - (num / 10 * 10);
        }
        if (num > 10 && num < 19) {
            switch (num % 10) {
                case (3) -> ret_str += thirt;
                case (4) -> ret_str += four;
                case (5) -> ret_str += fift;
                case (6) -> ret_str += six;
                case (7) -> ret_str += seven;
                case (8) -> ret_str += eight;
                case (9) -> ret_str += nine;
            }
            if (ret_str.charAt(ret_str.length() - 1) == 't') ret_str += "een";
            else ret_str += "teen";
            num = 0;
        }
        if (num > 0 && num < 10) {
            switch (num) {
                case (1) -> ret_str += one;
                case (2) -> ret_str += two;
                case (3) -> ret_str += three;
                case (4) -> ret_str += four;
                case (5) -> ret_str += five;
                case (6) -> ret_str += six;
                case (7) -> ret_str += seven;
                case (8) -> ret_str += eight;
                case (9) -> ret_str += nine;

            }
        }
        return ret_str;

    }
    /*
    Создать функцию, которая создаёт хэш-код для заданной строки на входе.
     */
    //Получаем байты:
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Статический метод getInstance вызывается с хешированием SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    //Переводим их в строку:
    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }


    /*
    Функция принимает строку с неправильными регистрами букв и возвращает с правильными,
    Все слова должны начинасться с заглавной буквы, кроме the, and, of, in.
     */
    public static String correctTitle(String s){
        String outS = "";
        String[] mas = s.split(" ");
        for (int i=0; i<mas.length; i++){
            if (!Objects.equals(mas[i].toLowerCase(), "and") && !Objects.equals(mas[i].toLowerCase(), "the") && !Objects.equals(mas[i].toLowerCase(), "of") && !Objects.equals(mas[i].toLowerCase(), "in")){
                outS += mas[i].substring(0,1).toUpperCase() + mas[i].substring(1).toLowerCase() + " "; //Если слово не исключение- изменяем первую букву на заглавную
            }
            else outS += mas[i].toLowerCase() + " ";//Или просто пишем это слово в нижнем регистре
        }
        return outS;
    }

    /*
    Гексагональная решётка- двумерная решетка, где каждая точка имеет 6 соседей.
    Центрированное шестиугольное число- число представляющее шестиугольник с точкой в центре
    и всеми другими точками окружающими центральную. Функция должна принимать число и определять
    допустимо ли оно для постройки решётки. Отобразить вывод в виде строки- где друг за другом идут
    строки для построения решётки.
     */
    public static String hexLattice(int n) { //постройка сетки. с верху. сначала три кружка и так далее
        String ret_str = "";
        String space = " ";
        String circl = "o ";
        int iter_lat = isLattice(n);
        if (iter_lat == 0) return "incorrect";
        for (int i = iter_lat; i <= iter_lat+(iter_lat-1); i++){
            ret_str += space.repeat(iter_lat+(iter_lat-1) - i);
            ret_str += circl.repeat(i);
            ret_str += "\n";
        }
        for (int i = iter_lat+(iter_lat-2); i >= iter_lat; i--){
            ret_str += space.repeat(iter_lat+(iter_lat-1) - i);
            ret_str += circl.repeat(i);
            ret_str += "\n";
        }
        return ret_str;
    }

    public static int isLattice(int n) { //Возврат на какой итерации столько кружков стало
        for (int i = 1; i < 100; i++) {
            int k = (int) (1 + 6 * (0.5 * i * (i - 1))); //формула для расчёта
            if(k == n){
                return i;
            }
        }
        return 0;
    }


}