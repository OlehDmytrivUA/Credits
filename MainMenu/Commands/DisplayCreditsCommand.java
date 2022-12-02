package MainMenu.Commands;
import Data.CreditInfo;
import Data.Credits;
import Data.User;
import MainMenu.MenuItem;
import WriteFile.CreditFile;
import java.util.Scanner;

public class DisplayCreditsCommand implements MenuItem {
    Scanner scan = new Scanner(System.in);
    CreditFile creditFile = new CreditFile();
    @Override
    public void execute(User user){
        System.out.println("���� ����������:");
        Credits credits = new Credits();
        CreditInfo[] creditInfo = credits.fillBankInfoArray(); // ������� ����� �������
        credits.printBankInfo(creditInfo); // ������� �� �������
        System.out.println('\n');

        //���� ����� ������ �������� ������
        System.out.println("��������� ������ ��� ���������� �������?");
        System.out.println("1 - ���");
        System.out.println("2 - ͳ");
        int confirm = scan.nextInt();
        switch (confirm) {
            case 1:
                creditFile.CreateCreditFile(user); //���������� ������
                break;
            case 2:
                break;
            default:
                System.out.println("������� ������, ������ �� ���:");
        }
    }
}
