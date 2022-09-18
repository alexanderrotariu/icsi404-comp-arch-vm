public class ALU_test
{

    public static void main (String [] args) throws Exception
    {
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        runTests();
    }

    public static void runTests() throws Exception
    {
        System.out.println("ALU UNIT TEST START ----------------------------------------------------------------------------");
        testOps();
        System.out.println("ALL UNIT TESTING HAS PASSED!");
    }

    public static void testOps() throws Exception
    {
        bit zero = new bit();
        bit one = new bit(true);

        //ops:
        //and : 1000
        bit[] andOperation = {one, zero, zero, zero};
        //or : 1001
        bit[] orOperation = {one, zero, zero, one};
        //xor : 1010
        bit[] xorOperation = {one, zero, one, zero};
        //not : 1011
        bit[] notOperation = {one, zero, one, one};
        //left shift : 1100
        bit[] leftShiftOperation = {one, one, zero, zero};
        //right shift : 1101
        bit[] rightShiftOperation = {one, one, zero, one};
        //add : 1110
        bit[] addOperation = {one, one, one, zero};
        //subtract : 1111
        bit[] subtractOperation = {one, one, one, one};
        //multiply : 0111
        bit[] multiplyOperation = {zero, one, one, one};

        longword operand1 = new longword();
        longword operand2 = new longword();
        longword operand3 = new longword();
        longword operand4 = new longword();
        longword operand5 = new longword();

        operand1.set(5); //101
        operand2.set(2); //010
        operand3.set(7); //111
        operand4.setAllOnes(); //all 1's
        operand5.set(1); //001

        longword output1 = ALU.doOp(andOperation, operand1, operand3); //expected output: 101
        longword output2 = ALU.doOp(orOperation, operand1, operand2); //expected output : 111
        longword output3 = ALU.doOp(xorOperation, operand1, operand2); //expected output : 111
        longword output4 = ALU.doOp(notOperation, operand4, operand2); //expected output : 0
        longword output5 = ALU.doOp(leftShiftOperation, operand1, operand5);//expected output : 1010
        longword output6 = ALU.doOp(rightShiftOperation, operand2, operand5);//expected output : 0001
        longword output7 = ALU.doOp(addOperation, operand1, operand2);//expected output : 7
        longword output8 = ALU.doOp(subtractOperation, operand1, operand2);//expected output : 3
        longword output9 = ALU.doOp(multiplyOperation, operand1, operand2);//expected output : 10

        //comparing expected with actual
        if(output1.getSigned() != 5) throw new Exception("ALU testing has failed.");
        if(output2.getSigned() != 7) throw new Exception("ALU testing has failed.");
        if(output3.getSigned() != 7) throw new Exception("ALU testing has failed.");
        if(output4.getSigned() != 0) throw new Exception("ALU testing has failed.");
        if(output5.getSigned() != 10) throw new Exception("ALU testing has failed.");
        if(output6.getSigned() != 1) throw new Exception("ALU testing has failed.");
        if(output7.getSigned() != 7) throw new Exception("ALU testing has failed.");
        if(output8.getSigned() != 3) throw new Exception("ALU testing has failed.");
        if(output9.getSigned() != 10) throw new Exception("ALU testing has failed.");

        System.out.println("\tALU ops testing has passed!");
    }

}
