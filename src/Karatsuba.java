import java.math.BigInteger;
import java.util.Scanner;

/**
 * This class multiplies (often very large) integers using Karatsuba's algorithm.
 * BigIntegers are utilized to prevent overflow, and allow HUGE numbers to be multiplied together almost instantly.
 * 
 * The program prompts the user for two strings containing only numbers,
 * which are turned into BigIntegers, then their product is printed to the console.
 */
public class Karatsuba {
    
    private static Scanner scanner = new Scanner(System.in);
    
    private static final String SENTINAL = "-1";
    
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
        System.out.println("This program multiplies two numbers together.\nThey can be huge numbers!\nFor example: ");
        BigInteger n1 = new BigInteger("314462643383279502884197169399375105820974944592");
        BigInteger n2 = new BigInteger("277757247093699959574966967627");
        System.out.print(n1 + " * " + n2 + " = " + karatsuba.multiply(n1, n2) + "\n");
        while (true) {
            System.out.println("\nEnter two numbers to find their product (-1 to quit): ");
            
            System.out.print("Number 1: ");
            String str1 = scanner.nextLine();
            if (str1.equals(SENTINAL)) break;
            BigInteger num1 = new BigInteger(str1);
            
            System.out.print("Number 2: ");
            String str2 = scanner.nextLine();
            if (str2.equals(SENTINAL)) break;
            BigInteger num2 = new BigInteger(str2);
            
            System.out.println("Product: " + karatsuba.multiply(num1, num2));
        }
        System.out.println("Goodbye.");
    }
}
