package WriteFile;
import Data.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class CreditFile {

    public void CreateCreditFile(User user){ // ����� �������� ����� �������
        Scanner scan = new Scanner(System.in);
        Credits credits = new Credits();
        CreditInfo[] CredInf = credits.fillBankInfoArray();
        String creditID, bankName = null, creditName = null;
        int creditSumMin = 0, creditSumMax = 0, creditTermMin = 0, creditTermMax = 0, count = 0;
        double percents = 0.0, monthlyFee, overpayment;
        File credit;
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        System.out.println("�������� " + user.getFirstName() + " " + user.getLastName() + ", ������ ID ������� ���� �� ������ ������:");

        while (true) {
            creditID = scan.next();  // ������� ������ �� ID

            for (CreditInfo creditInfo : CredInf) { // �������� � ������� ��� ��� ������� ������
                if (creditInfo.getCreditID().equals(creditID)) {
                    System.out.println(creditInfo);
                    creditID = creditInfo.getCreditID();
                    bankName = creditInfo.getBankName();
                    creditName = creditInfo.getCreditName();
                    creditSumMax = creditInfo.getCreditSumMax();
                    creditSumMin = creditInfo.getCreditSumMin();
                    creditTermMax = creditInfo.getCreditTermMax();
                    creditTermMin = creditInfo.getCreditTermMin();
                    percents = creditInfo.getPercents();
                    count++;
                    break;
                }
            }
            if(count>0){
                break;
            }
            System.out.println("���� ������ ID, ��������� �� ���: ");
        }

            // ������� ���� �������
        System.out.println("������ ���� �������(� �������):");
        int sum = scan.nextInt();
        while (sum < creditSumMin || sum > creditSumMax) {
            System.out.println("���� ������� ����� ������/����, ������ ���� ������� �� ���: ");
            sum = scan.nextInt();

        }

            //������� ����� �������
        System.out.println("������ ����� �������(� ������):");
        int term = scan.nextInt();
        while (term < creditTermMin || term > creditTermMax) {
            System.out.println("����� ������� ����� �������/�����, ������ ����� ������� �� ���: ");
            term = scan.nextInt();
        }

            // ���������� ��������� �� �������� � ������� ����� � ����������� ������� �����
        overpayment = (percents*sum*term/12)/100;
        monthlyFee = (sum+overpayment)/term;
        System.out.println("�������� ������� ���������: " + (double) ((int) (monthlyFee * 100)) / 100 + "���\n");
        System.out.println("��������� �� ������� ���������: " + (double) ((int) (overpayment * 100)) / 100 + "���\n");

            // ��������� ���� ��� �������
        Random rand = new Random();
        try {
            int Id = -1;
            while (true) {
                Id = rand.nextInt(100000); // �������� ID
                credit = new File("C:\\CreditData\\Credits\\Credit_" + Id + ".txt");
                if (!credit.exists()) {
                    credit.createNewFile();
                    try {  // ��������� �� ����� ����������� ID ���������� �������
                        fw = new FileWriter("C:\\CreditData\\Users\\" + user.getUserName() + "_" + user.getPassword() + ".txt", true);
                        bw = new BufferedWriter(fw);
                        pw = new PrintWriter(bw);
                        pw.println(Id);
                        pw.flush();
                    } finally {
                        try {
                            pw.close();
                            bw.close();
                            fw.close();
                        } catch (IOException io) {
                        }
                    }
                }
                break;
            }

                //������� ������ ��� �����
            String CreditID = Integer.toString(Id);
            String SumStr = Double.toString(sum);
            String SumWithOverpaymentStr = Double.toString(sum+overpayment);
            String termStr = Integer.toString(term);
            String creditSumMaxStr = Integer.toString(creditSumMax);
            String creditSumMinStr = Integer.toString(creditSumMin);
            String creditTermMaxStr = Integer.toString(creditTermMax);
            String creditTermMinStr = Integer.toString(creditTermMin);
            String percentsStr = Double.toString(percents);

                // �������� ��� � ����
            System.out.println("ID ������ ������� - "+Id);
            PrintWriter writer = new PrintWriter(credit);

            writer.append("������:").append(CreditID).append(" |").append(SumStr).append("��� ").append(termStr).append("��").append("\n\n");

            writer.append("����: ").append(bankName).append(" ID: ").append(creditID).append(String.valueOf('\n'));
            writer.append("����� �������: ").append(creditName).append(String.valueOf('\n'));
            writer.append(creditSumMinStr).append(String.valueOf('-')).append(creditSumMaxStr).append("���  ");
            writer.append(creditTermMinStr).append(String.valueOf('-')).append(creditTermMaxStr).append("��  ");
            writer.append(percentsStr).append("%\n\n");

            writer.append("������\n");
            writer.append("���������� ��������(���):\n").append(SumWithOverpaymentStr).append("\n");
            writer.append("̳�����:\n").append(termStr).append("\n");
            writer.append("�������� �������(���):\n").append(String.valueOf((double) ((int) (monthlyFee * 100)) / 100)).append("\n");
            writer.append("��������� �� �������(���):\n").append(String.valueOf((double) ((int) (overpayment * 100)) / 100)).append("\n\n");

            writer.append("��'�: ").append(user.getFirstName()).append(String.valueOf('\n'));
            writer.append("�������: ").append(user.getLastName()).append(String.valueOf('\n'));
            writer.append("�������� ������:").append(user.getCreditCard()).append(String.valueOf('\n'));
            writer.append("------------------------------------------------------\n");
            writer.flush();
            writer.close();

            System.out.println("��� �������.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

