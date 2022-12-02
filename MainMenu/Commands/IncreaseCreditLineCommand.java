package MainMenu.Commands;
import Data.User;
import MainMenu.MenuItem;
import WriteFile.FileActions;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class IncreaseCreditLineCommand implements MenuItem {
    Scanner scan = new Scanner(System.in);

    FileActions fileActions = new FileActions();

    @Override
    public void execute(User user) throws IOException {
        int CreditID = -1;
        System.out.print("Введіть ID вашого кредиту:");
        CreditID = scan.nextInt(); // вводимо ID кредиту

        File file = new File("C:\\CreditData\\Credits\\Credit_" + CreditID + ".txt");

        if(file.exists()) {
            System.out.print("Введіть термін збільшення кредитної лінії(міс):");
            int Term = scan.nextInt();  // вводимо термін
                //збільшуємо кредитну лінію
            fileActions.increaseCreditLine("C:\\CreditData\\Credits\\Credit_" + CreditID + ".txt", Term);
        }else{
            System.out.println("Кредиту з даним ID не існує");
        }
    }
}
