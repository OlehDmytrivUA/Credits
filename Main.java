import Data.User;
import MainMenu.MainMenu;
import WriteFile.FileActions;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args)  {
        Scanner scan = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu();

        User user = LoginUser(); //авторизація

        while (true) {  //цикл основного меню
            System.out.println("    \n--Menu--\n1 - Пропозиції банків");
            System.out.println("2 - Пошук кредитів");
            System.out.println("3 - Мої кредити");
            System.out.println("4 - Місячна сплата по кредиту");
            System.out.println("5 - Дострокове погашення кредиту");
            System.out.println("6 - Збільшення кредитної лінії");
            System.out.println("7 - Вихід");

            System.out.print("Введіть пункт меню:");
            String command = scan.next();
            mainMenu.execute(command, user);// передаю команду і дані користувача
        }
    }

    private static User LoginUser(){
        Scanner scan = new Scanner(System.in);
        FileActions fileActions = new FileActions();
        User user = new User();
        int choice = -1;

        while (true) { // аторизуватися або створити акаунт
            try {
                System.out.print("Log-in (1) or sing-up? (2)\nChoice:");
                choice = scan.nextInt();
            }catch (InputMismatchException ex){
                System.out.println("TryAgain");
                scan.next();
                continue;
            }
            if(choice==1 || choice==2){
                break;
            }
            System.out.println("TryAgain");
            scan.next();
        }

        if (choice == 1) {  // якщо авторизуватися
            while (true) {      // цикл авторизації
                System.out.print("\tPlease log-in\nUserName:");
                String username = scan.next();
                System.out.print("Password:");
                String password = scan.next();

                File file;
                file = new File("C:\\CreditData\\Users\\" + username + "_" + password + ".txt");

                if (!file.exists()) { // якщо файл не знайдено , тобто не має даного користувача
                    System.out.print("There no account find\nCreateAcount-1\nTry Again-2\nExit-0\n");
                    while (true) {
                        try {
                            choice = scan.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("incorrect choice\n");
                            System.out.print("CreateAcount-1\nTry Again-2\nExit-0\n");
                            scan.next();
                            continue;
                        }
                        switch (choice) {
                            case 1:
                                SingUpUser();  //створити акаунт
                                break;
                            case 2:
                                break;         //спробувати знову авторизуватися
                            case 0:
                                System.exit(0);  // завершити роботу програми
                            default:
                                System.out.print("Incorect choice\n");
                                System.out.print("CreateAcount-1\nTry Again-2\nExit-0\n");
                                continue;
                        }
                        break;
                    }
                }else {         // якщо файл існує , тобто користувач зареєстрований
                    try {
                        String UserData = fileActions.readFile("C:\\CreditData\\Users\\" + username + "_" + password + ".txt");
                        String[] lines = UserData.split("\n");
                        for (int i = 0; i < lines.length; i++) {                    //цикл зчитування даних користувача з
                            if(lines[i].startsWith("FirstName")){                           // файлу для використання
                                user.setFirstName(lines[i+1].replace("\r", ""));
                                continue;
                            }
                            if(lines[i].startsWith("LastName")){
                                user.setLastName(lines[i+1].replace("\r", ""));
                                continue;
                            }
                            if(lines[i].startsWith("CreditCard")){
                                user.setCreditCard(lines[i+1].replace("\r", ""));
                                continue;
                            }
                            if(lines[i].startsWith("UserName")){
                                user.setUserName(lines[i+1].replace("\r", ""));
                                continue;
                            }
                            if(lines[i].startsWith("PassWord")){
                                user.setPassword(lines[i+1].replace("\r", ""));
                            }
                        }
                    }
                    catch (IOException ex){
                        System.out.println("Проблеми з отриманням даних про користувача");
                    }
                    break;
                }
            }
        } else if (choice == 2) {  // якщо зареєстурватися
            SingUpUser();
        }
        return user;
    }

    private static void SingUpUser(){
        Scanner scan = new Scanner(System.in);
        Pattern CreditCardPattern = Pattern.compile("^[0-9]{4}+-[0-9]{4}+-[0-9]{4}+-[0-9]{4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        File file = null;

        while (true) {   // цикл реєстрації
            System.out.print("FirstName:");
            String FirstName = scan.next();
            System.out.print("LastName:");
            String LastName = scan.next();
            System.out.print("CreditCard:");
            String CreditCard = scan.next();
            System.out.print("UserName:");
            String UserName = scan.next();
            System.out.print("PassWord:");
            String PassWord = scan.next();
            matcher = CreditCardPattern.matcher(CreditCard);

            if (matcher.matches()) {  // якщо правильний формат карточки
                file = new File("C:\\CreditData\\Users\\" + UserName + "_" + PassWord + ".txt");
            } else {
                System.out.println("Incorrect Credit card (xxxx-xxxx-xxxx-xxxx)");
                continue;
            }
            if (file.exists()) {  // якщо такий користувач уже існує
                System.out.println("This UserName is already taken, try again");
            }else {
                try {
                    file.createNewFile();  // створення файлу користувач
                }catch (IOException ex){
                    System.out.println("Не вдалося зареєструвати користувача");
                }
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write("FirstName:\n");    // запис даних у файл
                    writer.write(FirstName);
                    writer.write("\nLastName:\n");
                    writer.write(LastName);
                    writer.write("\nCreditCard:\n");
                    writer.write(CreditCard);
                    writer.write("\nUserName:\n");
                    writer.write(UserName);
                    writer.write("\nPassWord:\n");
                    writer.write(PassWord);
                    writer.write("\nCredits:\n");
                    writer.close();
                    break;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        LoginUser();  // повернення до авторизації
    }
}

