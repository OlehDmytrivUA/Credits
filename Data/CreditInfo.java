package Data;
import java.util.Objects;

public class CreditInfo { // клас окремого кредиту
    private String creditID;
    private String bankName;
    private String creditName;
    private int creditSumMin;
    private int creditSumMax;
    private int creditTermMin;
    private int creditTermMax;
    private double percents;

    public CreditInfo(String creditID, String bankName, String creditName, int creditSumMin, int creditSumMax, int creditTermMin, int creditTermMax, double percents) {
        this.creditID = creditID;
        this.bankName = bankName;
        this.creditName = creditName;
        this.creditSumMin = creditSumMin;
        this.creditSumMax = creditSumMax;
        this.creditTermMin = creditTermMin;
        this.creditTermMax = creditTermMax;
        this.percents = percents;
    }

    public String getCreditID() {
        return creditID;
    }

    public String getBankName() {
        return bankName;
    }

    public String getCreditName() {
        return creditName;
    }

    public int getCreditSumMin() {
        return creditSumMin;
    }

    public int getCreditSumMax() {
        return creditSumMax;
    }

    public int getCreditTermMin() {
        return creditTermMin;
    }

    public int getCreditTermMax() {
        return creditTermMax;
    }

    public double getPercents() {
        return percents;
    }

    @Override
    public String toString() {
        return '\n' +
                "Банк: " + bankName + " ID: " + creditID + '\n' +
                "Назва кредиту: " + creditName + '\n' +
                creditSumMin + '-' + creditSumMax + "грн   " +
                creditTermMin + '-' + creditTermMax + " міс   " +
                percents + '%';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditInfo creditInfo = (CreditInfo) o;
        return creditSumMin == creditInfo.creditSumMin &&
                creditSumMax == creditInfo.creditSumMax &&
                creditTermMin == creditInfo.creditTermMin &&
                creditTermMax == creditInfo.creditTermMax &&
                Double.compare(creditInfo.percents, percents) == 0 &&
                creditID.equals(creditInfo.creditID) &&
                bankName.equals(creditInfo.bankName) &&
                Objects.equals(creditName, creditInfo.creditName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditID, bankName, creditName, creditSumMin, creditSumMax, creditTermMin, creditTermMax, percents);
    }
}
