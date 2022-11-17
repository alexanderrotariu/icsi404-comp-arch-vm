public class Assembler
{
    public static String[] assemble(String[] inputs) throws Exception
    {
        String[] outputs = new String[inputs.length];

        String splitStrings[];
        String op1;
        String op2;
        String destination;

        for(int i  = 0; i < inputs.length; i++)
        {
            splitStrings = inputs[i].split(" ");

            switch(splitStrings[0])
            {
                case "move":
                    String movOP = "0001";

                    //Gets register to set to
                    String registerBin = getRegister(splitStrings[1]);

                    //turns value into an integer
                    int value = Integer.parseInt(splitStrings[2]);

                    //the value we need in binary
                    longword valueLW = new longword();
                    valueLW.set(value);

                    //Turns from T,F to 1's and 0's for substringing. Then getting the 8 bits we need for the value
                    String valBin = valueLW.zeroOnes().substring(24);

                    //Concatenate all the parts together for the final instruction
                    outputs[i] = movOP + registerBin + valBin;
                    break;

                case "interrupt":
                    //If instruction is an interrupt
                    String interrupt0OP = "0010";
                    String rest = "000000000000";

                    String interrupt1OP = "0010";
                    String rest1 = "000000000001";

                    //Have strings for both, then distinguish which output to do

                    if(splitStrings[1].equals("1"))
                    {
                        //For interrupt 1
                        outputs[i] = interrupt1OP + rest1;
                    }
                    else
                    {
                        //For interrupt 0
                        outputs[i] = interrupt0OP + rest;
                    }
                    break;

                case "halt":
                    //if its a halt, return all 0's 16 bits
                    outputs[i] = "0000000000000000";
                    break;

                case "add":
                    //Adding operatioon
                    String addOp = "1110";

                    //get the two operands from string.split()
                    op1 = getRegister(splitStrings[1]);
                    op2 = getRegister(splitStrings[2]);

                    //get the destination from string.split()
                    destination = getRegister(splitStrings[3]);

                    //Concatenate all the parts together for the final instruction
                    outputs[i] = addOp + op1 + op2 + destination;
                    break;

                case "sub":
                    //Subtracting operation
                    String subOp = "1111";

                    //get the two operands from string.split()
                    op1 = getRegister(splitStrings[1]);
                    op2 = getRegister(splitStrings[2]);

                    //get the destination from string.split()
                    destination = getRegister(splitStrings[3]);

                    //Concatenate all the parts together for the final instruction
                    outputs[i] = subOp + op1 + op2 + destination;
                    break;

                case "multiply":
                    //Multiplying operation
                    String multOp = "0111";

                    //get the two operands from string.split()
                    op1 = getRegister(splitStrings[1]);
                    op2 = getRegister(splitStrings[2]);

                    //get the destination from string.split()
                    destination = getRegister(splitStrings[3]);

                    //Concatenate all the parts together for the final instruction
                    outputs[i] = multOp + op1 + op2 + destination;
                    break;

                case "and":
                    //anding operation
                    String andOp = "1000";

                    //get the two operands from string.split()
                    op1 = getRegister(splitStrings[1]);
                    op2 = getRegister(splitStrings[2]);

                    //get the destination from string.split()
                    destination = getRegister(splitStrings[3]);

                    //Concatenate all the parts together for the final instruction
                    outputs[i] = andOp + op1 + op2 + destination;
                    break;

                case "or":
                    String orOp = "1001";

                    //get the two operands from string.split()
                    op1 = getRegister(splitStrings[1]);
                    op2 = getRegister(splitStrings[2]);

                    //get the destination from string.split()
                    destination = getRegister(splitStrings[3]);


                    //Concatenate all the parts together for the final instruction
                    outputs[i] = orOp + op1 + op2 + destination;
                    break;


                case "xor":
                    String xorOp = "1010";

                    //get the two operands from string.split()
                    op1 = getRegister(splitStrings[1]);
                    op2 = getRegister(splitStrings[2]);

                    //get the destination from string.split()
                    destination = getRegister(splitStrings[3]);


                    //Concatenate all the parts together for the final instruction
                    outputs[i] = xorOp + op1 + op2 + destination;
                    break;

                case "not":
                    String notOp = "1011";

                    //get the two operands from string.split()
                    op1 = getRegister(splitStrings[1]);
                    op2 = getRegister(splitStrings[2]);

                    //Concatenate all the parts together for the final instruction
                    outputs[i] = notOp + op1 + "0000" + op2;
                    break;

                case "leftshift":
                    String leftShiftOp = "1100";

                    //get the two operands from string.split()
                    op1 = getRegister(splitStrings[1]);
                    op2 = getRegister(splitStrings[2]);

                    //get the destination from string.split()
                    destination = getRegister(splitStrings[3]);


                    //Concatenate all the parts together for the final instruction
                    outputs[i] = leftShiftOp + op1 + op2 + destination;
                    break;

                case "rightshift":
                    String rightShiftOp = "1101";

                    //get the two operands from string.split()
                    op1 = getRegister(splitStrings[1]);
                    op2 = getRegister(splitStrings[2]);

                    //get the destination from string.split()
                    destination = getRegister(splitStrings[3]);

                    //Concatenate all the parts together for the final instruction
                    outputs[i] = rightShiftOp + op1 + op2 + destination;
                    break;

                case "jump":
                    String jumpOp = "0011";

                    //turns value into an integer
                    int jumpVal = Integer.parseInt(splitStrings[1]);

                    //the value we need in binary
                    longword jumpValLW = new longword();
                    jumpValLW.set(jumpVal);

                    String jumpValueBinary = jumpValLW.zeroOnes().substring(20);

                    outputs[i] = jumpOp + jumpValueBinary;
                    break;

                case "compare":
                    String compareOP = "0100";

                    String zeroes = "0000";

                    op1 = getRegister(splitStrings[1]);
                    op2 = getRegister(splitStrings[2]);

                    outputs[i] = compareOP + zeroes + op1 + op2;
                    break;

                case "branchIfEqual":
                    String branchIfEqualJumpOp = "0101";
                    String ccEqual =  "10";


                    String sign = "0";
                    int addressNum = Integer.parseInt(splitStrings[1]);

                    if(addressNum < 0)
                    {
                        sign = "1";
                    }

                    longword address = new longword();
                    address.set(Integer.parseInt(splitStrings[1]));
                    String addressStr = address.zeroOnes();

                    String addressBin = addressStr.substring(23);

                    outputs[i] = branchIfEqualJumpOp + ccEqual + sign + addressBin;
                    break;

                case "branchIfNotEqual":
                    String branchIfNotEqualOp = "0101";
                    String ccNotEqual =  "00";

                    String signNotEqual = "0";

                    if(Integer.parseInt(splitStrings[1]) < 0)
                    {
                        signNotEqual = "1";
                    }

                    longword addressNotEqual = new longword();
                    addressNotEqual.set(Integer.parseInt(splitStrings[1]));
                    String addressStrNotEqual = addressNotEqual.zeroOnes();

                    String addressBinNotEqual = addressStrNotEqual.substring(23);

                    outputs[i] = branchIfNotEqualOp + ccNotEqual + signNotEqual + addressBinNotEqual;
                    break;

                case "branchIfLessThan":
                    String branchIfLessThanOp = "0101";
                    String ccLessThan = "00";

                    String signLessThan = "0";

                    if(Integer.parseInt(splitStrings[1]) < 0)
                    {
                        signLessThan = "1";
                    }

                    longword addressLessThan = new longword();
                    addressLessThan.set(Integer.parseInt(splitStrings[1]));
                    String addressStrLessThan = addressLessThan.zeroOnes();

                    String addressBinLessThan = addressStrLessThan.substring(23);

                    outputs[i] = branchIfLessThanOp + ccLessThan + signLessThan + addressBinLessThan;

                    break;

                case "branchIfGreaterThan":
                    String branchIfGreaterThanOp = "0101";
                    String ccGreaterThan = "01";

                    String signGreaterThan = "0";

                    if(Integer.parseInt(splitStrings[1]) < 0)
                    {
                        signGreaterThan = "1";
                    }

                    longword addressGreaterThan = new longword();
                    addressGreaterThan.set(Integer.parseInt(splitStrings[1]));
                    String addressStrGreaterThan = addressGreaterThan.zeroOnes();

                    String addressBinGreaterThan = addressStrGreaterThan.substring(23);

                    outputs[i] = branchIfGreaterThanOp + ccGreaterThan + signGreaterThan + addressBinGreaterThan;

                    break;

                default:
                    //default case, halt.
                    outputs[i] = "0000000000000000";
                    break;
            }

        }

        return outputs;
    }

    public static String getRegister(String register)
    {
        //from the switch, return the correct op code
        switch(register)
        {
            case "R0":
                return "0000";
            case "R1":
                return "0001";
            case "R2":
                return "0010";
            case "R3":
                return "0011";
            case "R4":
                return "0100";
            case "R5":
                return "0101";
            case "R6":
                return "0110";
            case "R7":
                return "0111";
            case "R8":
                return "1000";
            case "R9":
                return "1001";
            case "R10":
                return "1010";
            case "R11":
                return "1011";
            case "R12":
                return "1100";
            case "R13":
                return "1101";
            case "R14":
                return "1110";
            case "R15":
                return "1111";
            default:
                return "";
        }
    }


}


