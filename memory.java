public class memory
{
    //1024 BYTES OF MEMORY
     bit[] storage = new bit[8192];

     //Initializing memory to all false
     public memory()
     {
         for(int i = 0; i < this.storage.length; i++)
         {
             this.storage[i] = new bit();
         }
     }

    //go into the memory array and retrieve info
    public longword read(longword address) throws Exception
    {
        //get signed of address * 8, we are not going the full 32 bits
        int addressNum = address.getSigned() * 8;
        //if not in bounds, exception
        //System.out.println(addressNum);
        if(address.getSigned() >= 1024 || address.getSigned() < 0) throw new Exception("Address out of bounds!");

        longword output = new longword();

        //Get the address number
        //Should go from 0 - 255 (256 possible addresses)
        //read the address and set to output, return output
        int countOut = 0;
        for(int i = addressNum; i < addressNum+32; i++)
        {
            output.setBit(countOut, this.storage[i]);
            countOut++;
        }

        return output;
    }

    //write into memory something, that something being value
    public void write(longword address, longword value) throws Exception
    {
        //get signed of address * 8, we are not going the full 32 bits
        int addressNum = address.getSigned() * 8;
        //if not in bounds, exception
        if(address.getSigned() >= 1024 || address.getSigned() < 0) throw new Exception("Address out of bounds!");

        int countOut = 0;
        //write the bit into storage[i]
        for(int i = addressNum; i < addressNum+32; i++)
        {
            bit currentBit = value.getBit(countOut);
            this.storage[i].set(currentBit.getValue());
            countOut++;
        }


    }
}

