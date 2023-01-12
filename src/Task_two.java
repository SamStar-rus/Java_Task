import java.util.Arrays;

public class Task_two {
    public static void main (String[] args) {
        System.out.println (repeat("mice", 5));
        System.out.println (repeat("hello", 3));
        System.out.println (repeat("stop", 1));

        int[] mas = {10, 4, 1, 4, -10, -50, 32, 21};
        System.out.println (differenceMaxMin (mas));
        int[] mas_two = {44, 32, 86, 19};
        System.out.println (differenceMaxMin (mas_two));

        int[] nas = {1, 3};
        System.out.println (isAvgWhole (nas));
        int[] nas_two = {1, 2, 3, 4};
        System.out.println (isAvgWhole (nas_two));
        int[] nas_three = {1, 5, 6};
        System.out.println (isAvgWhole (nas_three));
        int[] nas_four = {1, 1, 1};
        System.out.println (isAvgWhole (nas_four));
        int[] nas_five = {9, 2, 2, 5};
        System.out.println (isAvgWhole (nas_five));

        int[] xas = {1, 2, 3};
        System.out.println (Arrays.toString(cumulativeSum(xas)));
        int[] xas_two = {1, -2, 3};
        System.out.println (Arrays.toString(cumulativeSum(xas_two)));
        int[] xas_three = {3, 3, -2, 408, 3, 3};
        System.out.println (Arrays.toString(cumulativeSum(xas_three)));

        System.out.println (getDecimalPlaces("43.20"));
        System.out.println (getDecimalPlaces("400"));
        System.out.println (getDecimalPlaces("3.1"));

        System.out.println (Fibonacci(3));
        System.out.println (Fibonacci(7));
        System.out.println (Fibonacci(12));

        System.out.println (isValid("59001"));
        System.out.println (isValid("853a7"));
        System.out.println (isValid("732 32"));
        System.out.println (isValid("393939"));

        System.out.println (isStrangePair("ratio", "orator"));
        System.out.println (isStrangePair("sparkling", "groups"));
        System.out.println (isStrangePair("bush", "hubris"));
        System.out.println (isStrangePair("", ""));

        System.out.println (isPrefix("automation", "auto-"));
        System.out.println (isSuffix("arachnophobia", "-phobia"));
        System.out.println (isPrefix("retrospect", "sub-"));
        System.out.println (isSuffix("vocation", "-logy"));

        System.out.println (boxSeq(0));
        System.out.println (boxSeq(1));
        System.out.println (boxSeq(2));



    }
        // Повторяет каждый символ в заданной строке n количество раз.
        public static String repeat(String s, int n){
            String x = ""; //создаём пустую строку, для заполнения
            for (int i = 0; i < s.length(); i++) { //перебираем символы в воддимой строке
                for (int j = 0; j < n; j++) { //перебор опираясь на n, чтобы буквы повторялись
                    x += s.charAt(i); //на каждом шаге предидущего перебора добавляем букву в выходную строку
                }
            }
            return x;
        }

        // Выводит разницу между самым большим и самым маленьким значениями в массиве.
        public static int differenceMaxMin (int[] mas){
            int max = 0;
            int min = Integer.MAX_VALUE; //присваиваем переменной самое большое значение из возможных
            for (int i = 0; i < mas.length; i++){ //Перебираем пока массив не канчится
                if (mas[i] > max){ //если значение под номером i из массива mas больше чем max
                    max = mas[i]; //присваиваем max значение из массива mas
                }
                if (mas[i] < min){ // находим минимальное значение
                    min = mas[i];
                }

            }
            return (max-min); //выводим разность
        }

        // Определяет является ли среднее значение массива целым числом (true ли false).
        public static boolean isAvgWhole (int[] nas){
            int sum = 0;
            for (int i = 0; i < nas.length; i++){ // Перебираем значения массива
                sum = sum + nas[i]; // Складываем все элементы массива
            }

            return (sum % nas.length == 0); //узнаём есть ли остаток при делении суммы всех элем. на длину массива.
        }

        // Создаёт массив из полученного на вход массива, где каждое число- сумма всех предидущих чисел плюс оно само.
        public static int[] cumulativeSum (int[] xas){
            int[] tas = new int[xas.length]; //создаём нов. массив, длина которого будет такая же как и у входного.
            int q = 0;
            for (int i = 0; i< xas.length; i++){ //перебираем элементы входного массива.
                q = q+ xas[i]; //складываем их значения в переменной q.
                tas[i]=q; //Ставим в нов. массив это число на то же место где стоит последний элем. суммы в начальном массиве.
            }
            return tas;
        }

        // Определяет количество знаков после точки, если в поданом на вход числе нет точки, выводится 0.
        public static int getDecimalPlaces (String d){
        if (d.indexOf('.')== -1) { //Праверка на целое число (есть ли точка в строке), есди нет- выводим 0.
            return (0);
        }
            return(d.length()- d.indexOf('.')-1); //вычитаем из длинны строки номер под которым стоит точка. (нумерация строки с 0)

        }

        // Фибоначчи
        public static int Fibonacci (int x){
            int n0 = 0;
            int n1 = 1;
            int n2 = 0;
            for(int i = 0; i < x; i++){
                n2=n0+n1;
                n0=n1;
                n1=n2;
            }
            return n2;
        }

        // Про почтовый индекс. чтобы строка на входе была длинной 5 символов, и содержала только цифры,
        // пробелы и буквы не подходят
        public static boolean isValid (String s){
        if (s.length() != 5) //если кол-во символов в входной строке больше 5, сразу false.
            return false;
        for (int i= 0; i < s.length(); i++){
            if (!Character.isDigit(s.charAt(i))) //Если данное число НЕ является числов- возвращаем false.
                    return false;
            }
        return true; //если предидущие условия НЕ выполнились- выводим true
        }

        // пределяет странную пару по нескольким условиям. Чтобы первый символ s совпадал с последним симовлом t
        // и наоборот, плюс частные случаи, когда обе строки пустые или одна из них пустая
        public static boolean isStrangePair (String s, String t){
        if (s.equals("") && t.equals("")){ // частный случай: если обе строки пустые- true
            return true;
        }
        if (s.equals("") || t.equals("")){ // Частный случай: если одна из строк пустая- false
            return false;
        }
            return s.charAt(0) == t.charAt(t.length() - 1) && t.charAt(0) == s.charAt(s.length() - 1);
            // если первый символ s совпадает с последним t (нумерация с 0) и наоборот- тогда true
        }

        // Является ли подстрока(b) суфиксом или префиксом строки(a) .
        public static boolean isSuffix (String a, String b){
            b= b.replace("-", ""); //убираем - и заменяем его на пустоту
            return a.endsWith(b); //является ли b концом a.
        }
        public static boolean isPrefix (String a, String b) {
            b = b.replace("-", ""); //убираем -
            return a.startsWith(b); //является ли b началом a
        }

        // принимает число (шаг) в качестве аргумента и возвращает количество полей на этом шаге последовательности.
        public static int boxSeq(int step){
        if (step == 0){ //когда шаг = 0, возвращаем 0
            return 0;
        }else if (step%2 == 0){ //если шаг чётный- возращаем количество клеток (оно равно шагу)
            return step;
        }else return step+2; //если не чётное- возвращаем количество клеток (равное номеру шага) +2
    }
}
