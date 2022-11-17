public class assembler_test
{
    public static void main(String[] args) throws Exception
    {
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        ALU_test.runTests();
        memory_test.runTests();
        cpu_test1.runTests();
        runTests();
    }

    public static void runTests() throws Exception
    {
        testAseembler();
    }

    public static void testAseembler() throws Exception
    {
        //String inputs, instruction set from the previous assignment with a few more
        String inputs[] = new String[13];
        inputs[0] = "move R0 -1";
        inputs[1] = "move R1 7";
        inputs[2] = "move R2 10";
        inputs[3] = "add R1 R2 R3";
        inputs[4] = "sub R0 R1 R4";
        inputs[5] = "and R0 R0 R5";
        inputs[6] = "multiply R1 R2 R6";
        inputs[7] = "interrupt 0";
        inputs[8] = "interrupt 1";
        inputs[9] = "xor R0 R1 R0";
        inputs[10] = "or R1 R0 R0";
        inputs[11] = "not R0 R1 R0";
        inputs[12] = "halt";

        String[] outputs = Assembler.assemble(inputs);

        //Error checking
        if(outputs[0].equals("0001000011111111")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[1].equals("0001000100000111")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[2].equals("0001001000001010")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[3].equals("1110000100100011")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[4].equals("1111000000010100")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[5].equals("1000000000000101")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[6].equals("0111000100100110")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[7].equals("0010000000000000")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[8].equals("0010000000000001")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[9].equals("1010000000010000")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[10].equals("1001000100000000")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[11].equals("1011000000000001")== false) throw new Exception("Assembler testing has failed.");
        if(outputs[12].equals("0000000000000000")== false) throw new Exception("Assembler testing has failed.");


        System.out.println("All assembler testing has passed!");
    }
}
