package MainMenu;
import Data.User;
import MainMenu.Commands.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenu {
    private Map<String, MenuItem> MenuItems;

    public MainMenu(){  // конструктор що ініціалізує мапу класів команд
        MenuItems = new LinkedHashMap<>();
        MenuItems.put("1",new DisplayCreditsCommand());
        MenuItems.put("2",new SearchCommand());
        MenuItems.put("3",new MyCreditsCommand());
        MenuItems.put("4",new MonthlyPaymentCommand());
        MenuItems.put("5",new EarlyLoanRepaymentCommand());
        MenuItems.put("6",new IncreaseCreditLineCommand());
        MenuItems.put("7",new ExitCommand());
        //пункти меню (класи з відповідними методами execute) у вигляді мапи
    }

    public void execute(String command, User user) {  // метод що викликає потрбний метод команди
        if(MenuItems.get(command) != null){
            try {
                MenuItems.get(command).execute(user); // викликаємо відповідну команду і передаєм user
            }catch (IOException | SQLException | ClassNotFoundException ex){
                System.out.println("Не вдалося виконати команду");
            }
        }
    }
}
