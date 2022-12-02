package MainMenu.Commands.SearchMenu.SearchCommands;

import Data.CreditInfo;
import Data.Credits;
import Data.User;
import MainMenu.Commands.SearchMenu.SearchMenuItem;
import WriteFile.CreditFile;

import java.io.IOException;
import java.util.Scanner;

public class SearchCreditTermCommand implements SearchMenuItem {
    Scanner in = new Scanner(System.in);
    Credits credits = new Credits();
    CreditInfo[] CredInf = credits.fillBankInfoArray();
    CreditFile creditfile = new CreditFile();
    private int count = 0;
    @Override
    public int execute(User user) throws IOException {
        System.out.println("Виконання пошуку по терміну кредиту");
        System.out.println("Введіть термін кредиту(в місяцях):");
            // вводимо потрібний термін
        int creditTerm = in.nextInt();

        for (CreditInfo creditInfo : CredInf) {
                // виводимо відповідні кредити
            if (creditTerm >= creditInfo.getCreditTermMin() && creditTerm <= creditInfo.getCreditTermMax()) {
                System.out.println(creditInfo);
                count++;
            }
        }
        if (count == 0) System.out.println("Немає такого кредиту");

            //Можливість створити кредит пісял пошуку
        System.out.println("Заповнити анкету для оформлення кредиту?");
        System.out.println("1 - Так");
        System.out.println("2 - Ні");
        int confirm = in.nextInt();
        switch (confirm) {
            case 1:
                creditfile.CreateCreditFile(user); // створення кредиту
                break;
            case 2:
                break;
            default:
                System.out.println("Невірний символ, введіть ще раз:");
        }
        return 1;
    }
}
