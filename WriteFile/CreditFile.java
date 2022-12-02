package WriteFile;
import Data.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class CreditFile {

    public void CreateCreditFile(User user){ // метод ствоення файлу кредиту
        Scanner scan = new Scanner(System.in);
        Credits credits = new Credits();
        CreditInfo[] CredInf = credits.fillBankInfoArray();
        String creditID, bankName = null, creditName = null;
        int creditSumMin = 0, creditSumMax = 0, creditTermMin = 0, creditTermMax = 0, count = 0;
        double percents = 0.0, monthlyFee, overpayment;
        File credit;
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        System.out.println("Шановний " + user.getFirstName() + " " + user.getLastName() + ", введіть ID кредиту який ви бажаєте обрати:");

        while (true) {
            creditID = scan.next();  // обираємо кредит за ID

            for (CreditInfo creditInfo : CredInf) { // виводимо і зчитуємо дані про обраний кредит
                if (creditInfo.getCreditID().equals(creditID)) {
                    System.out.println(creditInfo);
                    creditID = creditInfo.getCreditID();
                    bankName = creditInfo.getBankName();
                    creditName = creditInfo.getCreditName();
                    creditSumMax = creditInfo.getCreditSumMax();
                    creditSumMin = creditInfo.getCreditSumMin();
                    creditTermMax = creditInfo.getCreditTermMax();
                    creditTermMin = creditInfo.getCreditTermMin();
                    percents = creditInfo.getPercents();
                    count++;
                    break;
                }
            }
            if(count>0){
                break;
            }
            System.out.println("Немає такого ID, спробуйте ще раз: ");
        }

            // вводимо суму кредиту
        System.out.println("Введіть суму кредиту(в гривнях):");
        int sum = scan.nextInt();
        while (sum < creditSumMin || sum > creditSumMax) {
            System.out.println("Сума кредиту надто велика/мала, введіть суму кредиту ще раз: ");
            sum = scan.nextInt();

        }

            //вводимо термін кредиту
        System.out.println("Введіть термін кредиту(в місяцях):");
        int term = scan.nextInt();
        while (term < creditTermMin || term > creditTermMax) {
            System.out.println("Термін кредиту надто великий/малий, введіть термін кредиту ще раз: ");
            term = scan.nextInt();
        }

            // обраховуємо переплпту за кредитом і місячний платіж з урахуванням відсотку річних
        overpayment = (percents*sum*term/12)/100;
        monthlyFee = (sum+overpayment)/term;
        System.out.println("Щомісячна виплата становить: " + (double) ((int) (monthlyFee * 100)) / 100 + "грн\n");
        System.out.println("Переплата по кредиту становить: " + (double) ((int) (overpayment * 100)) / 100 + "грн\n");

            // створюємо файл для кредиту
        Random rand = new Random();
        try {
            int Id = -1;
            while (true) {
                Id = rand.nextInt(100000); // генеруємо ID
                credit = new File("C:\\CreditData\\Credits\\Credit_" + Id + ".txt");
                if (!credit.exists()) {
                    credit.createNewFile();
                    try {  // дописужмо до файлу користувача ID створеного кредиту
                        fw = new FileWriter("C:\\CreditData\\Users\\" + user.getUserName() + "_" + user.getPassword() + ".txt", true);
                        bw = new BufferedWriter(fw);
                        pw = new PrintWriter(bw);
                        pw.println(Id);
                        pw.flush();
                    } finally {
                        try {
                            pw.close();
                            bw.close();
                            fw.close();
                        } catch (IOException io) {
                        }
                    }
                }
                break;
            }

                //формуємо стрінги для файлу
            String CreditID = Integer.toString(Id);
            String SumStr = Double.toString(sum);
            String SumWithOverpaymentStr = Double.toString(sum+overpayment);
            String termStr = Integer.toString(term);
            String creditSumMaxStr = Integer.toString(creditSumMax);
            String creditSumMinStr = Integer.toString(creditSumMin);
            String creditTermMaxStr = Integer.toString(creditTermMax);
            String creditTermMinStr = Integer.toString(creditTermMin);
            String percentsStr = Double.toString(percents);

                // записуємо дані у файл
            System.out.println("ID вашого кредиту - "+Id);
            PrintWriter writer = new PrintWriter(credit);

            writer.append("Кредит:").append(CreditID).append(" |").append(SumStr).append("грн ").append(termStr).append("міс").append("\n\n");

            writer.append("Банк: ").append(bankName).append(" ID: ").append(creditID).append(String.valueOf('\n'));
            writer.append("Назва кредиту: ").append(creditName).append(String.valueOf('\n'));
            writer.append(creditSumMinStr).append(String.valueOf('-')).append(creditSumMaxStr).append("грн  ");
            writer.append(creditTermMinStr).append(String.valueOf('-')).append(creditTermMaxStr).append("міс  ");
            writer.append(percentsStr).append("%\n\n");

            writer.append("Статус\n");
            writer.append("Залишилось сплатити(грн):\n").append(SumWithOverpaymentStr).append("\n");
            writer.append("Місяців:\n").append(termStr).append("\n");
            writer.append("Щомісячна виплата(грн):\n").append(String.valueOf((double) ((int) (monthlyFee * 100)) / 100)).append("\n");
            writer.append("Переплата по кредиту(грн):\n").append(String.valueOf((double) ((int) (overpayment * 100)) / 100)).append("\n\n");

            writer.append("Ім'я: ").append(user.getFirstName()).append(String.valueOf('\n'));
            writer.append("Прізвище: ").append(user.getLastName()).append(String.valueOf('\n'));
            writer.append("Кредитна картка:").append(user.getCreditCard()).append(String.valueOf('\n'));
            writer.append("------------------------------------------------------\n");
            writer.flush();
            writer.close();

            System.out.println("Дані внесено.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

