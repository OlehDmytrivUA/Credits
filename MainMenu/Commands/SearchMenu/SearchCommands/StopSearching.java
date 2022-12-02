package MainMenu.Commands.SearchMenu.SearchCommands;
import Data.User;
import MainMenu.Commands.SearchMenu.SearchMenuItem;

public class    StopSearching implements SearchMenuItem {
    @Override
    public int execute(User user) {
        return 0;
    } //завершити пошук
}
