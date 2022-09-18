public class multiplier_test
{
    public static void main(String [] args) throws Exception
    {
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        runTests();
    }

    public static void runTests() throws Exception
    {
        System.out.println("MULTIPLIER UNIT TEST START ----------------------------------------------------------------------------");
        testMultiply();
        System.out.println("ALL UNIT TESTING HAS PASSED!");
    }
    public static void testMultiply() throws Exception
    {
        longword num1 = new longword();
        num1.set(3);
        longword num2 = new longword();
        num2.set(3);

        longword num3 = new longword();
        num3.set(-2);
        longword num4 = new longword();
        num4.set(5);

        longword num5 = new longword();
        num5.set(-7);
        longword num6 = new longword();
        num6.set(-2);

        longword num7 = new longword();
        num7.set(8);
        longword num8 = new longword();
        num8.set(-1);

        int output1 = multiplier.multiply(num1, num2).getSigned();
        int output2 = multiplier.multiply(num3, num4).getSigned();
        int output3 = multiplier.multiply(num5, num6).getSigned();
        int output4 = multiplier.multiply(num7, num8).getSigned();

        //If mismatch between any expected values, throw an exception
        if(output1 != 9) throw new Exception("multiplier testing has failed.");
        if(output2 != -10) throw new Exception("multiplier testing has failed.");
        if(output3 != 14) throw new Exception("multiplier testing has failed.");
        if(output4 != -8) throw new Exception("multiplier testing has failed.");

        System.out.println("\tAdd Testing has passed.");
    }
}
