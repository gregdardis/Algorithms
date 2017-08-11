import java.math.BigInteger;

/**
 * This class multiplies (often very large) integers using Karatsuba's algorithm.
 */
public class Karatsuba {
    
    public BigInteger multiply(BigInteger x, BigInteger y) {
        if (x.compareTo(BigInteger.TEN) == -1 && y.compareTo(BigInteger.TEN) == -1) {
            return x.multiply(y);
        }
        // divide x into a and b halves
        String xString = x.toString();
        int xMid = xString.length() / 2;
        
        String aString = xString.substring(0, xMid);
        String bString = xString.substring(xMid);
        BigInteger a = new BigInteger(aString);
        BigInteger b = new BigInteger(bString);
        
        // divide y into c and d halves
        String yString = y.toString();
        int yMid = yString.length() / 2;
        
        String cString = yString.substring(0, yMid);
        String dString = yString.substring(yMid);
        BigInteger c = new BigInteger(cString);
        BigInteger d = new BigInteger(dString);
        
        BigInteger ac = multiply(a, c);
        BigInteger bd = multiply(b, d);
        BigInteger ad_bc = multiply(a.add(b), c.add(d)).subtract(bd).subtract(ac); 
        
        return ac;
        // need to figure out which length to put it to power of... higher or lower one if one is odd and one is even
//        return ((BigInteger.TEN).pow(xString.length()).multiply(ac)).add((BigInteger.TEN).pow(xMid).multiply(ad_bc).add(bd)); 
    }
    
    public static void main(String[] args) {
        Karatsuba karatsuba = new Karatsuba();
        System.out.println("Return: " + karatsuba.multiply(BigInteger.valueOf(1234), BigInteger.valueOf(5678)));
    }
}
