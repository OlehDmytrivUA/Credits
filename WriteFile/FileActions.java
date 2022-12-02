package WriteFile;
import java.io.*;

public class FileActions {
    public String readFile(String fileName) throws IOException { //метод що читає файл і повертає string
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while (true) {
            if (!((line = reader.readLine()) != null)) break;
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        reader.close();
        String content = stringBuilder.toString();
        return content;
    }

    public void MonthlyPayment(String filepath) throws IOException { // метод для місячної сплати по кредиту
        String data = readFile(filepath); //зчиутємо файл кредиту
        String[] DataLines = data.split("\n", -1); // формуємо масив рядків
        int  Term = 0;
        double Sum = 0, MonthlyPayment = 0, OverPayment = 0, Percent = 0;

            //якщо сплачено
        if (DataLines[1].replace("\r", "").compareTo("СПЛАЧЕНО") == 0) {
            System.out.println("\nЦей Кредит уже сплачено\n");
            return;
        }
            //зчитуємо дані про кредит
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("Залишилось")){
                Sum = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("Місяців")){
                Term = Integer.parseInt(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("Щомісячна")){
                MonthlyPayment = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
        }

            //оновлюємо дані
        Term--;
        Sum-=MonthlyPayment;

        if(Sum<1){ //якщо сплатили
            DataLines[1] = "СПЛАЧЕНО\n";
        }
            //заносимо нові дані у масив
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("Залишилось")){
                DataLines[i+1] = Double.toString(Sum) + "\n";
            }
            if(DataLines[i].startsWith("Місяців")){
                DataLines[i+1] = Term + "\n";
            }
        }
            //записуємо у файл
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (String dataLine : DataLines) {
            writer.write(dataLine);
        }
        writer.close();
    }

        //метод дострокової сплати по кредиту
        //можна сплати повністю , або менше 40% від суми що залишилась
    public void earlyLoanRepayment(String filepath, double Payment) throws IOException {
        String data = readFile(filepath); //зчиутємо файл кредиту
        String[] DataLines = data.split("\n", -1); // формуємо масив рядків

        double limit = 0.4; //40% ліміт дострокової сплати

        int  Term = 0;
        double Sum = 0, MonthlyPayment = 0, OverPayment = 0, Percent = 0;
            //якщо уже сплачено
        if (DataLines[1].replace("\r", "").compareTo("СПЛАЧЕНО") == 0) {
            System.out.println("\nЦей Кредит уже сплачено\n");
            return;
        }
            //зчитуємо дані з масиву
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("Залишилось")){
                Sum = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("Місяців")){
                Term = Integer.parseInt(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("Переплата")){
                OverPayment = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
        }
            //обраховую відсоток вручну
        Percent = (OverPayment * 100 * 12) / ((Sum - OverPayment) * Term);

            //сума завелика
        if (Sum < Payment) {
            System.out.println("Сума дострокової оплати занадто велика!\n"+
                    "Вона повинна складати не більше " + limit*100 + "% від суми що залишилась(" + (int)Sum*limit + ")");
            return;
        }
            //якщо оплачується повністю
        if (Sum <= Payment+1 && Sum>=Payment) {
            DataLines[1] = "СПЛАЧЕНО\n";
            Sum = 0;
        }else
            //якщо оплата більше 40% але не повна
            if(Sum<Payment/limit){
            System.out.println("Сума дострокової оплати занадто велика!\n"+
                    "Вона повинна складати не більше " + limit*100 + "% від суми що залишилась(" + (int)Sum*limit + ")");
            return;
        }
                //якщо оплата менше 40% обраховуєм нові дані
            else{
            Sum -= Payment;
            OverPayment = Sum - Sum / (1 + (Percent * Term / 1200));
            MonthlyPayment = Sum / Term;
        }

            //оноввлюємо дані у масиві
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("Залишилось")){
                DataLines[i+1] = Double.toString(Sum) + "\n";
            }
            if(DataLines[i].startsWith("Місяців")){
                DataLines[i+1] = Term + "\n";
            }
            if(DataLines[i].startsWith("Щомісячна")){
                DataLines[i+1] = MonthlyPayment + "\n";
            }
            if(DataLines[i].startsWith("Переплата")){
                DataLines[i+1] = OverPayment + "\n";
            }
        }

            //записуємо оновлені дані у файл
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (String dataLine : DataLines) {
            writer.write(dataLine);
        }
        writer.close();

    }

        //метод збільшення кредитної лінії
    public void increaseCreditLine(String filepath , int Months) throws IOException {
        String data = readFile(filepath); //зчиутємо файл кредиту
        String[] DataLines = data.split("\n", -1); // формуємо масив рядків

        int  Term = 0;
        double Sum = 0, MonthlyPayment = 0, OverPayment = 0, Percent = 0;

            //якщо кредит сплачено
        if (DataLines[1].replace("\r", "").compareTo("СПЛАЧЕНО") == 0) {
            System.out.println("\nЦей Кредит уже сплачено\n");
            return;
        }
            //зчитуємо дані з масиву
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("Залишилось")){
                Sum = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("Місяців")){
                Term = Integer.parseInt(DataLines[i+1].replace("\r", ""));
            }
            if(DataLines[i].startsWith("Переплата")){
                OverPayment = Double.parseDouble(DataLines[i+1].replace("\r", ""));
            }
        }
            //Збільшуємо кредитну лінію і обчислюємо нові дані
        Percent = (OverPayment * 100 * 12) / ((Sum - OverPayment) * Term);
        Term+=Months;
        OverPayment = Sum - Sum / (1 + (Percent * Term / 1200));
        MonthlyPayment = Sum / Term;

            //оновлюмо дані у масиві радків
        for (int i = 0; i < DataLines.length; i++) {
            if(DataLines[i].startsWith("Залишилось")){
                DataLines[i+1] = Sum + "\n";
            }
            if(DataLines[i].startsWith("Місяців")){
                DataLines[i+1] = Term + "\n";
            }
            if(DataLines[i].startsWith("Щомісячна")){
                DataLines[i+1] = MonthlyPayment + "\n";
            }
            if(DataLines[i].startsWith("Переплата")){
                DataLines[i+1] = OverPayment + "\n";
            }
        }
            //записуємо дані у файл
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        for (String dataLine : DataLines) {
            writer.write(dataLine);
        }
        writer.close();
    }

}

