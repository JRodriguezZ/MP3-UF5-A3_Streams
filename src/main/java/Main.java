import Model.Alumne;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    static final String PATH = "https://srv-store1.gofile.io/download/BdjfJQ/alumnespuigm05.csv";

    static Scanner scanner = new Scanner(System.in);
    static int seleccio;

    static URL url = null;

    static BufferedReader br = null;
    static CSVReader reader = null;

    public static void main(String[] args) {

        System.out.println("BENVINGUT A LA BASE DE DADES D'ALUMNES DE 2N DE DAM");
        do {
            System.out.println("1. Observar");
            System.out.println("2. Cerca");
            System.out.println("3. Exit");

            seleccio = scanner.nextInt();

            switch (seleccio) {
                case 1 -> visualitzar();
                case 2 -> cerca();
                case 3 -> System.out.println("Gracias por utilizar el TPV de la cantina.");
                default -> System.out.println("Valor introducido incorrecto.");
            }
        } while (seleccio != 3);
    }

    private static void cerca() {
        do {
            System.out.println("1. Alumnes del subgrup A");
            System.out.println("2. Alumnes del subgrup B");
            System.out.println("3. Return");

            seleccio = scanner.nextInt();

            switch (seleccio) {
                case 1 -> alumnesSubGrupA();
                case 2 -> cerca();
                case 3 -> System.out.println();
                default -> System.out.println("Valor introducido incorrecto.");
            }
        } while (seleccio != 3);
    }

    private static void alumnesSubGrupA() {
        try {
            url = new URL(PATH);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            reader = new CSVReader(br);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CsvToBean<Alumne> alumneCsvToBean = new CsvToBeanBuilder(reader)
                .withFilter(strings -> )

    }

    private static void visualitzar() {
        try {
            url = new URL(PATH);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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

        Iterator<Alumne> csvIterator = alumneCsvToBean.iterator();
        while (csvIterator.hasNext()) {
            Alumne alumne = csvIterator.next();
            System.out.println(alumne);
        }
        System.out.println();

    }
}
