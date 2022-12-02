package MainMenu.Commands.SearchMenu;
import Data.User;
import java.io.IOException;

public interface SearchMenuItem {
    int execute(User user) throws IOException;
}
