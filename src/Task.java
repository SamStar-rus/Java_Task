public class Task {
    public static void main(String[] args) {
        // Для првого:
        System.out.println(remainder(1, 5));
        System.out.println(remainder(3, 4));
        System.out.println(remainder(-9, 45));
        System.out.println(remainder(5, 5));
        // Для второго:
        System.out.println(triArea(3, 2));
        System.out.println(triArea(7, 4));
        System.out.println(triArea(10, 10));
        // Для третьего:
        System.out.println(animals(2, 3, 5));
        System.out.println(animals(1, 2, 3));
        System.out.println(animals(5, 2, 8));
        //Для четвёртого:
        System.out.println(profitableGamble(0.2, 50, 9));
        System.out.println(profitableGamble(0.9, 1, 2));
        System.out.println(profitableGamble(0.9, 3, 2));
        //Для пятого:
        System.out.println(operation(24, 15, 9));
        System.out.println(operation(24, 26, 2));
        System.out.println(operation(15, 11, 11));
        //Для шестого:
        System.out.println(ctoa('A'));
        System.out.println(ctoa('m'));
        System.out.println(ctoa('['));
        //Для седьмого:
        System.out.println(addUpTo(3));
        System.out.println(addUpTo(10));
        System.out.println(addUpTo(7));
        //Для восьмого:
        System.out.println(nextEdge(8, 10));
        System.out.println(nextEdge(5, 7));
        System.out.println(nextEdge(9, 2));
        //Для девятого:
        int[] x = {1, 5, 9};
        int[] y = {3, 4, 5};
        int[] z = {2};
        int[] f = {};

        System.out.println(sumOfCubes(x));
        System.out.println(sumOfCubes(y));
        System.out.println(sumOfCubes(z));
        System.out.println(sumOfCubes(f));
        //Для десятого:
        System.out.println(abcmath(5,2,1));
        System.out.println(abcmath(1,2,3));
    }

    //Вывести остаток от деления
    public static int remainder(int x, int y) {
        return x%y;
    }

    //Вывести площадь треугольника
    public static int triArea(int x, int y) {
        return (x*y)/2;
    }

    //Считаем количество ног скота для фермера
    public static int animals(int chickens, int cows, int pigs) {
        return (chickens*2 + cows*4 + pigs*4);
    }

    //Правда или лож: произведение prob и prize должно быть больше pay
    public static boolean profitableGamble (double prob , double prize , double pay) {
        return (prob*prize>pay);
    }

    //Подбор знака меж a и b, для получения z
    public static String operation (int x , int y , int z) {
        if (x+y == z) {
            return "сложение";
        }
        if (x-y == z) {
            return "вычитание";
        }
        if (x*y == z) {
            return "умножение";
        }
        if (x/y == z) {
            return "деление";
        }
        return "Нет";
    }

    //Выводим ASCII
    public static int ctoa (char ss){
        return (ss);
    }

    //Вывод суммы всех чисел из последовательного списка с учётом последнего числа и без него
    public static int addUpTo (int z){
        int sum = 0;
        for (int i=1; i<=z; i++){
            sum += i;
        }
        return sum;
    }

    //Макс. знач. третьего ребра треугольника
    public static int nextEdge(int x, int y){
        return x+y-1;
    }

    //Возвращяет сумму кубов
    public static int sumOfCubes(int[] mas) {
        int sum = 0;
        for (int i = 0; i < mas.length; i++) {
            sum += mas[i] * mas[i] * mas[i];
        }
        return sum;
    }

    //Добавляется X сама к себе Y раз, проверка- делится ли результат на Z
    public static boolean abcmath(int x, int y, int z){
        for (int i=0; i<y; i++){
            x += x;
        }
        return (x%z == 0);
    }

}

