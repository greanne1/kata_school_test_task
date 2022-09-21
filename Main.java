public class Main {
    public static String calc(String input) {
        //String str1;
        //Scanner in = new Scanner(System.in);
        //str1 = in.nextLine();
        String[] symbols = input.split(" ");

        try {                                             // обработала все возможные варианты неправильного ввода строки
            if (symbols.length != 3)
                throw new ScannerException();
        } catch (ScannerException e) {
            System.out.println("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System.exit(1);
        }

        int calc = 0; // метка для кол-ва римских чисел в вводе
        int a = 0;
        int b = 0;

        try {
            try {                                            // перевела римские числа в арабские
                a = Integer.parseInt(symbols[0]);
            } catch (NumberFormatException e) {
                Roman num = Roman.valueOf(symbols[0]);
                a = num.getArab();
                calc++;
            }
            try {
                b = Integer.parseInt(symbols[2]);
            } catch (NumberFormatException e) {
                Roman num = Roman.valueOf(symbols[2]);
                b = num.getArab();
                calc++;
            }
        }catch(IllegalArgumentException e){                                        // Обработка исключений, когда вводится все,
            System.out.println("Вводимые символы должны быть числами от 1 до 10"); // что угодно, но не числа (ну или числа не из диапазона)
            System.exit(1);
        }

        try{ // обработка исключения, если было введено 2 арабских числа, но не из диапазона
            if(a < 0 || a > 10 || b < 0 || b > 10)
                throw new ScannerException();
        }catch(ScannerException e){
            System.out.println("Вводимые символы должны быть числами от 1 до 10");
            System.exit(1);
        }

        try {
            if (calc == 1) {
                throw new ScannerException();
            }
        } catch (ScannerException e) {
            System.out.println("т.к. используются одновременно разные системы счисления");
            System.exit(1);
        }

        String operation = symbols[1];

        int result = 0;

        try {
            switch (operation) {
                case "+" -> result = a + b;
                case "-" -> result = a - b;
                case "*" -> result = a*b;
                case "/" -> result = a/b;
                default -> throw new ScannerException();
            }
        }catch(ScannerException e){
            System.out.println("Неправильно введена операция");
        }

        String res = "";

        if (calc == 0){
            res += result;
        }

        try {
            if(result < 0)
                throw new ScannerException();
            if (calc == 2) {
                switch (result / 10) {
                    case 10 -> res += "C";
                    case 9 -> res += "CX";
                    case 8 -> res += "LXXX";
                    case 7 -> res += "LXX";
                    case 6 -> res += "LX";
                    case 5 -> res += "L";
                    case 4 -> res += "XL";
                    case 3 -> res += "XXX";
                    case 2 -> res += "XX";
                    case 1 -> res += "X";
                }
                switch (result - (result / 10) * 10) {
                    case 9 -> res += "IX";
                    case 8 -> res += "XIII";
                    case 7 -> res += "XII";
                    case 6 -> res += "XI";
                    case 5 -> res += "V";
                    case 4 -> res += "IV";
                    case 3 -> res += "III";
                    case 2 -> res += "II";
                    case 1 -> res += "I";
                }
            }
        }catch(ScannerException e){
            System.out.println("т.к. в римской системе нет отрицательных чисел");
            System.exit(1);
        }
        return res;
    }
}
