package Model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Alumne {

    @CsvBindByName
    String NOM;

    @CsvBindByName
    String COGNOM;

    @CsvBindByName
    String SEGON_COGNOM;

    @CsvBindByName
    String GRUP;

    @CsvBindByName
    String SUB_GRUP;

    @CsvBindByName
    int TELEFON;

    @CsvBindByName
    String CORREU;

}