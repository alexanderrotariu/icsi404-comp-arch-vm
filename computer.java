import java.sql.SQLOutput;

public class computer
{
    //Running bit, if computer is running or not
    bit running = new bit(true);
    //Program counter
    longword PC = new longword();
    //Current Instruction
    longword currentInstruction = new longword();

    //operator 1
    longword op1 = new longword();
    //operator 2
    longword op2 = new longword();
    //Move register
    longword movReg = new longword();
    //Move Value for the register
    longword movValue = new longword();
    //Result to store
    longword result = new longword();

    //If we are doing a move operation
    boolean movFlag = false;
    //If we are dealing with a negative value
    boolean negativeFlag = false;
    //If we need to store something
    boolean storeFlag = false;
    //Result of move
    longword movResult = new longword();

    //memory should be private
    //Our storage for the computer
    private memory storage = new memory();

    longword registers[] = new longword[16];

    //Computer Default constructor
    public computer() throws Exception
    {
        for(int i = 0; i < registers.length; i++)
        {
            registers[i] = new longword();
        }
    }

    //Run
    public void run() throws Exception
    {
        //While bit is true (running), fetch, decode, execute, store
        while(running.getValue())
        {
            fetch();
            decode();
            execute();
            store();
        }
    }

    public void fetch() throws Exception
    {
        //Save the current instruction from program counter
        //read off PC and then set currentInstruction to PC value
        currentInstruction.copy(storage.read(PC));


        //Longword of the value two to increment the PC by
        longword two = new longword();
        two.set(2);

        //Increment the PC.
        PC = rippleAdder.add(PC, two);
    }

    //get the values from the registers and prepare for the ALU
    public void decode() throws Exception
    {
        //mask of 0000 0000 0000 0000 0000 0000 0000 1111
        //This will get us the first 4 bits we want
        //Shift the instruction over however many bits necessary to get the value
        longword mask = new longword();
        mask.set(15);

        longword mask2 = new longword();
        mask2.set(255);
        //0000 0000 0000 0000 0000 0000 1111 1111

        //shift right 24 bits for reg1
        //shift right 20 bits for reg2

        //to get op1
        longword shifted = currentInstruction.rightShift(24);
        longword importantBits = shifted.and(mask);
        int registerNumber = importantBits.getSigned();
        op1 = registers[registerNumber];

        //to get op2
        longword shifted2 = currentInstruction.rightShift(20);
        longword importantBits2 = shifted2.and(mask);
        int registerNumber2 = importantBits2.getSigned();
        op2 = registers[registerNumber2];

        //Get info for mov if its is needed.
        //Just a different way of interpreting
        movReg = currentInstruction.rightShift(24).and(mask);
        movValue = currentInstruction.rightShift(16).and(mask2);
    }
    public void execute() throws Exception
    {
        //mask of 0000 0000 0000 0000 0000 0000 0000 1111
        longword mask = new longword();
        mask.set(15);

        //shift right 12 for reg3 (destination)
        //Operation is array of 4 bits
        bit operation[] = new bit[4];
        operation[0] = new bit();
        operation[1] = new bit();
        operation[2] = new bit();
        operation[3] = new bit();

        //Getting the first 4 bits and then assigning.
        for(int i = 0; i < 4; i++)
        {
            operation[i].set(currentInstruction.getBit(i).getValue());
        }

        if(operation[0].getValue() == false && operation[1].getValue() == false && operation[2].getValue() == false && operation[3].getValue() == false)
        {
            //halt
            //Set running to 0
            running.set(false);
        }
        else if(operation[0].getValue() == false && operation[1].getValue() == false && operation[2].getValue() == false && operation[3].getValue() == true)
        {
            movFlag = true;
            //move
            //find the register that we need
            //write the value into that register.
            //If negative value
            if(currentInstruction.getBit(8).getValue())
            {
                negativeFlag = true;
                //Negative sum, working backwards
                longword negativeSum = new longword();
                negativeSum.set(-256);

                //operation array
                bit[] addOP = {new bit(true), new bit(true), new bit(true), new bit()};

                //pass in addition, and two operators
                movResult = ALU.doOp(addOP, negativeSum, movValue);
            }
        }
        else if(operation[0].getValue() == false && operation[1].getValue() == false && operation[2].getValue() == true && operation[3].getValue() == false)
        {
            //Interrupt: 2 types

            //Print addresses
            if(currentInstruction.getBit(15).getValue())
            {
                longword currentAddress = new longword();
                //– print all 1024 bytes of memory to the screen.
                for(int i = 0; i <= 1020; i = i+4)
                {
                    currentAddress.set(i);
                    System.out.println("Address["+i+"]: "+storage.read(currentAddress));
                }

            }
            else
            {
                //Print out all the registers
                for(int i = 0; i < registers.length; i++)
                {
                    System.out.println("R"+i+": "+registers[i].getSigned());
                }
            }
        }
        else
        {
            //calling the ALU to do the operation and save the result
            result = ALU.doOp(operation, op1, op2);
            storeFlag = true;
        }

    }
    public void store() throws Exception
    {
        //if we are still running !
            //if we have a mov operation
        if (movFlag)
        {
            //if that mov operation contains a negative number
            if(negativeFlag)
            {
                registers[movReg.getSigned()].copy(movResult);
                negativeFlag = false;
            }
            else
            {
                //otherwise, its a positive number
                registers[movReg.getSigned()].copy(movValue);
            }
            //reset flag
            movFlag = false;
        }
        else if(storeFlag)
        {
            //mask of 0000 0000 0000 0000 0000 0000 0000 1111
            longword mask = new longword();
            mask.set(15);
            longword shifted = currentInstruction.rightShift(16);

            //find where to save the result
            int resultReg = shifted.and(mask).getSigned();

            //save result into the correct register
            registers[resultReg].copy(result);
            //reset flag
            storeFlag = false;
        }
        else
        {
            // we dont need to do anything if we are not moving or doing an operation.
            //Things like interrupts dont need to store.
        }
    }


    //preload
    public void preload(String[] inputs) throws Exception
    {
        longword currentAddress = new longword();
        //hit every input
        for(int i = 0; i < inputs.length; i++)
        {
            longword instructionTemp = new longword();
            //start at end and go backwards
            for(int j = 15; j >= 0; j--)
            {
                if(inputs[i].charAt(j) == '1')
                {
                    instructionTemp.setBit(j, new bit(true));
                }

                //store 2 instructions in every longword
                currentAddress.set(i*2);
                storage.write(currentAddress, instructionTemp);
            }
        }
    }
}

