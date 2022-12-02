package MainMenu.Commands;

import Data.User;
import MainMenu.MenuItem;
import WriteFile.FileActions;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EarlyLoanRepaymentCommand implements MenuItem {
    Scanner scan = new Scanner(System.in);
    FileActions fileActions = new FileActions();

    @Override
    public void execute(User user) throws IOException {
        int CreditID = -1;
        System.out.print("������ ID ������ �������:");
        CreditID = scan.nextInt(); // ������� ID �������

        File file = new File("C:\\CreditData\\Credits\\Credit_" + CreditID + ".txt");

        if(file.exists()) {
            System.out.print("������ ���� ������������ ���������:");
            double Sum = scan.nextDouble(); // ������� ����
                // ���������� �������� �� �������
            fileActions.earlyLoanRepayment("C:\\CreditData\\Credits\\Credit_" + CreditID + ".txt", Sum);
        }else{
            System.out.println("������� � ����� ID �� ����");
        }
    }
}
