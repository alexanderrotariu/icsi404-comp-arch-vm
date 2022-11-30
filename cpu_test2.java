public class cpu_test2
{
    public static void main(String[] args) throws Exception
    {
        //Run all previous tests
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        ALU_test.runTests();
        memory_test.runTests();
        cpu_test1.runTests();
        assembler_test.runTests();

        runTests();
    }


    public static void runTests() throws Exception
    {
        testComputer2();
    }

    public static void testComputer2() throws Exception
    {
        //Creating a new computer
        computer test2 = new computer();

        //Instruction set
        String inputs[] = new String[21];
        inputs[0] = "move R0 5";
        inputs[1] = "move R1 5";
        inputs[2] = "move R2 10";
        inputs[3] = "move R3 1";
        inputs[4] = "compare R1 R2";
        inputs[5] = "branchIfLessThan 2"; //should skip next halt and update R4
        inputs[6] = "halt";
        inputs[7] = "move R4 1";
        inputs[8] = "compare R0 R1";
        inputs[9] = "branchIfEqual 2"; //should skip next halt and update R5
        inputs[10] = "halt";
        inputs[11] = "move R5 1";
        inputs[12] = "compare R2 R1";
        inputs[13] = "branchIfGreaterThan 2"; //should skip next halt and update R6
        inputs[14] = "halt";
        inputs[15] = "move R6 1";
        inputs[16] = "compare R0 R2";
        inputs[17] = "branchIfNotEqual 2"; //should skip next halt and update R7
        inputs[18] = "halt";
        inputs[19] = "move R7 1";
        inputs[20] = "halt"; //should halt on this instruction

        //Save outputs in an array
        String[] outputs = Assembler.assemble(inputs);

        //Pre-load instructions
        test2.preload(outputs);


        //Exception handling for assembler
        if(outputs[0].equals("0001000000000101")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[1].equals("0001000100000101")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[2].equals("0001001000001010")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[3].equals("0001001100000001")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[4].equals("0100000000010010")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[5].equals("0101000000000010")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[6].equals("0000000000000000")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[7].equals("0001010000000001")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[8].equals("0100000000000001")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[9].equals("0101100000000010")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[10].equals("0000000000000000")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[11].equals("0001010100000001")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[12].equals("0100000000100001")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[13].equals("0101010000000010")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[14].equals("0000000000000000")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[15].equals("0001011000000001")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[16].equals("0100000000000010")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[17].equals("0101000000000010")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[18].equals("0000000000000000")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[19].equals("0001011100000001")== false) throw new Exception("Wrong instruction (Assemble failed).");
        if(outputs[20].equals("0000000000000000")== false) throw new Exception("Wrong instruction (Assemble failed).");

        //Run computer
        test2.run();

        //See if the registers are updated
        //If they are 1 in the places move is called good, otherwise throw exception
        if(test2.registers[4].getSigned() != 1) throw new Exception("moving value not done, testing has failed");
        if(test2.registers[5].getSigned() != 1) throw new Exception("moving value not done, testing has failed");
        if(test2.registers[6].getSigned() != 1) throw new Exception("moving value not done, testing has failed");
        if(test2.registers[7].getSigned() != 1) throw new Exception("moving value not done, testing has failed");


        System.out.println("All CPU_test2 testing passed");
    }
}
