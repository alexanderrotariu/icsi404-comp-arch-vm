public class ALU
{
    public static longword doOp(bit[] operation, longword a, longword b) throws Exception
    {
        longword output = new longword();

        bit zero = new bit();
        bit one = new bit(true);

        bit[][] ops =
        {
                {one, zero, zero, zero}, //and
                {one, zero, zero, one}, //or
                {one, zero, one, zero}, //xor
                {one, zero, one, one}, //not
                {one, one, zero, zero}, //left shift
                {one, one, zero, one}, //right shift
                {one, one, one, zero}, //add
                {one, one, one, one}, //subtract
                {zero, one, one, one}, //multiply
        };

        int selectOp = 0;


        for(int i = 0; i < 9; i++)
        {
            //Get the value of each bit in the array
            boolean bit1 = ops[i][0].getValue() == operation[0].getValue();
            boolean bit2 = ops[i][1].getValue() == operation[1].getValue();
            boolean bit3 = ops[i][2].getValue() == operation[2].getValue();
            boolean bit4 = ops[i][3].getValue() == operation[3].getValue();

            //If they match the dict of ops, then set the op to that operation
            if(bit1&&bit2&&bit3&&bit4)
            {
                selectOp = i;
            }
        }

        //variable for shifting (right and left)
        longword shiftVal = new longword();

        //Switch case for doing the actual operation
        switch(selectOp)
        {
            case 0: //and operation
                output = a.and(b);
                break;

            case 1: //or operation
                output = a.or(b);
                break;

            case 2: //xor operation
                output = a.xor(b);
                break;

            case 3: //not operation
                output = a.not();
                break;

            case 4: //shift left operation
                //get the lowest 5 bits
                // set into shift val
                // shift that amount
                shiftVal.setBit(31, b.getBit(31));
                shiftVal.setBit(30, b.getBit(30));
                shiftVal.setBit(29, b.getBit(29));
                shiftVal.setBit(28, b.getBit(28));
                shiftVal.setBit(27, b.getBit(27));

                //if shifting a negative number, throw exception
                if(shiftVal.getSigned() < 0) throw new Exception("Can't shift longword by negative value!");

                //shift a by b
                output = a.leftShift(shiftVal.getSigned());
                break;

            case 5: //shift right operation
                //get the lowest 5 bits
                // set into shift val
                // shift that amount
                shiftVal.setBit(31, b.getBit(31));
                shiftVal.setBit(30, b.getBit(30));
                shiftVal.setBit(29, b.getBit(29));
                shiftVal.setBit(28, b.getBit(28));
                shiftVal.setBit(27, b.getBit(27));

                //if shifting a negative number, throw exception
                if(shiftVal.getSigned() < 0) throw new Exception("Can't shift longword by negative value!");

                //shift a by b
                output = a.rightShift(shiftVal.getSigned());
                break;

            case 6: //add operation
                output = rippleAdder.add(a, b);
                break;

            case 7: //subtract operation
                output = rippleAdder.subtract(a, b);
                break;

            case 8: //multiply operation
                output = multiplier.multiply(a, b);
                break;
        }

        return output;
    }

}
