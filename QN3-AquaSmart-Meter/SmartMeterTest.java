public class SmartMeterTest {
    public static void main(String[] args) {
        testLoadTokenReopensValve();
        testConsumptionExceedingCreditClosesValve();
        System.out.println("All tests passed.");
    }

    private static void assertTrue(boolean cond, String msg) {
        if (!cond) throw new AssertionError(msg);
    }

    private static void assertFalse(boolean cond, String msg) {
        if (cond) throw new AssertionError(msg);
    }

    private static void assertEquals(double expected, double actual, double eps) {
        if (Math.abs(expected - actual) > eps) throw new AssertionError("Expected " + expected + " but was " + actual);
    }

    public static void testLoadTokenReopensValve() {
        SmartMeter meter = new SmartMeter("M101", 0.0);
        assertFalse(meter.isValveOpen(), "Valve should be closed initially with 0 balance");

        meter.loadToken(2000.0);
        assertTrue(meter.isValveOpen(), "Loading a token must re-open a closed valve");
    }

    public static void testConsumptionExceedingCreditClosesValve() {
        SmartMeter meter = new SmartMeter("M102", 100.0);
        assertTrue(meter.isValveOpen(), "Valve should be open with initial credit");

        boolean dispensed = meter.recordConsumption(3.0);
        assertTrue(dispensed, "Water should dispense for this transaction execution loop");
        assertEquals(0.0, meter.getCreditBalance(), 0.001);
        assertFalse(meter.isValveOpen(), "Consumption beyond available credit must close the valve");
    }
}