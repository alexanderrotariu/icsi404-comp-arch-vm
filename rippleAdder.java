public class rippleAdder
{
    //Add two longwords together
    public static longword add(longword a, longword b) throws Exception
    {
        longword output = new longword();
        //CarryIn initially is 0
        bit carryIn = new bit();
        //CarryOut to be determined after arithmetic
        bit carryOut = new bit();

        //Go bit by bit, index 31 is first bit
        for(int i = 31; i >= 0; i--)
        {
            //get bit from longword a
            bit bit1 = a.getBit(i);
            //get bit from longword b
            bit bit2 = b.getBit(i);

            //set output bit at index based on circuit given in lecture
            //S = X XOR Y XOR CarryIn
            output.setBit(i, bit1.xor(bit2).xor(carryIn));

            //set carryout bit at index based on circuit given in lecture
            //CarryOut = X AND Y OR (( X XOR Y) AND Cin)
            carryOut = (bit1.and(bit2).or(bit1.xor(bit2).and(carryIn)));

            carryIn = carryOut;
        }
        return output;
    }

    //subtract two longwords
    public static longword subtract(longword a, longword b) throws Exception
    {
        longword output = new longword();

        //Subtracting is the same as negating and adding one of the values
        //Negate longword b and add it using rippleAdder to a
        longword tempSum = rippleAdder.add(a, b.not());

        //twos complement
        longword one = new longword();
        one.set(1);

        //add one
        output = rippleAdder.add(tempSum, one);

        //Difference between a and b
        return output;
    }
}
