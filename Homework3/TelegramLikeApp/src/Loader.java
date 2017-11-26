import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.AuthSentCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TelegramApiBridge bridge = new TelegramApiBridge("149.154.167.50:443", 156664, "16af814783a639dad88385f4cc2ddea1");
    //    System.out.println("Please type phone number to check status: ");
    //    AuthCheckedPhone checkedPhone = bridge.authCheckPhone(reader.readLine().trim());
    //    System.out.println(checkedPhone.isRegistered());
        System.out.println("Please enter telephone number: ");
        String phoneNumber = reader.readLine().trim();
        AuthCheckedPhone checkedPhone = bridge.authCheckPhone(phoneNumber);
        if(checkedPhone.isRegistered()) {
            bridge.authSendCode(phoneNumber);
            System.out.println("Enter activation code: ");
            AuthAuthorization auth = bridge.authSignIn(reader.readLine().trim());
            System.out.println("Registered user is: " + auth.getUser());
        }
    }
}
