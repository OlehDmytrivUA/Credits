package Data;

public class Credits { //���� �������
    public void printBankInfo(CreditInfo[] bi) { // �������� �� ������� � ������
        for (CreditInfo creditInfo : bi) {
            System.out.println(creditInfo);
        }
    }

    public CreditInfo[] fillBankInfoArray() {  // �������� ����� �������
        return new CreditInfo[]{
                new CreditInfo("1", "���������� ���� �����", "������ ������� \"������ �����\"(����� �� 30 ���.���./��.)", 150000, 500000, 12, 72, 21.6),
                new CreditInfo("2", "Idea Bank", "������ ������� \"������ ������\"", 1000, 50000, 12, 36, 20),
                new CreditInfo("3", "�����-���� ������", "������ �������", 500000, 1000000, 1, 48, 27),
                new CreditInfo("4", "����", "������ �� ������� ��� \"�������\"", 50000, 300000, 1, 36, 35.89),
                new CreditInfo("5", "��� ����", "������ �������", 200000, 750000, 6, 84, 36),
                new CreditInfo("6", "����������", "E�� �����", 1000, 300000, 1, 60, 24.5),
                new CreditInfo("7", "Ocean", "Ocean Credit", 10000, 200000, 1, 36, 30),
                new CreditInfo("8", "KredoBank", "������ VIP+", 100000, 500000, 6, 60, 35),
                new CreditInfo("9", "�redit Agricole", "������ ������� \"�������\"", 200000, 300000, 3, 72, 12)
        };
    }
}

