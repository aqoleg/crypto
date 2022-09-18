import com.aqoleg.crypto.Sha512;

public class Example {
    public static void main(String[] args) {
        String message = "";
        if (args != null && args.length > 0) {
            message = args[0];
        }
        byte[] hash = Sha512.getHash(message.getBytes());
        StringBuilder stringBuilder = new StringBuilder("0x");
        for (byte b : hash) {
            stringBuilder.append(String.format("%02x", b));
        }
        System.out.println(stringBuilder);
    }
}
