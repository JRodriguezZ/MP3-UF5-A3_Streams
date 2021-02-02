import Model.Alumne;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class DadesManager {

    final String PATH = "https://srv-store5.gofile.io/download/lXHjGO/alumnespuigm05.csv";

    List<Alumne> listBBDD = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);
    int seleccio;

    public void menuPrincipal() {
        do {
            System.out.println("1. Observar llista completa");
            System.out.println("2. Filtres");
            System.out.println("3. Exit");

            seleccio = scanner.nextInt();

            switch (seleccio) {
                case 1 -> visualitzar();
                case 2 -> filtresMenu();
                case 3 -> System.out.println("Gracies per utilitzar la base de dades d'alumnes del Puig Castellar.");
                default -> System.out.println("Valor introducido incorrecto.");
            }
        } while (seleccio != 3);
    }

    public void filtresMenu() {
        do {
            System.out.println();
            System.out.println("1. Alumnes del subgrup A");
            System.out.println("2. Alumnes del subgrup B");
            System.out.println("3. Buscar un alumne pel cognom");
            System.out.println("4. Nombre d'alumnes");
            System.out.println("5. Ordenar alumnes d'un subgrup per nom ");
            System.out.println("6. Alumnes amb numero al correu");
            System.out.println("7. Return");

            seleccio = scanner.nextInt();

            switch (seleccio) {
                case 1 -> alumnesSubGrupA();
                case 2 -> alumnesSubGrupB();
                case 3 -> cercaCognom();
                case 4 -> nombreAlumnes();
                case 5 -> ordenarSubgrupsPerNom();
                case 6 -> alumnesAmb0Correu();
                case 7 -> System.out.println();
                default -> System.out.println("Valor introducido incorrecto.");
            }
        } while (seleccio != 7);
    }

    private void alumnesAmb0Correu() {
        List<Alumne> list= listBBDD.stream()
                .filter(alumne -> alumne.getCORREU().contains("0") )
                .collect(Collectors.toList());

        list.forEach(System.out::println);
    }

    public void ordenarSubgrupsPerNom() {

        System.out.println("Introdueix el subgrup a cercar: ");
        System.out.println("A. Subgrup A ");
        System.out.println("B. Subgrup B ");

        String sub = scanner.next();

        String subgrupSeleccionat = "";
        do {
            switch (sub) {
                case "A" -> subgrupSeleccionat = "A";
                case "a" -> subgrupSeleccionat = "A";
                case "B" -> subgrupSeleccionat = "B";
                case "b" -> subgrupSeleccionat = "B";
                default -> System.out.println("Valor introducido incorrecto.");
            }
        } while (!sub.equals("a") && !sub.equals("b"));

        String finalSubgrupSeleccionat = subgrupSeleccionat;

        List<Alumne> listCorreus = listBBDD.stream()
                .filter(alumne -> alumne.getSUB_GRUP().equals(finalSubgrupSeleccionat))
                .sorted(Comparator.comparing(Alumne::getNOM))
                .collect(Collectors.toList());

        listCorreus.forEach(System.out::println);
    }

    public void nombreAlumnes() {

        Long nombreAlumnes = listBBDD.stream().count();

        System.out.print("Nombre d'alumnes: ");
        System.out.println(nombreAlumnes);
    }

    public void cercaCognom() {

        System.out.println("Introdueix el cognom a cercar: ");

        String cognom = scanner.next();

        List<Alumne> listCognom = listBBDD.stream()
                .filter(alumne -> alumne.getCOGNOM().equals(cognom)).collect(Collectors.toList());

        listCognom.forEach(System.out::println);
    }

    public void alumnesSubGrupB() {

        List<Alumne> listAlumnesA = listBBDD.stream()
                .filter(alumne -> alumne.getSUB_GRUP().equals("B")).collect(Collectors.toList());

        listAlumnesA.forEach(System.out::println);
    }

    public void alumnesSubGrupA() {

        List<Alumne> listAlumnesA = listBBDD.stream()
                .filter(alumne -> alumne.getSUB_GRUP().equals("A")).collect(Collectors.toList());

        listAlumnesA.forEach(System.out::println);
    }

    public void visualitzar() {

        listBBDD.forEach(System.out::println);
        System.out.println();

    }

    public void getDades() {
        URL url = null;

        BufferedReader br = null;
        CSVReader reader = null;
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
            listBBDD.add(alumne);
        }
    }

}
