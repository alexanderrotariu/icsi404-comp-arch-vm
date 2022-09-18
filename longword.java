interface ILongword {
    bit getBit(int i) throws Exception; // Get bit i
    void setBit(int i, bit value) throws Exception; // set bit i's value
    longword and(longword other) throws Exception; // and two longwords, returning a third
    longword or(longword other) throws Exception; // or two longwords, returning a third
    longword xor(longword other) throws Exception;// xor two longwords, returning a third
    longword not() throws Exception; // negate this longword, creating another
    longword rightShift(int amount) throws Exception; // rightshift this longword by amount bits, creating a new longword
    longword leftShift(int amount) throws Exception;// leftshift this longword by amount bits, creating a new longword
    @Override
    String toString();// returns a comma separated string of 0's and 1's: "0,0,0,0,0 (etcetera)" for example
    long getUnsigned() throws Exception; // returns the value of this longword as a long
    int getSigned() throws Exception; // returns the value of this longword as an int
    void copy(longword other) throws Exception; // copies the values of the bits from another longword into this one
    void set(int value) throws Exception; // set the value of the bits of this longword (used for tests)
}



public class longword implements ILongword
{
    //Attribute of a longword is an array of bits
    private bit[] vals;

    //Default constructor
    public longword()
    {
        //By default, an empty array?
        //Maybe make an array of 0's.... maybe though
        this.vals = new bit[32];

        for(int i = 0; i < this.vals.length; i++ )
        {
            this.vals[i] = new bit();
        }
    }

    public longword(int val) throws Exception {
        //By default, an empty array?
        //Maybe make an array of 0's.... maybe though
        this.vals = new bit[32];

        this.set(val);
    }

    //Parameterized constructor: make a longword with a certain bit as true/false
    //Used for testing
    public longword(boolean input, int location)
    {
        this.vals = new bit[32];

        for(int i = 0; i < this.vals.length; i++ )
        {
            this.vals[i] = new bit();
        }

        this.vals[location].set(input);
    }

    //Parameterized constructor:
    // allOnes - if true, makes the longword 32 bits of trues
    public longword(boolean allOnes)
    {
        this.vals = new bit[32];

        if(allOnes)
        {
            for(int i = 0; i < this.vals.length; i++ )
            {
                this.vals[i] = new bit(true);
            }
        }
        else
        {
            for(int i = 0; i < this.vals.length; i++ )
            {
                this.vals[i] = new bit();
            }
        }

    }

    //for testing
    //Sets every bit to 1's. used for testing
    public void setAllOnes()
    {
        for(int i = 0; i < this.vals.length; i++ )
        {
            this.vals[i] = new bit(true);
        }
    }


    public bit getBit(int i) throws Exception
    {
        //If an index, greater than 31 and less than 0, throw an exception where index are out of bounds
        if(i > 31 || i < 0) throw new Exception("Index out of bounds: Longword indexes from 0 to 31");

        //Get this bit in the vals array at index i
        bit output = this.vals[i];

        return output;
    }

    public void setBit(int i, bit value) throws Exception
    {
        //If an index, greater than 31 and less than 0, throw an exception where index are out of bounds
        if(i > 31 || i < 0) throw new Exception("Index out of bounds: Longword indexes from 0 to 31");

        //Set vals[i] with passed in bit
        this.vals[i] = value;
    }

    public longword and(longword other) throws Exception
    {
        longword output = new longword();

        //Going index by index
        //Take both bits, and perform bit.and() together.
        //Take the result from bit.and(). and set output accordingly.
        for(int i = 0; i<32; i++)
        {
            output.setBit(i,this.getBit(i).and(other.getBit(i)));
        }

        return output;
    }

    public longword or(longword other) throws Exception
    {
        longword output = new longword();

        //Going index by index
        //Take both bits, and perform bit.or() together.
        //Take the result from bit.or(). and set output accordingly.
        for(int i = 0; i<32; i++)
        {
            output.setBit(i, this.getBit(i).or(other.getBit(i)));
        }

        return output;
    }

    public longword xor(longword other) throws Exception
    {
        longword output = new longword();

        //Going index by index
        //Take both bits, and perform bit.xor() together.
        //Take the result from bit.xor(). and set output accordingly.
        for(int i = 0; i<32; i++)
        {
            output.setBit(i, this.getBit(i).xor(other.getBit(i)));
        }

        return output;
    }

    public longword not() throws Exception
    {
        longword output = new longword();

        //Going index by index 0 - 31
        for(int i = 0; i < 32; i++)
        {
            //save the current bit in a temp variable
            bit current = this.getBit(i);

            //take the temp variable and not it. set output accordingly
            output.setBit(i, current.not());
        }

        return output;
    }

    //might have to redo, rolling shift
    public longword rightShift(int amount) throws Exception
    {
        longword output = new longword();

        //Reference: temp variable
        longword reference = this;
        //for loop runs n=amount times
        for(int i = 0; i < amount; i++)
        {
            output = new longword();
            //gets the last bit in the array of bits

            //chucks it at the beginning of the output array
            output.setBit(0, new bit(false));

            //take the rest of the values and shift it right
            for(int j = 1; j < 32; j++)
            {
                output.setBit(j, reference.getBit(j-1));
            }

            reference = output;
        }
        return output;
    }

    //same here
    public longword leftShift(int amount) throws Exception
    {
        longword output = new longword();

        longword reference = this;
        //for loop runs n=amount times
        for(int i = 0; i < amount; i++)
        {
            output = new longword();
            //gets the last bit in the array of bits
            output.setBit(31, new bit(false));

            //take the rest of the values and shift it right
            for(int j = 1; j < 32; j++)
            {
                output.setBit(j-1, reference.getBit(j));
            }

            reference = output;
        }
        return output;
    }

    @Override
    public String toString()
    {
        String output = "";

        //Using toString from bit class
        for(int i = 0; i < this.vals.length; i++)
        {
            try
            {
                //concat output
                output = output + this.getBit(i).toString();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }

            if(i != this.vals.length-1)
            {
                output = output + ",";
            }
        }

        return output;
    }

    //For ease of use and testing, outputs cleaner.
    public String zeroOnes() throws Exception
    {
        String output = "";

        for(int i = 0; i < this.vals.length; i++)
        {
            if(this.vals[i].getValue())
            {
                output = output + "1";
            }
            else
            {
                output = output + "0";
            }


        }

        return output;
    }

    public long getUnsigned() throws Exception
    {
        long output = 0;
        //Separate counter to track exponential
        int exponentialCounter = 0;

        for(int i = this.vals.length-1; i>=0; i--)
        {
            //Binary arithmetic
            if(this.getBit(i).getValue())
            {
                output = output + (long)Math.pow(2, exponentialCounter);
            }

            exponentialCounter++;
        }

        return output;
    }

    public int getSigned() throws Exception
    {
        int output = 0;

        boolean signedBit = this.getBit(0).getValue();

        //If the signed bit is false, just get the unsigned of the longword
        if(signedBit == false)
        {
            output = (int)this.getUnsigned();
            return output;
        }
        //otherwise, the signed bit is true
        else
        {
            longword temp = new longword();
            //using a temp variable to avoid chaging the object being called on
            temp.copy(this);

            //Negate all bits
            for(int i =0; i < this.vals.length; i++)
            {
                temp.setBit(i, this.getBit(i).not());
            }
            //Take the negated longword and get the unsigned
            int negatedValue = (int) temp.getUnsigned();
            //Add one to it (same as the reverse).
            negatedValue = negatedValue + 1;

            //Make a longword with said value
            longword twosComp = new longword();
            twosComp.set(negatedValue);

            //Separate counter
            int exponentialCounter = 0;
            //Getting the value oof the new longword
            for(int i = this.vals.length-1; i>=0; i--)
            {
                if(twosComp.getBit(i).getValue())
                {
                    output = output + (int)Math.pow(2, exponentialCounter);
                }
                exponentialCounter++;
            }

            //Divide it out by one
            output = output / -1;
        }

        return output;
    }

    public void copy(longword other) throws Exception
    {
        //Take every value from this longword and set it to the other.
        for(int i = 0; i < other.vals.length; i++)
        {
            this.setBit(i, other.getBit(i));
        }
    }

    public void set(int value) throws Exception
    {
        int indexVals = 31;

        //If the value requested is negative
        if(value < 0)
        {
            //divide by negative one and -1 is the same as opposite
            int temp = (value/-1) - 1;
            while(temp > 0)
            {
                if(temp%2 == 1)
                {
                    this.setBit(indexVals, new bit(true));                }
                else
                {
                    this.setBit(indexVals, new bit(false));
                }
                indexVals--;
                temp = temp/2;
            }

            //set the rest to 0's
            for(int i = indexVals; i >= 0; i--)
            {
                this.getBit(indexVals).set(false);
            }

            //negate the rest of the bits
            for(int i =0; i < this.vals.length; i++)
            {
                this.setBit(i, this.getBit(i).not());
            }
        }
        //If the value requested is positive
        else
        {
            int temp = value;
            while(temp > 0)
            {
                if(temp%2 == 1)
                {
                    this.setBit(indexVals, new bit(true));
                }
                else
                {
                    this.setBit(indexVals, new bit(false));
                }
                indexVals--;
                temp = temp/2;
            }

            for(int i = indexVals; i >= 0; i--)
            {
                this.setBit(indexVals, new bit(false));
            }
        }
    }



}
