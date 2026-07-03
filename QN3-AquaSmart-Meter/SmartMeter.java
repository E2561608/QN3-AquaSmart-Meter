public class SmartMeter {
    private String meterId;
    private double creditBalance;
    private boolean valveOpen;

    public SmartMeter(String meterId, double openingCreditBalance) {
        this.meterId = meterId;
        this.creditBalance = openingCreditBalance;
        this.valveOpen = openingCreditBalance > 0;
    }

    public double loadToken(double amount) {
        if (amount > 0) {
            this.creditBalance += amount;
            this.valveOpen = true;
        }
        return this.creditBalance;
    }

    public boolean recordConsumption(double litres) {
        if (!valveOpen) {
            return false;
        }
        
        double cost = litres * 50.0; // UGX 50 per litre
        this.creditBalance -= cost;

        if (this.creditBalance <= 0) {
            this.creditBalance = 0.0;
            this.valveOpen = false;
        }
        return true;
    }

    // Getters 
    public double getCreditBalance() { return creditBalance; }
    public boolean isValveOpen() { return valveOpen; }
}