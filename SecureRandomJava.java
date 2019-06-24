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

