public class cpu_test3 {
    public static void main(String[] args) throws Exception {
        //Run all previous tests
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        ALU_test.runTests();
        memory_test.runTests();
        cpu_test1.runTests();
        assembler_test.runTests();
        cpu_test2.runTests();

        runTests();
    }

    public static void runTests() throws Exception
    {
        testComputer3();
    }

    public static void testComputer3() throws Exception
    {
        //Creating a new computer
        computer test3 = new computer();

        //Instruction set
        String inputs[] = new String[5];
        inputs[0] = "call 6";
        inputs[1] = "interrupt 0";
        inputs[2] = "halt";
        inputs[3] = "interrupt 1";
        inputs[4] = "return";

        String inputs2[] = new String[9];
        inputs2[0] = "move R0 12";
        inputs2[1] = "move R1 7";
        inputs2[2] = "push R0";
        inputs2[3] = "push R1";
        inputs2[4] = "pop R2";
        inputs2[5] = "pop R3";
        inputs2[6] = "interrupt 0";
        inputs2[7] = "interrupt 1";
        inputs2[8] = "halt";

        //Save outputs in an array
        String[] outputs = Assembler.assemble(inputs);

        if(outputs.length == 9)
        {
            if(outputs[0].equals("0001000000001100")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[1].equals("0001000100000111")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[2].equals("0110000000000000")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[3].equals("0110000000000001")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[4].equals("0110010000000010")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[5].equals("0110010000000011")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[6].equals("0010000000000000")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[7].equals("0010000000000001")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[8].equals("0000000000000000")== false) throw new Exception("Wrong instruction (Assemble failed).");
        }
        else if(outputs.length == 5)
        {
            if(outputs[0].equals("0110100000000110")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[1].equals("0010000000000000")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[2].equals("0000000000000000")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[3].equals("0010000000000001")== false) throw new Exception("Wrong instruction (Assemble failed).");
            if(outputs[4].equals("0110110000000000")== false) throw new Exception("Wrong instruction (Assemble failed).");
        }
        else
        {
            //dont error check
        }

        //Pre-load instructions
        test3.preload(outputs);

        test3.run();

        if(outputs.length == 9)
        {
            if(test3.registers[0].getSigned() != 12) throw new Exception("moving value not done, testing has failed");
            if(test3.registers[1].getSigned() != 7) throw new Exception("moving value not done, testing has failed");
            if(test3.registers[2].getSigned() != 7) throw new Exception("moving value not done, testing has failed");
            if(test3.registers[3].getSigned() != 12) throw new Exception("moving value not done, testing has failed");
        }
        else if(outputs.length == 5)
        {
            //should print addresses and then registers
        }




    }
}