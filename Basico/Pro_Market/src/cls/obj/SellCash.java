package cls.obj;

public class SellCash {
    int cod;
    String product;
    int amount;
    double PriceUnit;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPriceUnit() {
        return PriceUnit;
    }

    public void setPriceUnit(double priceUnit) {
        PriceUnit = priceUnit;
    }
}
