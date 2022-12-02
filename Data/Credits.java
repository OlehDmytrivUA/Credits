package Data;

public class Credits { //клас кредитів
    public void printBankInfo(CreditInfo[] bi) { // виводить усі кредити з масиву
        for (CreditInfo creditInfo : bi) {
            System.out.println(creditInfo);
        }
    }

    public CreditInfo[] fillBankInfoArray() {  // ініціалізує масив кредитів
        return new CreditInfo[]{
                new CreditInfo("1", "Райффайзен Банк Аваль", "Кредит готівкою \"Акційні умови\"(дохід від 30 тис.грн./міс.)", 150000, 500000, 12, 72, 21.6),
                new CreditInfo("2", "Idea Bank", "Кредит готівкою \"Онлайн кредит\"", 1000, 50000, 12, 36, 20),
                new CreditInfo("3", "Альфа-Банк Україна", "Кредит готівкою", 500000, 1000000, 1, 48, 27),
                new CreditInfo("4", "ПУМБ", "Кредит на загальні цілі \"ВСЕЯСНО\"", 50000, 300000, 1, 36, 35.89),
                new CreditInfo("5", "ОТП Банк", "Кредит готівкою", 200000, 750000, 6, 84, 36),
                new CreditInfo("6", "Укргазбанк", "Eко оселя", 1000, 300000, 1, 60, 24.5),
                new CreditInfo("7", "Ocean", "Ocean Credit", 10000, 200000, 1, 36, 30),
                new CreditInfo("8", "KredoBank", "Готівка VIP+", 100000, 500000, 6, 60, 35),
                new CreditInfo("9", "Сredit Agricole", "Кредит готівкою \"Свобода\"", 200000, 300000, 3, 72, 12)
        };
    }
}

