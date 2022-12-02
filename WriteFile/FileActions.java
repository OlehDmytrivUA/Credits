package WriteFile;
import java.io.*;

public class FileActions {
    public String readFile(String fileName) throws IOException { //����� �� ���� ���� � ������� string
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while (true) {
            if (!((line = reader.readLine()) != null)) break;
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        reader.close();
        String content = stringBuilder.toString();
        return content;
    }

    public void MonthlyPayment(String filepath) throws IOException { // ����� ��� ������ ������ �� �������
        String data = readFile(filepath); //������� ���� �������
        String[] DataLines = data.split("\n", -1); // ������� ����� �����
        int  Term = 0;
        double Sum = 0, MonthlyPayment = 0, OverPayment = 0, Percent = 0;

            //���� ��������
        if (DataLines[1].replace("\r", "").compareTo("��������") == 0) {
            System.out.println("\n��� ������ ��� ��������\n");
            return;
        }
            //������� ��� ��� ������
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("����������")){
                Sum = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("̳�����")){
                Term = Integer.parseInt(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("��������")){
                MonthlyPayment = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
        }

            //��������� ���
        Term--;
        Sum-=MonthlyPayment;

        if(Sum<1){ //���� ��������
            DataLines[1] = "��������\n";
        }
            //�������� ��� ��� � �����
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("����������")){
                DataLines[i+1] = Double.toString(Sum) + "\n";
            }
            if(DataLines[i].startsWith("̳�����")){
                DataLines[i+1] = Term + "\n";
            }
        }
            //�������� � ����
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (String dataLine : DataLines) {
            writer.write(dataLine);
        }
        writer.close();
    }

        //����� ���������� ������ �� �������
        //����� ������ ������� , ��� ����� 40% �� ���� �� ����������
    public void earlyLoanRepayment(String filepath, double Payment) throws IOException {
        String data = readFile(filepath); //������� ���� �������
        String[] DataLines = data.split("\n", -1); // ������� ����� �����

        double limit = 0.4; //40% ��� ���������� ������

        int  Term = 0;
        double Sum = 0, MonthlyPayment = 0, OverPayment = 0, Percent = 0;
            //���� ��� ��������
        if (DataLines[1].replace("\r", "").compareTo("��������") == 0) {
            System.out.println("\n��� ������ ��� ��������\n");
            return;
        }
            //������� ��� � ������
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("����������")){
                Sum = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("̳�����")){
                Term = Integer.parseInt(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("���������")){
                OverPayment = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
        }
            //��������� ������� ������
        Percent = (OverPayment * 100 * 12) / ((Sum - OverPayment) * Term);

            //���� ��������
        if (Sum < Payment) {
            System.out.println("���� ���������� ������ ������� ������!\n"+
                    "���� ������� �������� �� ����� " + limit*100 + "% �� ���� �� ����������(" + (int)Sum*limit + ")");
            return;
        }
            //���� ���������� �������
        if (Sum <= Payment+1 && Sum>=Payment) {
            DataLines[1] = "��������\n";
            Sum = 0;
        }else
            //���� ������ ����� 40% ��� �� �����
            if(Sum<Payment/limit){
            System.out.println("���� ���������� ������ ������� ������!\n"+
                    "���� ������� �������� �� ����� " + limit*100 + "% �� ���� �� ����������(" + (int)Sum*limit + ")");
            return;
        }
                //���� ������ ����� 40% ��������� ��� ���
            else{
            Sum -= Payment;
            OverPayment = Sum - Sum / (1 + (Percent * Term / 1200));
            MonthlyPayment = Sum / Term;
        }

            //���������� ��� � �����
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("����������")){
                DataLines[i+1] = Double.toString(Sum) + "\n";
            }
            if(DataLines[i].startsWith("̳�����")){
                DataLines[i+1] = Term + "\n";
            }
            if(DataLines[i].startsWith("��������")){
                DataLines[i+1] = MonthlyPayment + "\n";
            }
            if(DataLines[i].startsWith("���������")){
                DataLines[i+1] = OverPayment + "\n";
            }
        }

            //�������� ������� ��� � ����
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (String dataLine : DataLines) {
            writer.write(dataLine);
        }
        writer.close();

    }

        //����� ��������� �������� ��
    public void increaseCreditLine(String filepath , int Months) throws IOException {
        String data = readFile(filepath); //������� ���� �������
        String[] DataLines = data.split("\n", -1); // ������� ����� �����

        int  Term = 0;
        double Sum = 0, MonthlyPayment = 0, OverPayment = 0, Percent = 0;

            //���� ������ ��������
        if (DataLines[1].replace("\r", "").compareTo("��������") == 0) {
            System.out.println("\n��� ������ ��� ��������\n");
            return;
        }
            //������� ��� � ������
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("����������")){
                Sum = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("̳�����")){
                Term = Integer.parseInt(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("���������")){
                OverPayment = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
        }
            //�������� �������� ��� � ���������� ��� ���
        Percent = (OverPayment * 100 * 12) / ((Sum - OverPayment) * Term);
        Term+=Months;
        OverPayment = Sum - Sum / (1 + (Percent * Term / 1200));
        MonthlyPayment = Sum / Term;

            //�������� ��� � ����� �����
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("����������")){
                DataLines[i+1] = Sum + "\n";
            }
            if(DataLines[i].startsWith("̳�����")){
                DataLines[i+1] = Term + "\n";
            }
            if(DataLines[i].startsWith("��������")){
                DataLines[i+1] = MonthlyPayment + "\n";
            }
            if(DataLines[i].startsWith("���������")){
                DataLines[i+1] = OverPayment + "\n";
            }
        }
            //�������� ��� � ����
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (String dataLine : DataLines) {
            writer.write(dataLine);
        }
        writer.close();
    }

}

