public class longword_test
{
    public static void main(String[] args) throws Exception
    {
        bit_test.runTests();
        runTests();
    }
    public static void runTests() throws Exception
    {
        System.out.println("LONGWORD UNIT TEST START ----------------------------------------------------------------------------");
        System.out.println("Running Tests:");
        testGetBit();
        testSetBit();
        testAnd();
        testOr();
        testXor();
        testNot();
        testRightShift();
        testLeftShift();
        testToString();
        testGetUnsigned();
        testGetSigned();
        testCopy();
        testSet();
        System.out.println("ALL UNIT TESTING HAS PASSED!");

    }

    public static void testGetBit() throws Exception
    {
        longword test = new longword();
        longword test2 = new longword(true, 31);

        bit output = test.getBit(31);
        bit output2 = test2.getBit(31);

        if(output.getValue() != false) throw new Exception("getBit (false) testing has failed.");
        if(output2.getValue() != true) throw new Exception("getBit (true) testing has failed.");

        System.out.println("\tGetBit Testing has passed.");
    }

    public static void testSetBit() throws Exception
    {
        //Should be all false
        longword test = new longword();
        //Should be one true
        longword test2 = new longword(true, 31);

        test.setBit(31, new bit(true));
        test2.setBit(31, new bit(false));

        if(test.getBit(31).getValue() != true) throw new Exception("setValue (true) testing has failed.");
        if(test2.getBit(31).getValue() != false) throw new Exception("setValue (false) testing has failed.");

        System.out.println("\tSetBit Testing has passed");
    }

    public static void testAnd() throws Exception
    {
        longword test = new longword();
        longword test2 = new longword();
        test2.setAllOnes();

        //0's and 1's
        longword output = test.and(test2); //expect all 0's
        longword output2 = test2.and(test); //expect all 0's

        boolean check0 = true;

        for(int i = 0; i < 32; i++)
        {
            if(output.getBit(i).getValue() == true || output2.getBit(i).getValue() == true)
            {
                check0 = false;
            }
        }

        if(check0 == false) throw new Exception("and testing (0's and 1's) has failed.");


        longword test3 = new longword();
        longword test4 = new longword();

        test3.setAllOnes();
        test4.setAllOnes();

        //1's and 1's
        longword output3 = test3.and(test4); //expect all 1's
        longword output4 = test4.and(test3); //expect all 1's

        boolean check1 = true;

        for(int i = 0; i < 32; i++)
        {
            if(output3.getBit(i).getValue() == false || output4.getBit(i).getValue() == false)
            {
                check1 = false;
            }
        }

        if(check1 == false) throw new Exception("and testing (1's and 1's) has failed.");

        //0's and 0's

        longword test5 = new longword();
        longword test6 = new longword();

        longword output5 = test5.and(test6);//expect 0's
        longword output6 = test6.and(test5);//expect 0's

        boolean check02 = true;

        for(int i = 0; i < 32; i++)
        {
            if(output5.getBit(i).getValue() == true || output6.getBit(i).getValue() == true)
            {
                check02 = false;
            }
        }

        if(check02 == false) throw new Exception("and testing (0's and 0's) has failed.");

        System.out.println("\tAnd testing has passed.");
    }

    public static void testOr() throws Exception
    {
        longword test = new longword();
        longword test2 = new longword();
        test2.setAllOnes();

        //0's and 1's
        longword output = test.or(test2); //expect all 1's
        longword output2 = test2.or(test); //expect all 1's

        boolean check0 = true;

        for(int i = 0; i < 32; i++)
        {
            if(output.getBit(i).getValue() == false || output2.getBit(i).getValue() == false)
            {
                check0 = false;
            }
        }

        if(check0 == false) throw new Exception("or testing (0's and 1's) has failed.");


        longword test3 = new longword();
        longword test4 = new longword();

        test3.setAllOnes();
        test4.setAllOnes();

        //1's and 1's
        longword output3 = test3.or(test4); //expect all 1's
        longword output4 = test4.or(test3); //expect all 1's

        boolean check1 = true;

        for(int i = 0; i < 32; i++)
        {
            if(output3.getBit(i).getValue() == false || output4.getBit(i).getValue() == false)
            {
                check1 = false;
            }
        }

        if(check1 == false) throw new Exception("or testing (1's and 1's) has failed.");

        //0's and 0's

        longword test5 = new longword();
        longword test6 = new longword();

        longword output5 = test5.or(test6);//expect 0
        longword output6 = test6.or(test5);//expect 0

        boolean check02 = true;

        for(int i = 0; i < 32; i++)
        {
            if(output5.getBit(i).getValue() == true || output6.getBit(i).getValue() == true)
            {
                check02 = false;
            }
        }

        if(check02 == false) throw new Exception("or testing (0's and 0's) has failed.");

        System.out.println("\tOr testing has passed.");
    }

    public static void testXor() throws Exception
    {
        longword test = new longword();
        longword test2 = new longword();

        test2.setAllOnes();

        //0's and 1's
        longword output = test.xor(test2); //expect all 1's
        longword output2 = test2.xor(test); //expect all 1's

        boolean check0 = true;

        for(int i = 0; i < 32; i++)
        {
            if(output.getBit(i).getValue() == false || output2.getBit(i).getValue() == false)
            {
                check0 = false;
            }
        }

        if(check0 == false) throw new Exception("xor testing (0's and 1's) has failed.");


        longword test3 = new longword();
        longword test4 = new longword();

        test3.setAllOnes();
        test4.setAllOnes();

        //1's and 1's
        longword output3 = test3.xor(test4); //expect all 0's
        longword output4 = test4.xor(test3); //expect all 0's

        boolean check1 = true;

        for(int i = 0; i < 32; i++)
        {
            if(output3.getBit(i).getValue() == true || output4.getBit(i).getValue() == true)
            {
                check1 = false;
            }
        }

        if(check1 == false) throw new Exception("xor testing (1's and 1's) has failed.");

        //0's and 0's

        longword test5 = new longword();
        longword test6 = new longword();

        longword output5 = test5.or(test6);//expect 0
        longword output6 = test6.or(test5);//expect 0

        boolean check02 = true;

        for(int i = 0; i < 32; i++)
        {
            if(output5.getBit(i).getValue() == true || output6.getBit(i).getValue() == true)
            {
                check02 = false;
            }
        }

        if(check02 == false) throw new Exception("xor testing (0's and 0's) has failed.");

        System.out.println("\tXor testing has passed.");
    }

    public static void testNot() throws Exception
    {
        longword test1 = new longword(true);
        longword test2 = new longword();

        longword output1 = test1.not();//expect all 0's
        longword output2 = test2.not();//expect all 1's

        boolean check = true;

        for(int i = 0; i < 32; i++)
        {
            if(output1.getBit(i).getValue() == true || output2.getBit(i).getValue() == false)
            {
                check = false;
            }
        }

        if(check == false) throw new Exception("not Testing has failed.");

        System.out.println("\tNot Testing has passed.");

    }

    public static void testRightShift() throws Exception
    {
        longword test = new longword();

        test.set(15); //1111

        longword test1 = test.rightShift(2); // 3
        longword test2 = test.rightShift(4); // 0

        if(test1.getUnsigned() != 3) throw new Exception("rightShift testing has failed.");
        if(test2.getUnsigned() != 0) throw new Exception("rightShift testing has failed.");

        System.out.println("\trightShift testing has passed.");
    }

    public static void testLeftShift() throws Exception
    {
        longword test = new longword();

        test.set(15); // 1111

        longword test1 = test.leftShift(2);
        longword test2 = test.leftShift(4);

        if(test1.getUnsigned() != 60) throw new Exception("leftShift testing has failed.");
        if(test2.getUnsigned() != 240) throw new Exception("leftShift testing has failed.");

        System.out.println("\tleftShift testing has passed.");
    }

    public static void testToString() throws Exception
    {
        String expected = "f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,t,f,f";

        longword test = new longword();
        test.set(4);

        if(test.toString().equals(expected) == false) throw new Exception("toString testing has failed.");

        System.out.println("\ttoString testing has passed.");
    }

    public static void testGetUnsigned() throws Exception
    {
        int testNum1 = 0;
        int testNum2 = 1;
        int testNum3 = 154004;

        longword test1 = new longword();
        longword test2 = new longword();
        longword test3 = new longword();

        test1.set(testNum1);
        test2.set(testNum2);
        test3.set(testNum3);

        long output1 = test1.getUnsigned();
        long output2 = test2.getUnsigned();
        long output3 = test3.getUnsigned();

        if(output1 != testNum1) throw new Exception("getUnsigned testing has failed.");
        if(output2 != testNum2) throw new Exception("getUnsigned testing has failed.");
        if(output3 != testNum3) throw new Exception("getUnsigned testing has failed.");

        System.out.println("\tgetUnsigned testing has passed.");
    }

    public static void testGetSigned() throws Exception
    {
        int testNum1 = 0;
        int testNum2 = 1;
        int testNum3 = -1;
        int testNum4 = 154004;
        int testNum5 = -59424;

        longword test1 = new longword();
        longword test2 = new longword();
        longword test3 = new longword();
        longword test4 = new longword();
        longword test5 = new longword();

        test1.set(testNum1);
        test2.set(testNum2);
        test3.set(testNum3);
        test4.set(testNum4);
        test5.set(testNum5);

        long output1 = test1.getSigned();
        long output2 = test2.getSigned();
        long output3 = test3.getSigned();
        long output4 = test4.getSigned();
        long output5 = test5.getSigned();

        if(output1 != testNum1) throw new Exception("getSigned testing has failed.");
        if(output2 != testNum2) throw new Exception("getSigned testing has failed.");
        if(output3 != testNum3) throw new Exception("getSigned testing has failed.");
        if(output4 != testNum4) throw new Exception("getSigned testing has failed.");
        if(output5 != testNum5) throw new Exception("getSigned testing has failed.");

        System.out.println("\tgetSigned testing has passed.");
    }

    public static void testCopy() throws Exception
    {
        longword test = new longword();
        longword test2 = new longword();

        test2.set(5);
        test.copy(test2);

        String output = test.zeroOnes();
        String expected = "00000000000000000000000000000101";

        if(output.equals(expected) == false) throw new Exception("copy testing has failed.");

        System.out.println("\tcopy Testing has passed.");
    }

    public static void testSet() throws Exception
    {
        longword test = new longword();

        int num1 = 4;

        test.set(4);

        int output = (int)test.getUnsigned();

        if(output != num1) throw new Exception("set testing has failed.");

        System.out.println("\tset Testing has passed.");
    }
}

