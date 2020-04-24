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
            carPreview = String.join("", Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\webapp\\components\\carPreview.jsp")));//doesnt work on heroku probably
        } catch (IOException e) {
            carPreview = "<div class=\"col-md-3 \" >\n"
                    + "    <div class=\"cclient\">\n"
                    + "        <a href=\";carProfileLink;\">\n"
                    + "            <h3>;carCompany;<strong>;carModel;</strong></h3>\n"
                    + "            <div class=\"cimage\">\n"
                    + "                <img src=\"data:image/png;base64,;carPhotoData;\" alt=\"Foto auta\" height=\"100\" width=\"100\"/>;\n"
                    + "            </div>\n"
                    + "        </a>\n"
                    + "        <div class=\"cmatter\">\n"
                    + "            <a href=\";carProfileLink;\">\n"
                    + "                <div class=\"row\">\n"
                    + "                    <div class=\"col-sm-5 detailLeftTd\">Pocet sedadel: <b>;carSeatNumber;</b>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"col-sm-7\">Trida: <b>Co to je</b>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "                <div class=\"row\">\n"
                    + "                    <div class=\"col-sm-5 detailLeftTd\">Palivo: <b>Nafta</b>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"col-sm-7\">Prevodovka: <b>Automat</b>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "                <div class=\"btn btn-price rezervovatDetailBtn\">\n"
                    + "                    od <strong>;carPrice; / den</strong>\n"
                    + "                </div>\n"
                    + "            </a>\n"
                    + "            <div class=\"button\">\n"
                    + "                <a href=\";carProfileLink;\">Prohlednout vuz</a>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</div>\n"
                    + "";
            System.out.println("Couldnt load carPreview.jsp");
        }
    }

    public String getCarPreview() {
        return carPreview;
    }

}
