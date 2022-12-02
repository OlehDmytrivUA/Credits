package MainMenu.Commands;
import Data.User;
import MainMenu.MenuItem;
public class ExitCommand implements MenuItem {
    @Override
    public void execute(User user) {
        System.exit(0);
    } // заверхуємо роботу програми
}
