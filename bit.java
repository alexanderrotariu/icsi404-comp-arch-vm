interface bitOps
{
    //Interface: Given by Professor Phipps
    void set(boolean value);
    void toggle();
    void set();
    void clear();
    boolean getValue();
    bit and(bit other);
    bit or(bit other);
    bit xor(bit other);
    bit not();

    @Override
    String toString();
}

public class bit implements bitOps
{
    //Attributes of a bit - value (representing a 1 (true, on) or a 0 (false, off)
    private boolean value;

    //Default Constructor
    public bit()
    {
        //By default, a bit object will be set to false, (0 or off).
        this.value = false;
    }

    //Parameterized Constructor
    //newVal: create a bit object with a desired value (true or false, 1 or 0, on or off)
    public bit(boolean newVal)
    {
        this.value = newVal;
    }

    //Toggle(): if true, make false. if false, make true.
    public void toggle()
    {
        //If value of said bit is true, set value to false.
        if(this.value)
        {
            this.value = false;
        }
        //Otherwise, the value is false, so change value to true.
        else
        {
            this.value = true;
        }
    }

    //set(): Force value of bit value to true
    public void set()
    {
        this.value = true;
    }

    //set(boolean value): Set the bit value to desired boolean value
    public void set(boolean value)
    {
        this.value = value;
    }

    //clear(): Force the value of the bit to false.
    public void clear()
    {
        this.value = false;
    }

    //getValue(): return the value of the bit object being called on
    public boolean getValue()
    {
        return this.value;
    }

    //and(): compare the bit being called and the bit being passed in
    //if both bits are true, return a bit with value true, otherwise, return a bit with value false.
    public bit and(bit other)
    {
        bit output = new bit();

        //By default, a bit object's value will be false
        //If both conditions are not met, will return the default false bit value.
        if(this.value == true)
        {
            if(other.value == true)
            {
                output.value = true;
            }
        }

        return output;
    }

    //or(): compare the bit being called and the bit being passed in
    //if either one of the bits are true, return a bit with value true.
    //if both bits are false, return a bit with value false.
    public bit or(bit other)
    {
        bit output = new bit();

        //By default, a bit object's value will be false
        //If either one of the conditions are not met,
        //will return the default false bit value.
        if(this.value ==  true)
        {
            output.value = true;
        }
        else if(other.value == true)
        {
            output.value = true;
        }

        return output;
    }


    //xor(): compare the bit being called and the bit being passed in
    //if both bit's values are opposite, return a bit with value true
    //otherwise, return a bit with value false
    public bit xor(bit other)
    {
        bit output = new bit();

        //If both bits have opposite values, change output to true
        if(this.value == true)
        {
            if(other.value == false)
            {
                output.value = true;
            }
        }

        //Checking the other way around
        if(this.value == false)
        {
            if(other.value == true)
            {
                output.value = true;
            }
        }

        //If both true or both false, return false.
        return output;
    }


    //not(): take the value of the bit being called on and negate it
    //return a new bit with said value
    public bit not()
    {
        bit output = new bit();

        if(this.value)
        {
            output.value = false;
        }
        else
        {
            output.value = true;
        }

        return output;
    }

    //Override toString() for bit object
    //Should just say "f" if bit's value false or  "t" if bit's value true.
    @Override
    public String toString()
    {
        String output = "f";

        if(this.value == true)
        {
            output = "t";
        }

        return output;
    }
}
