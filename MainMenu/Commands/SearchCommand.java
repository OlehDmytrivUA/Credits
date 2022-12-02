package MainMenu.Commands;

import Data.User;
import MainMenu.Commands.SearchMenu.SearchMenu;
import MainMenu.MenuItem;

import java.io.IOException;
import java.util.Scanner;

public class SearchCommand implements MenuItem{ //клас команди пошуку
    SearchMenu Menu = new SearchMenu();
    String command;
    Scanner scan = new Scanner(System.in);
    @Override
    public void execute(User user) throws IOException {
        int ret = -1;
        while(true) {  // цикл меню пошуку
            System.out.println("Виконання пошуку по:");
            System.out.println("1 - Виконання пошуку по відсотках у банку");
            System.out.println("2 - Виконання пошуку по терміну кредиту");
            System.out.println("3 - Виконання пошуку по сумі кредиту");
            System.out.println("4 - Повернутися до головного меню");

            System.out.println("Виберіть пункт меню");
            command = scan.next();
            ret = Menu.execute(command,user);
            if(ret == 0){ // якщо повернути 0 вийти з меню пошуку(циклу)
                return;
            }
        }
    }
}
