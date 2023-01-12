import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Task_Three {
    public static void main(String[] args) {
        //Для первого задания:
        System.out.println(solutions(1, 0, -1));
        System.out.println(solutions(1, 0, 0));
        System.out.println(solutions(1, 0, 1));

        //Для второго задания:
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed"));

        //Для третьего:
        System.out.println(checkPerfect(6));
        System.out.println(checkPerfect(28));
        System.out.println(checkPerfect(496));
        System.out.println(checkPerfect(12));
        System.out.println(checkPerfect(97));

        //Для четвёртого:
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(flipEndChars("ada"));
        System.out.println(flipEndChars("Ada"));
        System.out.println(flipEndChars("z"));

        //Для пятого:
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(isValidHexCode("#EAECEE"));
        System.out.println(isValidHexCode("#eaecee"));
        System.out.println(isValidHexCode("#CD5C58C"));
        System.out.println(isValidHexCode("#CD5C5Z"));
        System.out.println(isValidHexCode("#CD5C&C"));
        System.out.println(isValidHexCode("CD5C5C"));

        //Для шестого:
        Integer[] m = {1, 3, 4, 4, 4};
        Integer[] n = {2, 5, 7};
        System.out.println(same(m,n));
        Integer[] a = {9, 8, 7, 6};
        Integer[] b = {4, 4, 3, 1};
        System.out.println(same(a,b));
        Integer[] x = {2};
        Integer[] y = {3, 3, 3, 3, 3};
        System.out.println(same(x,y));

        //Для седьмого:
        System.out.println(isKaprekar(3));
        System.out.println(isKaprekar(5));
        System.out.println(isKaprekar(297));

        //Для восьмого:
        System.out.println(longestZero("01100001011000"));
        System.out.println(longestZero("100100100"));
        System.out.println(longestZero("11111"));

        //Для девятого:
        System.out.println(nextPrime(12));
        System.out.println(nextPrime(24));
        System.out.println(nextPrime(11));

        //Для десятого:
        System.out.println(rightTriangle(3, 4, 5));
        System.out.println(rightTriangle(145, 105, 100));
        System.out.println(rightTriangle(70, 130, 110));
    }

    //Вернуть колличество решений квадратного уравнения
    public static int solutions(int a, int b, int c) {
        int x = 0;
        int d = ((b * b) - 4 * a * c);
        if (d == 0) x = 1;
        if (d > 0) x = 2;
        return x;
    }

    //Вывести позицию второго вхождения "zip" в строку. Или -1 (если второго нет)
    public static int findZip(String s) {
          return s.indexOf("zip", s.indexOf("zip") + 2);
    }

    //Является ли целое число совершенным (сумма его множителей не включая его равна ему самому)
    public static boolean checkPerfect(int s) {
        int x = 0;
        for (int i = 1; i < s; i++) {
            if (s % i == 0) {
                x = x + i;
            }
        }
        return (x == s);
    }

    /*Возвращает строку с заменой её первого и последнего символов (если длина меньше 2-х- "несовместимо",
    если первый и последний символы- одинаковые- "Два-это пара"
     */
    public static String flipEndChars(String s) {
        if (s.length() <= 2) return "несовместимо";
        if (s.charAt(0) == s.charAt(s.length() - 1)) return "Два- это пара";
        return s.charAt(s.length() - 1) + s.substring(1, s.length() - 1) + s.charAt(0);
    }

    //Допустимый ли 16-ричный код? Должен начинаться с #, длинной 6 символов, каждый символ 0-9 или A-F
    public static boolean isValidHexCode(String s) {
        return Pattern.matches("^#[a-fA-F0-9]{6}$", s);
    }

    //Имеют ли два массива одиинаковое количество уникальных элементов ?
    public static boolean same(Integer[] arr1, Integer[] arr2) {
        List<Integer> arr1_edit = new ArrayList<Integer>(Arrays.asList(arr1)); //Преобразует массив в класс список (лист)
        List<Integer> arr2_edit = new ArrayList<Integer>(Arrays.asList(arr2));

        arr1_edit = supportSort(arr1_edit); //присваиваем значение, которое выдает нужняя ф-ия
        arr2_edit = supportSort(arr2_edit);
        return arr1_edit.size() == arr2_edit.size();//Сравниваем кол-во уникал. значений в массивах
    }

    public static List<Integer> supportSort(List<Integer> list) { // ф-ия оставляет только уникальные значения
        List<Integer> list_edit = list;
        for (int i = 0; i < list_edit.size(); i++) {
            for (int k = i + 1; k < list.size(); k++) {
                if (list_edit.get(i) == list_edit.get(k)) { //сравниваем элемент первого перебора с элементом второго перебора
                    list_edit.remove(k); //удаляет значение k
                    k -= 1;
                }
            }
        }
        return list_edit;
    }

    //Число Капрекара.
    public static boolean isKaprekar(int num) {
        if (num == 0 || num == 1) return true;

        int n_sqad = num * num;
        String string_n = Integer.toString(n_sqad);
        String left = "";
        String right = "";

        if (string_n.length() == 1) return false;
        for (int i = 0; i < string_n.length(); i++) {
            if (i < string_n.length() / 2) {
                left += string_n.charAt(i);
            } else right += string_n.charAt(i);
        }
        return Integer.parseInt(left) + Integer.parseInt(right) == num;
    }

    //Ф-ия возвращает самую длинную последовательность нулей в двоичной строке.
    public static String longestZero(String s) {
        String[] a = s.split("1");
        if (a.length == 0) return "";
        Arrays.sort(a); //Отсортирует строку- в начале самая короткая, в конце- самая длинная
        return (a[a.length - 1]);
    }

    //Если число целое- вывести следущее просто число, если число простое- вывести само число
    public static int nextPrime(int z) {
        int y = z;
        while (true) {
            int x = 1;
            for (int i = 2; i < z; i++) {
                if (y % i == 0)
                    x = 0;
            }
            if (x == 1) {
                return y;
            }
            y += 1;

        }

    }


    //Определить являются ли числа x, y и z рёбрами прямоугольного треугольника
    public static boolean rightTriangle(int a,int b,int c){
    return ((a*a+b*b)==c*c) ||  ((a*a+c*c)==b*b) || ((c*c+b*b)==a*a);
    }




}





