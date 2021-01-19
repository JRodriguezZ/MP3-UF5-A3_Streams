import Model.Alumne;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    static final String PATH = "https://srv-store5.gofile.io/download/XIm1i4/alumnespuig.csv";

    public static void main(String[] args) {

        URL url = null;
        try {
            url = new URL(PATH);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader br = null;
        CSVReader reader = null;

        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            reader = new CSVReader(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CsvToBean<Alumne> alumneCsvToBean = new CsvToBeanBuilder(reader)
                .withType(Alumne.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        for (Alumne alumne : alumneCsvToBean) {
            System.out.println(alumne);
        }
    }
}
