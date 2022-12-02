package MainMenu.Commands.SearchMenu;
import Data.User;
import MainMenu.Commands.SearchMenu.SearchCommands.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SearchMenu {
    private Map<String, SearchMenuItem> MenuItems;

    public SearchMenu(){
        MenuItems = new LinkedHashMap<>();
        MenuItems.put("1",new SearchPercentCommand());
        MenuItems.put("2",new SearchCreditTermCommand());
        MenuItems.put("3",new SearchCreditSumCommand());
        MenuItems.put("4",new StopSearching());
        // мапа класів пошуку
    }

    public int execute(String command, User user) throws IOException {
        int result = -1;
        if(MenuItems.get(command) != null){
            result = MenuItems.get(command).execute(user); // виклик відповідної команди
            if(result == 0){
                return 0;
            };
        }
        return 1;
    }
 }
