# Using SecureRandom class to generate secure random bytes in Java

Here is the code example

```
import java.security.*; 
import java.util.*; 

public class SecureRandomJava {

    public static void main(String[] args) throws NoSuchAlgorithmException {
	SecureRandom sr = SecureRandom.getInstance("NativePRNG");
	byte[] bytes = new byte[64];
	sr.nextBytes(bytes);
	System.out.println("Secure random 64 bytes: " + Arrays.toString(bytes));
	String base64Encoded = Base64.getEncoder().encodeToString(bytes);
	System.out.println("Secure random 64 bytes Base64 encoded: " + base64Encoded);
	System.out.println("Length of base 64 encoded bytes: " + base64Encoded.length());
    }

}

```

## Which provider to use

We use NativePRNG above relying on the underlying OS device, /dev/urandom for
the source of entropy.

The getInstance() can throw the NoSuchAlgorithmException. An alternative
provider could be SHA1PRNG. 

I choose above to use NativePRNG. If you have some reason to trust your OS'es
PRNG less than the SHA1PRNG might be a better choice. This is an awkward situation 
however if this is the case.

## 64 random bytes

In cryptography we call bytes following one another stings. In Java strings are 
usually validly encoded, for example with UTF-8. 

So to get 64 random bytes string in crypto sense, we generate above 64 random bytes.

To store it in a string, for JSON transfer or for storage in a database, we choose
to use Base64 encoding for bytes. Notice how it enlarges the string length to 88 characters.

```
$ java SecureRandomJava 
Secure random 64 bytes: [33, 19, 108, 102, 10, -46, -32, 28, 88, -61, -45, 53, -58, 109, -29, 59, 8, -85, -77, 95, 89, 3, -66, -101, -38, -46, 39, 74, -128, 127, 44, -71, -47, 121, 121, 15, 12, 7, -2, 112, -16, -121, -43, -22, -88, 79, 37, -56, -16, 99, 91, -100, -75, 100, 5, -80, -32, -112, -35, -123, -9, -115, -66, 12]
Secure random 64 bytes Base64 encoded: IRNsZgrS4BxYw9M1xm3jOwirs19ZA76b2tInSoB/LLnReXkPDAf+cPCH1eqoTyXI8GNbnLVkBbDgkN2F942+DA==
Length of base 64 encoded bytes: 88
```

## Storing the random bytes

As explained above, you could store the Base64 encoded 88 bytes string or 
binary(64) - fixed length binary type in DBs supporting it.



# Compile and run

Compile:

`javac SecureRandomJava.java`


Run:

`java SecureRandomJava`


