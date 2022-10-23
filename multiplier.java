public class multiplier
{
    //Multiply
    public static longword multiply(longword a, longword b) throws Exception
    {
        longword output = new longword();

        int shiftcounter = 0;
        for(int i = 31; i >= 0;  i--)
        {
            bit andBit = b.getBit(i);
            longword temp = new longword();

            for(int j = 31; j >= 0; j--)
            {
                //Getting single value and then and-ing them with the whole of the second
                temp.setBit(j, a.getBit(j).and(andBit));
            }

            if(shiftcounter != 0)
            {
                //leftShifting based on where location of and bit is
                longword shifted = temp.leftShift(shiftcounter);
                output = rippleAdder.add(output, shifted);
            }
            else
            {
                output = rippleAdder.add(output, temp);
            }
            shiftcounter++;
        }


        return output;
    }

}
