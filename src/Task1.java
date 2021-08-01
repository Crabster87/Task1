import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task1 {

    public static final String INPUT_FILE_PATH = "src/task_2_addresses.txt";                                            // Записывваем путь к исходному файлу(я сразу поместил его в папку вместе с программой)
                                                                                                                        // С помощью слов 'public static final' я сделал это поле константой(они всегда пишутся большими буквами)
    public static void main(String[] args) throws IOException {                                                         // Метод мэйн(точка входа в программу), к нему добавлено 'throws IOException' это обработка исключений ввода-вывода
        removeRadischevaStreet();                                                                                       // Здесь вызываем метод, который выполняет только первую часть задания по удалению улицы Радищева
        removeChosenStreet();                                                                                           // Здесь вызываем метод, в котором реализована логика удаления улицы по зпросу
    }

    public static void removeRadischevaStreet() throws IOException {                                                    // В программе у нас все методы кроме последнего статические и бросают исключения
        String outputFilePath = getOutputFilePath() + ".txt";                                                           // Записываем путь к новому файлу, для этого вызываем вспомогательный метод getOutputFilePath()
        String line;                                                                                                    // Объявляем переменную, в которую будут записываться считываемые строки
        try (BufferedReader bufferedReader = new BufferedReader(                                                        // С помощью оператора 'try with resources' создаем безопасные поток ЧТЕНИЯ 'bufferedReader' целой строки в буфер
                                             new InputStreamReader(                                                     // В наш буфер для чтения обёрнут символьный поток данных 'InputStreamReader'
                                             new FileInputStream(INPUT_FILE_PATH), StandardCharsets.UTF_8));            // Здесь создается байтовый входной поток данных в кодировке 'UTF_8' на основании прописанного пути
             BufferedWriter bufferedWriter = new BufferedWriter(                                                        // В том же блоке 'try with resources' создаем безопасные поток ЗАПИСИ 'bufferedWriter' целой строки
                                             new OutputStreamWriter(                                                    // В наш буфер для записи обёрнут символьный поток данных 'OutputStreamWriter'
                                             new FileOutputStream(outputFilePath), StandardCharsets.UTF_8))) {          // Здесь создается байтовый вЫходной поток данных в кодировке 'UTF_8' на основании прописанного пути
            while ((line = bufferedReader.readLine()) != null) {                                                        // "До тех по пока строки из файла читаются (!=null)"
                if (!line.startsWith("Радищева")) bufferedWriter.write(line + "\n");                                    // "Если каждая строка НЕ начинается с 'Радищева', то записываем её в поток записи, прибавив символ перехода на новую строку"
            }                                                                                                           // Таким образом данные уходят во вновь созданный файл 'task_1_addresses_result'
            System.out.println("Данные по улице Радищева не попали в итоговый файл согласно заданию!");                 // Просто отчёт, что метод отработал
        }
    }

    public static void removeChosenStreet() throws IOException {
        Scanner scanner = new Scanner(System.in);                                                                       // Создаём объект класса Scanner, который будет считывать вводимые в консоль данные
        List<String> list = new ArrayList<>();                                                                          // Создаём коллекцию(список) куда данные будем записывать целыми строками по одной
        String outputFilePath = getOutputFilePath() + "_removing.txt";                                                  // Записываем путь к новому файлу, для этого вызываем вспомогательный метод getOutputFilePath()
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(
                                             new InputStreamReader(
                                             new FileInputStream(INPUT_FILE_PATH), StandardCharsets.UTF_8));            // Здесь полная аналогия с той разницей, что записывать планируем в другой файл, его имя будет заканчиваться на '_removing.txt'
             BufferedWriter bufferedWriter = new BufferedWriter(
                                             new OutputStreamWriter(
                                             new FileOutputStream(outputFilePath), StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {                                                        // "До тех по пока строки из файла читаются (!=null)"
                list.add(line);                                                                                         // Добавляем каждую считанную строку в коллекцию
            }
            System.out.println("В начальной версии списка находятся следующие улицы:");                                 // Печатаем такую строку-напоминалку
            list.forEach(s -> System.out.print(s.substring(0, s.indexOf(" ")) + " "));                                  // Вот здесь сложная тема: печатаем каждый элемент коллекции в одну строку через пробел
                                                                                                                        // Но поскольку в коллекции 4 таких строки:
                                                                                                                        // 1) "Строителей 25 | 145 | Эконом"
                                                                                                                        // 2) "Радищева 43 | 50 | Комфорт"
                                                                                                                        // 3) "Радищева 44 | 500 | Эконом"
                                                                                                                        // 4) "Гагарина 25 | 12 | Элитный"
                                                                                                                        // а нам надо только название улицы, то мы каждую обрезаем в интервале от начала '0' до первого пробела методом 'substring(0, s.indexOf(" ")'
                                                                                                                        // так на печать без лишней мути попадают только улицы
            while (true) {                                                                                              // Создаём бесконечный цикл, который можно прервать только вводом слова 'выход'
                System.out.println("\nВведите название той, данные по которой необходимо удалить!"                      // Печатаем инструкцию
                                 + " Либо введите 'выход' чтобы закончить работу");
                String enteredName = scanner.nextLine();                                                                // Сканнером считываем введённую строку
                if (enteredName.equalsIgnoreCase("выход")) break;                                                       // Распознаём её, игнорируя регистр. Если это слово 'выход', то прерываем цикл и программа завершается
                else {                                                                                                  // А если нет, то
                    list.removeIf(s -> s.substring(0, s.indexOf(" ")).equalsIgnoreCase(enteredName));                   // Удаляем из коллекции нужную улицу, для чего сравниваем введенное название с каждым
                }                                                                                                       // обрезанным до первого пробела элеметном коллекции, игнорируя регистр
            }
            for (int i = 0; i < list.size(); i++) {                                                                     // Здесь наш список стал короче, если удаление было совершено, мы не знаем на сколько именно он укортился, поэтому обходим все элементы,
                bufferedWriter.write(list.get(i) + "\n");                                                               // не зная сколько их 'list.size()'. Каждый из них записываем в поток записи и добавляем символ новой строки
            }
        }
    }

    public static File getOutputFilePath() {                                                                            // Я до конца не понял, надо ли было это делать в рамках задания, или можно было тупо самому дату нарисовать...
        Date currentDate = new Date();                                                                                  // Создаём объект дата, чтобы получить точную дату и время
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_YYYY");                                                // Создаём шаблон формата, согласно которого нам нужно получить данные (день_месяц_год)
        String date = formatter.format(currentDate);                                                                    // Получаем строку с помошью шаблона, подставиви в него дату
        File filePath = new File("src/task_1_addresses_result_" + date);                                                // Дописываем эту дату к уже почти сформированному пути в самый конец
        return filePath;                                                                                                // Возвращаем эту строку, выше мы её будем 2 раза использовать(строка 16 и 34)
    }

}
