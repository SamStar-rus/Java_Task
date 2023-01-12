import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class Task_Four {
        public static void main(String[] args) {
            //Для первого:
            System.out.println(text_recover(10,7, "hello my name is Bessie and this is my essay"));
            //Для второго:
            System.out.println(split("()()()"));
            System.out.println(split("((()))"));
            System.out.println(split("((()))(())()()(()())"));
            System.out.println(split("((())())(()(()()))"));
            //Для третьего:
            System.out.println(toCamelCase("hello_edabit"));
            System.out.println(toSnakeCase("helloEdabit"));
            System.out.println(toCamelCase("is_modal_open"));
            System.out.println(toSnakeCase("getColor"));
            //Для четвёртого:
            System.out.println(overTime(new double[]{9,17,30,1.5}));
            System.out.println(overTime(new double[]{16, 18, 30, 1.8}));
            System.out.println(overTime(new double[]{13.25, 15, 30, 1.5}));
            //Для пятого:
            System.out.println(BMI("205 pounds", "73 inches"));
            System.out.println(BMI("55 kilos", "1.65 meters"));
            System.out.println(BMI("154 pounds", "2 meters"));
            //Для шестого:
            System.out.println(bugger(39));
            System.out.println(bugger(999));
            System.out.println(bugger(4));
            //Для седьмого:
            System.out.println(toStarShorthand("abbccc"));
            System.out.println(toStarShorthand("77777geff"));
            System.out.println(toStarShorthand("abc"));
            System.out.println(toStarShorthand(""));
            //Для восьмого:
            System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
            System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
            System.out.println(doesRhyme("You are off to the races", "a splendid day."));
            System.out.println(doesRhyme("and frequently do?", "you gotta move."));
            //Для девятого:
            System.out.println(trouble(451999277, 41177722899L));
            System.out.println(trouble(1222345, 12345));
            System.out.println(trouble(666789, 12345667));
            System.out.println(trouble(33789, 12345337));
            //Для десятого:
            System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
            System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
            System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));

        }
        /*Слово сожержит до 15 букв, состоит из прописных или строчных букв.
        Каждая строка должна содержать не более 80 символов. Если слово вмещается в текущщую строку
        пишем, если нет- переносим на следующую.
         */
        public static String text_recover(int n, int k, String string){
            String[] mas_str = string.split(" "); //Переводим строку в массив
            int string_lengt = 0; //Длина строки
            String exit_string = ""; //Пустая строка для заполнения
            for (int i = 0; i < mas_str.length; i++){
                String now_string = mas_str[i];
                if(now_string.length() + string_lengt <= k ){ //Если длина слова + длнна строки до него <= k
                    exit_string += now_string + " "; //Тогда добав. в новую строку это слово
                    string_lengt += now_string.length(); //Прибавляем длинну строки
                } else {
                    exit_string += "\n" + now_string + " "; //Переносим на след. строку
                    string_lengt = now_string.length(); //Длина строки= длина слова
                }
            }
            return exit_string;
        }
        //Сгруппировать скобки по сбалансированным кластерам
        public static List split(String string){
            int num_bkt = 0; //переменная для количества скобок в кластере
            String return_str = ""; //Пустая строка для вывода
            String edit_str = ""; //Строка для сложения скобок
            for (int i = 0; i < string.length(); i++){
                if(string.charAt(i) == '('){ //Если символ i -'('
                    num_bkt+=1; //прибавляем 1
                }else if (string.charAt(i) == ')'){
                    num_bkt-=1;
                }
                edit_str += string.charAt(i); //Складываем значения в переменную
                if (num_bkt == 0) { //Проверяем баланс
                    return_str += edit_str + " "; //Переносим в переменную вывода
                    edit_str = ""; //обнуляем едит
                }
            }
            List strList = Arrays.asList(return_str); //Строка выводится как список
            return strList;
        }
        /*2 функции-toCameCase- уберает прочерки и создаёт заглавные буквы.
        to SnakeCase- добавляет прочерки и убирает заглавные буквы
         */
        public static String toCamelCase(String string){
            String ret_string = "";
            for(int i = 0; i < string.length(); i++){
                if (string.charAt(i) == '_'){
                    //Превращает аски код мал. буквы и превращает его в аски код большой буквы
                    int ascii = (int) string.charAt(i+1) - 32;
                    ret_string += (char) ascii; //Заменяет мал. букву заглавной
                    i+=1;
                }else {
                    ret_string += string.charAt(i); //просто добавляем буку в строку для вывода
                }
            }
            return ret_string;
        }
        public static String toSnakeCase(String string){
            String ret_string = "";
            for(int i = 0; i < string.length(); i++){
                if ((int) string.charAt(i) <= 90 && ((int) string.charAt(i) >= 65)){ //Если находит загалвную букву
                    ret_string += '_';

                    int ascii = (int) string.charAt(i) + 32; //Заменяет заглавную букву на прописную
                    ret_string += (char) ascii;
                } else {
                    ret_string += string.charAt(i);
                }
            }
            return ret_string;
        }
        /*Высчитать оплату сверхурочной работы. Обычные рабочие часы с 9 до 17.
        вывод: сколько заработал за день
         */
        public static String overTime(double[] enter_ms){
            double start_wk = enter_ms[0]; // - начало рабочего дня
            double stop_wk = enter_ms[1]; // - конец рабочего дня
            double salary = enter_ms[2]; // - ставка
            double factor = enter_ms[3]; // - коэф. переработки

            double normal_hr = 0; //переменная для рабочего времени
            double overprice_hr = 0; //переменная для сверхурочного
            if(stop_wk > 17){
                overprice_hr = stop_wk - 17; //количество часов переработки
                stop_wk -= overprice_hr;
            }
            normal_hr = stop_wk - start_wk; //узнаём сколько он работал по обычной ставке
            return ("$" + Math.round((normal_hr * salary + overprice_hr * salary * factor)*100d)/100d);
        }
        /*Индекс массы тела. получаем вес и рост, по формуле высчитывается индекс.
        до 18.5- недостаточный, 18.5-24.9- нормальный, 25 и больше- избыточный.
        Единицы счисления могут быть разные, результат округлить.
         */
        // m / h*h
        public static String BMI(String weight_str, String height_str){
            String[] arr_weight = weight_str.split(" "); //Делим строку по пробелу
            double weight_db = Double.parseDouble(arr_weight[0]); //присваиваем новой переменной- число- вес
            String type_of_weight = arr_weight[1]; //прсваиваем нов. переменной значение- ед. измер. веса

            String[] arr_height = height_str.split(" "); //То же делаем со второй строкой
            double height_db = Double.parseDouble(arr_height[0]);
            String type_of_height = arr_height[1];

            if(type_of_weight.charAt(0) == 'p') weight_db /= 2.205; //переводим в килограммы и метры
            if (type_of_height.charAt(0) == 'i') height_db/= 39.37;

            double bmi = weight_db/ (height_db*height_db);//высчитываем индекс массы

            if(bmi >= 25) return (Math.round(bmi*10d)/10d + " Overweight");
            else if (bmi < 18.5) return (Math.round(bmi*10d)/10d + " Underweight");
            else return (Math.round(bmi*10d)/10d + " Normal weight");
        }

    /* Возвращает мультипликативное ростоянство (кол-во раз, перемножения цифр числа друг на друга
    пока не получится в результате однозначная цифра)
     */
        public static int bugger(int start_num){

            if (start_num <= 9) return  0;

            int next_num = 1;
            int digit = (int) Math.ceil(Math.log10(start_num + 0.5));//определяем колличество цифр в числе
            while (digit != 0){
                next_num *= start_num % 10; //делим с остатком на 10- чтобы взять последнюю цифру числа
                start_num = start_num / 10; //делим без остатка, чтобы взять вторую цифру числа
                digit -= 1; //вычитаем из длинны числа
            }
            //рекурсия. сколько раз преобразуем число пока оно не станет меньше или равно 9
            return 1 + bugger(next_num);
        }

        /*Преобразует строку в звёздную стенографию. Если символ повторяется n раз.
        Преобразует его в символ*n
         */

        public static String toStarShorthand(String string){
            String return_string = "";
            string += "&"; // Добавляем на конец входной строки
            int n = 1;
            for (int i = 0; i < string.length()-1; i++){
                if(string.charAt(i) == string.charAt(i + 1)){
                    n++;
                }
                else {
                    return_string += string.charAt(i); //Добав. букву в выхлд. строку
                    //Добав. знак * и колличество этой буквы в строке
                    if (n != 1) return_string +=  "*" + String.valueOf(n);
                    n = 1;
                }
            }
            return return_string;
        }

        /* Проверить рифмуются ли две строки. Две строк рифмуются, если
        последнее слово каждоого предложениясодержит одни и те же гласные
         */
        public static boolean doesRhyme(String str1, String str2){
            String[] arr_str1 = str1.split(" "); //Делим строку неа слова по пробелам
            String[] arr_str2 = str2.split(" ");
            String last_word1 = arr_str1[arr_str1.length-1];//убираем в конце строк знаки припинания
            String last_word2 = arr_str2[arr_str2.length-1];
            String vowels_1 = "";//создаются две новые строки
            String vowels_2 = "";

            for(int i = 0; i < last_word1.length(); i++){
                //проверяет есть ли гласные в слове и добавляет их в строку
                if("AEIOUaeiou".indexOf(last_word1.charAt(i)) != -1) vowels_1+=last_word1.charAt(i);
            }
            for(int i = 0; i < last_word2.length(); i++){
                //то же самое для второй строки
                if("AEIOUaeiou".indexOf(last_word2.charAt(i)) != -1) vowels_2+=last_word2.charAt(i);
            }
            return vowels_1.equalsIgnoreCase(vowels_2);
        }
        /*Проверить повторяется ли заданное число три раза в первой строке и
        два раза во второй */
        public static boolean trouble(long num1, long num2){
            String num1_str = String.valueOf(num1); // перевод из int в string
            String num2_str = String.valueOf(num2);
            List<Character> list_rep_int = new ArrayList<>(); //создаем лист для значений из первой строки х3
            int q = 1;
            for (int i = 0; i < num1_str.length()-1; i++){
                if (num1_str.charAt(i) == num1_str.charAt(i+1)){
                    q+=1;
                }else if(q==3){
                    list_rep_int.add(num1_str.charAt(i));
                    q = 1;
                }else q = 1;
            }

            for(Character num : list_rep_int){ //перебираем список с повторениями
                String str = "" + num + num; // записыввем в новую переменную два значения
                return num2_str.contains(str); //проверяем есть ли эти повторения в второй строке
            }
            return false;

        }

        /*на вход получаем книгу-строку и конец книги- это же и начало, вывести
        из найденных книг уникальные буквы между началом и концом книги
         */
        public static int countUniqueBooks(String stringSequnce, char bookEnd){
            boolean lever = false;
            List<Character> list_char = new ArrayList<Character>();//список из символов
            for (int i = 0; i < stringSequnce.length()-1; i++){ //перебор первой строки
                if (stringSequnce.charAt(i) == bookEnd) lever = !lever; //если i равно концу книги, то переключаем рычаг
                if (lever){ //если рычаг активен
                    //если список не содержит i и i не равно конц книги
                    if(!list_char.contains(stringSequnce.charAt(i)) && stringSequnce.charAt(i) != bookEnd){
                        list_char.add(stringSequnce.charAt(i));//добавляем  в список
                    }
                }
            }
            return list_char.size();// возврат. длину списка
        }
    }

