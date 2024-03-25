package ara.main.Config;

import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class GeneratorId {
    public String IdGenerator(){
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();
        StringBuilder cadena= new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char randomChar = (char) ('A' + random.nextInt(26));
            int randomNumber = random.nextInt(10);
            cadena.append(String.valueOf(randomNumber)).append(randomChar);
        }
        return cadena.toString();
    }
}
