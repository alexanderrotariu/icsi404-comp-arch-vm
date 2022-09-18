public class bit_test
{
    public static void main(String[] args) throws Exception
    {
        runTests();
    }

    public static void runTests() throws Exception
    {
        System.out.println("UNIT TEST START ----------------------------------------------------------------------------");
        System.out.println("Running Tests:");
        testGetValue();
        testToString();
        testToggle();
        testSet();
        testClear();
        testAnd();
        testOr();
        testXor();
        testNot();
        System.out.println("ALL UNIT TESTING HAS PASSED!");
    }

    public static void testGetValue() throws Exception
    {
        bit bit1 = new bit();
        bit bit2 = new bit(true);

        if(bit1.getValue() != false) throw new Exception("getValue of false has failed.");
        if(bit2.getValue() != true) throw new Exception("getValue of true has failed.");

        System.out.println("\tgetValue testing has passed.");
    }

    public static void testToString() throws Exception
    {
        bit bit1 = new bit();
        bit bit2 = new bit(true);

        if(bit1.toString().equals("f") != true) throw new Exception("toString of false has failed.");
        if(bit2.toString().equals("t") != true) throw new Exception("toString of true has failed.");

        System.out.println("\ttoString testing has passed.");
    }

    public static void testToggle() throws Exception
    {
        bit bit1 = new bit(); //false
        bit bit2 = new bit(true); //true

        bit1.toggle();
        bit2.toggle();

        if(bit1.getValue() != true) throw new Exception("Toggle false to true has failed.");
        if(bit2.getValue() != false) throw new Exception("Toggle true to false has failed");

        System.out.println("\tToggle Testing has passed.");
    }

    public static void testSet() throws Exception
    {
        bit bit1 = new bit(); //false
        bit bit2 = new bit(true); //true

        bit1.set();
        bit2.set();

        if(bit1.getValue() != true) throw new Exception("Setting false to true has failed.");
        if(bit2.getValue() != true) throw new Exception("Setting true to true has failed");

        bit bit3 = new bit(); //false
        bit bit4 = new bit(true); //true

        bit3.set(true);
        bit4.set(false);

        if(bit3.getValue() != true) throw new Exception("Parameterized setting false to true has failed.");
        if(bit4.getValue() != false) throw new Exception("Parameterized setting true to false has failed");

        System.out.println("\tSetting Testing has passed.");
    }

    public static void testClear() throws Exception
    {
        bit bit1 = new bit(); //false
        bit bit2 = new bit(true); //true

        bit1.clear();
        bit2.clear();

        if(bit1.getValue() != false) throw new Exception("Clearing false to false has failed.");
        if(bit2.getValue() != false) throw new Exception("Clearing true to false has failed");

        System.out.println("\tClear Testing has passed.");
    }

    public static void testAnd() throws Exception
    {
        bit bit1 = new bit(true);
        bit bit2 = new bit(true);

        bit outputTT = bit1.and(bit2);

        if(outputTT.getValue() != true) throw new Exception("True & True has failed.");
        //System.out.println("\ttrue & true (expected: true): "+outputTT);

        bit bit3 = new bit(true);
        bit bit4 = new bit(false);

        bit outputTF = bit3.and(bit4);

        if(outputTF.getValue() != false) throw new Exception("True & False has failed.");
        //System.out.println("\ttrue & false (expected: false): "+outputTF);

        bit bit5 = new bit(false);
        bit bit6 = new bit(true);

        bit outputFT = bit5.and(bit6);

        if(outputFT.getValue() != false) throw new Exception("False & True has failed.");
        //System.out.println("\tfalse & true (expected: false): "+outputFT);

        bit bit7 = new bit(false);
        bit bit8 = new bit(false);

        bit outputFF = bit7.and(bit8);

        if(outputFF.getValue() != false) throw new Exception("False & False has failed.");
        //System.out.println("\tfalse & false (expected: false): "+outputFF);

        System.out.println("\tAnd testing passed.");
    }

    public static void testOr() throws Exception
    {
        bit bit1 = new bit(true);
        bit bit2 = new bit(true);

        bit outputTT = bit1.or(bit2);

        if(outputTT.getValue() != true) throw new Exception("True | True has failed.");
        //System.out.println("\ttrue | true (expected: true): "+outputTT);

        bit bit3 = new bit(true);
        bit bit4 = new bit(false);

        bit outputTF = bit3.or(bit4);

        if(outputTF.getValue() != true) throw new Exception("True | False has failed.");
        //System.out.println("\ttrue | false (expected: true): "+outputTF);

        bit bit5 = new bit(false);
        bit bit6 = new bit(true);

        bit outputFT = bit5.or(bit6);

        if(outputFT.getValue() != true) throw new Exception("False | True has failed.");
        //System.out.println("\tfalse | true (expected: true): "+outputFT);

        bit bit7 = new bit(false);
        bit bit8 = new bit(false);

        bit outputFF = bit7.or(bit8);

        if(outputFF.getValue() != false) throw new Exception("False | False has failed.");
        //System.out.println("\tfalse | false (expected: false): "+outputFF);

        System.out.println("\tOr Testing has passed.");
    }

    public static void testXor() throws Exception
    {
        bit bit1 = new bit(true);
        bit bit2 = new bit(true);

        bit outputTT = bit1.xor(bit2);

        if(outputTT.getValue() != false) throw new Exception("True ^ True has failed.");
        //System.out.println("\ttrue ^ true (expected: false): "+outputTT);

        bit bit3 = new bit(true);
        bit bit4 = new bit(false);

        bit outputTF = bit3.xor(bit4);

        if(outputTF.getValue() != true) throw new Exception("True ^ False has failed.");
        //System.out.println("\ttrue ^ false (expected: true): "+outputTF);

        bit bit5 = new bit(false);
        bit bit6 = new bit(true);

        bit outputFT = bit5.xor(bit6);

        if(outputFT.getValue() != true) throw new Exception("False ^ True has failed.");
        //System.out.println("\tfalse ^ true (expected: true): "+outputFT);

        bit bit7 = new bit(false);
        bit bit8 = new bit(false);

        bit outputFF = bit7.xor(bit8);

        if(outputFF.getValue() != false) throw new Exception("False ^ False has failed.");
        //System.out.println("\tfalse ^ false (expected: false): "+outputFF);

        System.out.println("\tXor Testing has passed.");
    }

    public static void testNot() throws Exception
    {
        bit notTest = new bit(); //false
        bit notTest2 = new bit(true); //true

        bit notTestOutput = notTest.not();
        bit notTest2Output =  notTest2.not();

        if(notTestOutput.getValue() != true) throw new Exception("!false has failed.");
        if(notTest2Output.getValue() != false) throw new Exception("!true has failed.");

        System.out.println("\tNot Testing has passed.");
    }




}
