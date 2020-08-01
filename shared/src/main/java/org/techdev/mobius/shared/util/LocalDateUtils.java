package org.techdev.mobius.shared.util;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 10/2/2019
 *
 * PRO: Clase de utilidad p/fecha y tiempo
 * OBS: Se usa la librería externa threetenabp en vez de java.time
 * Fuentes:
 *  . https://stackoverflow.com/a/54800223/5279996
 *  . https://stackoverflow.com/a/38922755/5279996
 */
public class LocalDateUtils {

    private static final String API_DATE_PATTERN = "yyyy-MM-dd";
    private static final String FILE_NAME_PATTERN = "yyyyMMdd_HHmmss";
    public static final LocalDate EMPTY_FIELDS = createLocalDate(0);
    public static final LocalDate EMPTY_FIELD_DAY_OF_MONTH = createLocalDate(1);
    public static final LocalDate EMPTY_FIELD_MONTH = createLocalDate(2);
    public static final LocalDate EMPTY_FIELD_YEAR = createLocalDate(3);

    private LocalDateUtils() {
    }

    /**
     * PRO: Crea una fecha de época con @param epochDay días agregados
     * OBS: EPOCH (1970-01-01) es una fecha de referencia de épocas para Unix y Java
     */
    private static LocalDate createLocalDate(long epochDay) {
        return LocalDate.ofEpochDay(epochDay);
    }

    /**
     * PRO: Describe una fecha de cumpleaños con un formato ajustado al patrón deseado.
     *
     */
    public static String formatBirthdateForApi(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                API_DATE_PATTERN, Locale.getDefault()
        );
        return simpleDateFormat.format(date);
    }

    /**
     * PRO: Describe una fecha para el nombre de un archivo ajustado al patrón deseado
     * Fuente:
     *  . https://stackoverflow.com/a/28178034/5279996
     */
    public static String formatDateForFileName(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FILE_NAME_PATTERN);
        return localDateTime.format(dateTimeFormatter);
    }

}
