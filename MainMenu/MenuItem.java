package MainMenu;
import Data.User;
import java.io.IOException;
import java.sql.SQLException;

public interface MenuItem {
        void execute(User user) throws SQLException, ClassNotFoundException, IOException;
}
