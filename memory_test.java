public class memory_test
{
    public static void main(String [] args) throws Exception
    {
        //Run all tests
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        ALU_test.runTests();
        runTests();
    }
    public static void runTests() throws Exception
    {
        //Run memory tests
        System.out.println("MEMORY UNIT TEST START ----------------------------------------------------------------------------");
        testWrite();
        testRead();
        System.out.println("ALL UNIT TESTING HAS PASSED!");
    }
    public static void testWrite() throws Exception
    {
        //inputting 5 to address 1
        longword input1 = new longword();
        input1.set(5);
        longword address = new longword();
        address.set(1);

        //writing 5 to address 1 in memory
        memory mem1 = new memory();
        mem1.write(address, input1);

        longword output = mem1.read(address);

        //If the output is not 5, throw new exception
        if(output.getSigned()!=5) throw new Exception("write testing has failed.");

        //Writing the value of 10 to address 2
        longword input2 = new longword();
        input1.set(10);
        longword address2 = new longword();
        address.set(2);

        //Writing to memory
        mem1.write(address2, input2);

        //Reading from memory address 1, to see if overwritten
        longword output2 = mem1.read(address);

        //System.out.println(output.getSigned());
        //System.out.println(output2.getSigned());

        //if not overwrittenn, throw expection
        if(output2.getSigned()==5) throw new Exception("write testing has failed.");

        System.out.println("\twrite testing has passed");
    }
    public static void testRead() throws Exception
    {
        //inputting 7 to address 2
        longword input1 = new longword();
        input1.set(7);
        longword address = new longword();
        address.set(2);

        //System.out.println("input1: "+input1.getSigned());
        //System.out.println("address: "+address.getSigned());

        //writing 7 to address 2 in memory
        memory mem1 = new memory();
        mem1.write(address, input1);

        longword output = mem1.read(address);

        //System.out.println("original output: " + output.getSigned());

        //If the output is not 7, throw new exception
        if(output.getSigned()!=7) throw new Exception("read testing has failed.");

        //Writing the value of 15 to address 1
        longword input2 = new longword();
        input2.set(15);
        longword address2 = new longword();
        address2.set(1);

        //System.out.println("input2: " + input2.getSigned());
        //System.out.println("address2: "+address2.getSigned());

        //Writing to memory
        mem1.write(address2, input2);

        //Reading from memory address 1, to see if overwritten
        longword output2 = mem1.read(address2);
        output = mem1.read(address);

        //System.out.println("original output: "+output.getSigned());
        //System.out.println("new address output: "+output2.getSigned());

        if(output.getSigned()==7) throw new Exception("write testing has failed.");

        System.out.println("\tread testing has passed");
    }


}
