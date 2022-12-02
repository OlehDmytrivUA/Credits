package MainMenu.Commands.SearchMenu.SearchCommands;

import Data.CreditInfo;
import Data.Credits;
import Data.User;
import MainMenu.Commands.SearchMenu.SearchMenuItem;
import WriteFile.CreditFile;

import java.io.IOException;
import java.util.Scanner;

public class SearchPercentCommand implements SearchMenuItem {
    Scanner in = new Scanner(System.in);
    Credits credits = new Credits();
    CreditInfo[] CredInf = credits.fillBankInfoArray(); // ����� �������
    CreditFile creditfile = new CreditFile();
    private int count = 0;
    @Override
    public int execute(User user) throws IOException {
        System.out.println("��������� ������ �� ������� �����");
        System.out.println("������ ������� �������:");
            //������� ������� ������� �������
        int percent = in.nextInt();

        for (CreditInfo creditInfo : CredInf) {
            if (creditInfo.getPercents() <= percent) { // �������� ������ �������
                System.out.println(creditInfo);
                count++;
            }
        }
        if (count == 0) System.out.println("���� ������ �������");

            //��������� �������� ������
        System.out.println("��������� ������ ��� ���������� �������?");
        System.out.println("1 - ���");
        System.out.println("2 - ͳ");
        int confirm = in.nextInt();
        switch (confirm) {
            case 1:
                creditfile.CreateCreditFile(user); //��������� �������
                break;
            case 2:
                break;
            default:
                System.out.println("������� ������, ������ �� ���:");
        }
        return 1;
    }
}
