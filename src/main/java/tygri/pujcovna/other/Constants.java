package tygri.pujcovna.other;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    private String carPreview;

    public Constants() {
        try {
            carPreview = String.join("", Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\webapp\\components\\carPreview.jsp")));
        } catch (IOException e) {
            System.out.println("Couldnt load carPreview.jsp");
        }
    }

    public String getCarPreview() {
        return carPreview;
    }

}
