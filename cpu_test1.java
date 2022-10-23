public class cpu_test1
{
    public static void main(String[] args) throws Exception
    {
        //Run all tests
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        ALU_test.runTests();
        memory_test.runTests();
        runTests();
    }

    public static void runTests() throws Exception
    {
        testComputer();
    }

    public static void testComputer() throws Exception
    {
        computer test1 = new computer();

        //instructions
        String[] instructions = {   "0001000011111111", //mov R0 -1
                                    "0001000100000111", //mov R1 7
                                    "0001001000001010", //mov R2 10
                                    "1110000100100011", //add R1 R2 (store into) R3
                                    "1111000000010100", //subtract R0 R1 (store into) R4
                                    "1000000000000101", //and R0 R0 (store into) R5
                                    "0111000100100110", //multiply R1 R2 and store into R6
                                    "0010000000000000", //interrupt 0 (print registers)
                                    "0010000000000001", //interrupt 1 (print memory)
                                    "0000000000000000"}; //HALT

        //Preload our instructions
        test1.preload(instructions);

        //run
        test1.run();

        //Checking register for the right values.
        if(test1.registers[0].getSigned() != -1) throw new Exception("moving negative value has failed.");
        if(test1.registers[1].getSigned() != 7) throw new Exception("moving positive value has failed.");
        if(test1.registers[2].getSigned() != 10) throw new Exception("moving positive value has failed.");
        if(test1.registers[3].getSigned() != 17) throw new Exception("adding two registers has failed.");
        if(test1.registers[4].getSigned() != -8) throw new Exception("subtracting two registers has failed.");
        if(test1.registers[5].getSigned() != -1) throw new Exception("anding two registers has failed.");
        if(test1.registers[6].getSigned() != 70) throw new Exception("multiplying two registers has failed.");

        //Yay! everything is good! : )
        System.out.println("All computer testing passeed!");
    }
}
