import java.math.BigInteger;

/**
 * This class multiplies (often very large) integers using Karatsuba's algorithm.
 */
public class Karatsuba {
    
    public BigInteger multiply(BigInteger x, BigInteger y) {
        // Once the problem is reduced down to single digit multiplication, simply multiply.
        // This is the base case to stop the recursion.
        if (x.compareTo(BigInteger.TEN) == -1 || y.compareTo(BigInteger.TEN) == -1) {
            return x.multiply(y);
        }
        
        int n = Math.max(x.toString().length(), y.toString().length());
        int halfN = n / 2;
        
        BigInteger a = x.divide(BigInteger.TEN.pow(halfN));
        BigInteger b = x.mod(BigInteger.TEN.pow(halfN));
        BigInteger c = y.divide(BigInteger.TEN.pow(halfN));
        BigInteger d = y.mod(BigInteger.TEN.pow(halfN));
        
        BigInteger ac = multiply(a, c);
        BigInteger bd = multiply(b, d);
        BigInteger ad_bc = (multiply(a.add(b), c.add(d))).subtract(ac).subtract(bd);
        
        return ac.multiply(BigInteger.TEN.pow(2 * halfN)).add(ad_bc.multiply(BigInteger.TEN.pow(halfN))).add(bd);
    }
    
    public static void main(String[] args) {
        Karatsuba karatsuba = new Karatsuba();
        BigInteger n1 = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
        BigInteger n2 = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
        System.out.println("Product: " + karatsuba.multiply(n1, n2));
    }
}
