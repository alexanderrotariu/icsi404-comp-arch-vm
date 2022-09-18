public class rippleAdder_test
{
    public static void main(String[] args ) throws Exception
    {
        //Run all previous tests
        bit_test.runTests();
        longword_test.runTests();
        runTests();

    }

    public static void runTests() throws Exception
    {
        //Run testAdd and testSubtract
        System.out.println("RIPPLE ADDER UNIT TEST START ----------------------------------------------------------------------------");
        testAdd();
        testSubtract();
        System.out.println("ALL UNIT TESTING HAS PASSED!");
    }

    public static void testAdd() throws Exception
    {
        longword num1 = new longword();
        num1.set(100);
        longword num2 = new longword();
        num2.set(250);
        longword num3 = new longword();
        num3.set(-50);
        longword num4 = new longword();
        num4.set(-100);

        int output1 = rippleAdder.add(num1, num2).getSigned();//350
        int output2 = rippleAdder.add(num2, num3).getSigned();//200
        int output3 = rippleAdder.add(num3, num4).getSigned();//-150
        int output4 = rippleAdder.add(num2, num1).getSigned();//350
        int output5 = rippleAdder.add(num4, num1).getSigned();//0

        //If mismatch between any expected values, throw an exception
        if(output1 != 350) throw new Exception("add testing has failed.");
        if(output2 != 200) throw new Exception("add testing has failed.");
        if(output3 != -150) throw new Exception("add testing has failed.");
        if(output4 != 350) throw new Exception("add testing has failed.");
        if(output5 != 0) throw new Exception("add testing has failed.");

        System.out.println("\tAdd Testing has passed.");

    }

    public static void testSubtract() throws Exception
    {
        longword num1 = new longword();
        num1.set(100);
        longword num2 = new longword();
        num2.set(250);
        longword num3 = new longword();
        num3.set(-50);
        longword num4 = new longword();
        num4.set(-100);

        //If mismatch between any expected values, throw an exception
        int output1 = rippleAdder.subtract(num1, num2).getSigned();//-150
        int output2 = rippleAdder.subtract(num2, num3).getSigned();//300
        int output3 = rippleAdder.subtract(num3, num4).getSigned();//50
        int output4 = rippleAdder.subtract(num2, num1).getSigned();//150
        int output5 = rippleAdder.subtract(num4, num1).getSigned();//200

        if(output1 != -150) throw new Exception("subtract testing has failed.");
        if(output2 != 300) throw new Exception("subtract testing has failed.");
        if(output3 != 50) throw new Exception("subtract testing has failed.");
        if(output4 != 150) throw new Exception("subtract testing has failed.");
        if(output5 != -200) throw new Exception("subtract testing has failed.");

        System.out.println("\tSubtract Testing has passed.");
    }
}
