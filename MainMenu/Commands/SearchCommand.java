package MainMenu.Commands;

import Data.User;
import MainMenu.Commands.SearchMenu.SearchMenu;
import MainMenu.MenuItem;

import java.io.IOException;
import java.util.Scanner;

public class SearchCommand implements MenuItem{ //���� ������� ������
    SearchMenu Menu = new SearchMenu();
    String command;
    Scanner scan = new Scanner(System.in);
    @Override
    public void execute(User user) throws IOException {
        int ret = -1;
        while(true) {  // ���� ���� ������
            System.out.println("��������� ������ ��:");
            System.out.println("1 - ��������� ������ �� �������� � �����");
            System.out.println("2 - ��������� ������ �� ������ �������");
            System.out.println("3 - ��������� ������ �� ��� �������");
            System.out.println("4 - ����������� �� ��������� ����");

            System.out.println("������� ����� ����");
            command = scan.next();
            ret = Menu.execute(command,user);
            if(ret == 0){ // ���� ��������� 0 ����� � ���� ������(�����)
                return;
            }
        }
    }
}
