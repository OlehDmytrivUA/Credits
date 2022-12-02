package MainMenu.Commands;
import Data.User;
import MainMenu.MenuItem;
import WriteFile.FileActions;
import java.io.IOException;
import java.sql.SQLException;

public class MyCreditsCommand implements MenuItem {

    @Override
    public void execute(User user) throws SQLException, ClassNotFoundException, IOException {
        FileActions fileActions = new FileActions();
            // ������� ���� ����������� �� ������� ID �������
        String userdata = fileActions.readFile("C:\\CreditData\\Users\\" + user.getUserName() + "_" + user.getPassword() + ".txt");
        String[] lines = userdata.split("\r\n"); // �������� �� ����� �����

        for (int i=0;i<lines.length;i++){
            if(lines[i].startsWith("Credits")) {  // ���� ������� ����� Credits:
                for (int j = i+1; j < lines.length; j++) {
                        //������ ���� ��� ������
                    String CreditData = fileActions.readFile("C:\\CreditData\\Credits\\Credit" + "_" + lines[j].replace("\r", "") + ".txt");
                        // �������� ���� ��� ������
                    System.out.println("\n" + CreditData);
                }
                break;
            }
        }
    }
}