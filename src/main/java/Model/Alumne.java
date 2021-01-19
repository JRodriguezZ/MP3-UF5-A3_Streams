package Model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Alumne {
    @CsvBindByName
    String nom;
    @CsvBindByName
    String cognom;
    @CsvBindByName
    String cognom2;
    @CsvBindByName
    String grup;
    @CsvBindByName
    String subGrup;
    @CsvBindByName
    int telefon;
    @CsvBindByName
    String correu;
}