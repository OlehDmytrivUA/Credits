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
        System.out.println("Вивід пропозицій:");
        Credits credits = new Credits();
        CreditInfo[] creditInfo = credits.fillBankInfoArray(); // формуємо масив кредитів
        credits.printBankInfo(creditInfo); // дуркуємо усі кредити
        System.out.println('\n');

        //даємо змогу одразу оформити кредит
        System.out.println("Заповнити анкету для оформлення кредиту?");
        System.out.println("1 - Так");
        System.out.println("2 - Ні");
        int confirm = scan.nextInt();
        switch (confirm) {
            case 1:
                creditFile.CreateCreditFile(user); //оформляємо кредит
                break;
            case 2:
                break;
            default:
                System.out.println("Невірний символ, введіть ще раз:");
        }
    }
}
