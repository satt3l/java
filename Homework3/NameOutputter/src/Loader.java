import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] name = reader.readLine().split("\\s+");
        System.out.println("Фамилия: " + name[0] + "\nИмя: " + name[1] + "\nОтчество: " + name[2]);
    }
}
