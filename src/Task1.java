import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task1 {

    public static final String INPUT_FILE_PATH = "src/task_2_addresses.txt";                                            // ����������� ���� � ��������� �����(� ����� �������� ��� � ����� ������ � ����������)
                                                                                                                        // � ������� ���� 'public static final' � ������ ��� ���� ����������(��� ������ ������� �������� �������)
    public static void main(String[] args) throws IOException {                                                         // ����� ����(����� ����� � ���������), � ���� ��������� 'throws IOException' ��� ��������� ���������� �����-������
        removeRadischevaStreet();                                                                                       // ����� �������� �����, ������� ��������� ������ ������ ����� ������� �� �������� ����� ��������
        removeChosenStreet();                                                                                           // ����� �������� �����, � ������� ����������� ������ �������� ����� �� ������
    }

    public static void removeRadischevaStreet() throws IOException {                                                    // � ��������� � ��� ��� ������ ����� ���������� ����������� � ������� ����������
        String outputFilePath = getOutputFilePath() + ".txt";                                                           // ���������� ���� � ������ �����, ��� ����� �������� ��������������� ����� getOutputFilePath()
        String line;                                                                                                    // ��������� ����������, � ������� ����� ������������ ����������� ������
        try (BufferedReader bufferedReader = new BufferedReader(                                                        // � ������� ��������� 'try with resources' ������� ���������� ����� ������ 'bufferedReader' ����� ������ � �����
                                             new InputStreamReader(                                                     // � ��� ����� ��� ������ ������ ���������� ����� ������ 'InputStreamReader'
                                             new FileInputStream(INPUT_FILE_PATH), StandardCharsets.UTF_8));            // ����� ��������� �������� ������� ����� ������ � ��������� 'UTF_8' �� ��������� ������������ ����
             BufferedWriter bufferedWriter = new BufferedWriter(                                                        // � ��� �� ����� 'try with resources' ������� ���������� ����� ������ 'bufferedWriter' ����� ������
                                             new OutputStreamWriter(                                                    // � ��� ����� ��� ������ ������ ���������� ����� ������ 'OutputStreamWriter'
                                             new FileOutputStream(outputFilePath), StandardCharsets.UTF_8))) {          // ����� ��������� �������� �������� ����� ������ � ��������� 'UTF_8' �� ��������� ������������ ����
            while ((line = bufferedReader.readLine()) != null) {                                                        // "�� ��� �� ���� ������ �� ����� �������� (!=null)"
                if (!line.startsWith("��������")) bufferedWriter.write(line + "\n");                                    // "���� ������ ������ �� ���������� � '��������', �� ���������� � � ����� ������, �������� ������ �������� �� ����� ������"
            }                                                                                                           // ����� ������� ������ ������ �� ����� ��������� ���� 'task_1_addresses_result'
            System.out.println("������ �� ����� �������� �� ������ � �������� ���� �������� �������!");                 // ������ �����, ��� ����� ���������
        }
    }

    public static void removeChosenStreet() throws IOException {
        Scanner scanner = new Scanner(System.in);                                                                       // ������ ������ ������ Scanner, ������� ����� ��������� �������� � ������� ������
        List<String> list = new ArrayList<>();                                                                          // ������ ���������(������) ���� ������ ����� ���������� ������ �������� �� �����
        String outputFilePath = getOutputFilePath() + "_removing.txt";                                                  // ���������� ���� � ������ �����, ��� ����� �������� ��������������� ����� getOutputFilePath()
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(
                                             new InputStreamReader(
                                             new FileInputStream(INPUT_FILE_PATH), StandardCharsets.UTF_8));            // ����� ������ �������� � ��� ��������, ��� ���������� ��������� � ������ ����, ��� ��� ����� ������������� �� '_removing.txt'
             BufferedWriter bufferedWriter = new BufferedWriter(
                                             new OutputStreamWriter(
                                             new FileOutputStream(outputFilePath), StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {                                                        // "�� ��� �� ���� ������ �� ����� �������� (!=null)"
                list.add(line);                                                                                         // ��������� ������ ��������� ������ � ���������
            }
            System.out.println("� ��������� ������ ������ ��������� ��������� �����:");                                 // �������� ����� ������-�����������
            list.forEach(s -> System.out.print(s.substring(0, s.indexOf(" ")) + " "));                                  // ��� ����� ������� ����: �������� ������ ������� ��������� � ���� ������ ����� ������
                                                                                                                        // �� ��������� � ��������� 4 ����� ������:
                                                                                                                        // 1) "���������� 25 | 145 | ������"
                                                                                                                        // 2) "�������� 43 | 50 | �������"
                                                                                                                        // 3) "�������� 44 | 500 | ������"
                                                                                                                        // 4) "�������� 25 | 12 | �������"
                                                                                                                        // � ��� ���� ������ �������� �����, �� �� ������ �������� � ��������� �� ������ '0' �� ������� ������� ������� 'substring(0, s.indexOf(" ")'
                                                                                                                        // ��� �� ������ ��� ������ ���� �������� ������ �����
            while (true) {                                                                                              // ������ ����������� ����, ������� ����� �������� ������ ������ ����� '�����'
                System.out.println("\n������� �������� ���, ������ �� ������� ���������� �������!"                      // �������� ����������
                                 + " ���� ������� '�����' ����� ��������� ������");
                String enteredName = scanner.nextLine();                                                                // ��������� ��������� �������� ������
                if (enteredName.equalsIgnoreCase("�����")) break;                                                       // ��������� �, ��������� �������. ���� ��� ����� '�����', �� ��������� ���� � ��������� �����������
                else {                                                                                                  // � ���� ���, ��
                    list.removeIf(s -> s.substring(0, s.indexOf(" ")).equalsIgnoreCase(enteredName));                   // ������� �� ��������� ������ �����, ��� ���� ���������� ��������� �������� � ������
                }                                                                                                       // ���������� �� ������� ������� ��������� ���������, ��������� �������
            }
            for (int i = 0; i < list.size(); i++) {                                                                     // ����� ��� ������ ���� ������, ���� �������� ���� ���������, �� �� ����� �� ������� ������ �� ���������, ������� ������� ��� ��������,
                bufferedWriter.write(list.get(i) + "\n");                                                               // �� ���� ������� �� 'list.size()'. ������ �� ��� ���������� � ����� ������ � ��������� ������ ����� ������
            }
        }
    }

    public static File getOutputFilePath() {                                                                            // � �� ����� �� �����, ���� �� ���� ��� ������ � ������ �������, ��� ����� ���� ���� ������ ���� ����������...
        Date currentDate = new Date();                                                                                  // ������ ������ ����, ����� �������� ������ ���� � �����
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_YYYY");                                                // ������ ������ �������, �������� �������� ��� ����� �������� ������ (����_�����_���)
        String date = formatter.format(currentDate);                                                                    // �������� ������ � ������� �������, ���������� � ���� ����
        File filePath = new File("src/task_1_addresses_result_" + date);                                                // ���������� ��� ���� � ��� ����� ��������������� ���� � ����� �����
        return filePath;                                                                                                // ���������� ��� ������, ���� �� � ����� 2 ���� ������������(������ 16 � 34)
    }

}
