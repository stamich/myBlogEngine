package pl.codecity.perun.file.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public final class Utils {
    /**
     * Constructor
     */
    private Utils() {

    }

    /**
     * Function for closing
     * @param closeables Object of closeable
     */
    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (Exception ignore) {

            }
        }
    }

    /**
     * Function converting object to byte array
     * @param object Given object
     * @param <T> Template
     * @return Result as byte array
     * @throws IOException
     */
    public static <T extends Serializable> byte[] toByteArray(T object) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(result);

        try {
            oos.writeObject(object);
            return result.toByteArray();
        } finally {
            Utils.close(result, oos);
        }
    }

    /**
     * Function converting object to json from string
     * @param object Given object
     * @param <T> Template
     * @return Result as string
     * @throws IOException
     */
    public static <T> String toJsonString(T object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(object);
        return result;
    }

    /**
     * Function converting object from json to string
     * @param clazz Given class
     * @param value Given value
     * @param <T> Template
     * @return Result as json
     * @throws IOException
     */
    public static <T> T fromJsonString(Class<T> clazz, String value) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T result = mapper.readValue(value, clazz);
        return result;
    }

    /**
     * Function for reading serializable
     * @param stream Stream of bytes
     * @param <T> Template
     * @return Read object class
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T readSerializable(byte[] stream) throws IOException, ClassNotFoundException {
        ByteArrayInputStream source = new ByteArrayInputStream(stream);
        ObjectInputStream ois = new ObjectInputStream(source);

        try {
            return (T) ois.readObject();
        } finally {
            Utils.close(source, ois);
        }
    }

    /**
     * Cast class
     * @param aClass Given class
     * @param <T> Template
     * @return Class object
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> castClass(Class<?> aClass) {
        return (Class<T>) aClass;
    }

    /**
     * Function reading resource text file
     * @param source Given resource file
     * @param delimeter String delimeter
     * @param provider Reader provider
     * @throws IOException
     */
    public static void readResourceTextFile(InputStream source, String delimeter, ReaderProvider provider) throws IOException {
        InputStreamReader isr = null;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(isr = new InputStreamReader(source));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(delimeter);
                provider.read(tokens);
            }
        } finally {
            close(isr, reader);
        }
    }

    /**
     * Interface for reader provider {@link ReaderProvider}
     */
    public static interface ReaderProvider {
        void read(String[] line);
    }

    /**
     * Function checking if candidate is in collection
     * @param candidate Given candidate
     * @param inCollection Given collection
     * @param <T> Template
     * @return True if it is, false if not
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean isIn(T candidate, T... inCollection) {
        for (T c : inCollection) {
            if (ObjectUtils.equals(c, candidate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Field containing week of month
     */
    private static TemporalField weekOfMonthField = WeekFields.of(Locale.getDefault()).weekOfMonth();

    /**
     * Field containing week of year
     */
    private static TemporalField weekOfYearField = WeekFields.of(Locale.getDefault()).weekOfYear();

    /**
     * Getter for week of month
     * @param date Given date
     * @return Week of month for given date
     */
    public static int getWeekOfMonth(LocalDate date) {
        return date.get(weekOfMonthField);
    }

    /**
     * Getter for calendar week of month
     * @param date Given date
     * @return Difference in time between first week of month and last week of month
     */
    public static int getCalendarWeekOfMonth(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = date;
        int firstWeekOfMonth = firstDayOfMonth.get(weekOfYearField);
        int lastWeekOfMonth = lastDayOfMonth.get(weekOfYearField);
        // int week = (int) Math.ceil(date.getDayOfMonth() / (float) 7);
        return (lastWeekOfMonth - firstWeekOfMonth) + 1;
    }

    /**
     * Gets week of month by full week days
     * @param date Given date
     * @return Number of calculated week
     */
    public static int getWeekOfMonthByFullWeekDays(LocalDate date) {
        int week = (int) Math.ceil(date.getDayOfMonth() / (float) 7);
        return week;
    }

    /**
     * Gets weeks in month
     * @param date Given date
     * @return Amount of weeks in current month
     */
    public static int getWeeksInMonth(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());
        int firstWeekOfMonth = firstDayOfMonth.get(weekOfYearField);
        int lastWeekOfMonth = lastDayOfMonth.get(weekOfYearField);

        return (lastWeekOfMonth - firstWeekOfMonth) + 1;
    }

    /**
     * Gets months between two given dates
     * @param from Starting date
     * @param to Ending date
     * @return Difference in time
     */
    public static long getMonthsBetween(LocalDate from, LocalDate to) {
        long difference = ChronoUnit.MONTHS.between(from, to);
        if (from.getDayOfMonth() > to.getDayOfMonth()) {
            difference++;
        }
        return difference;
    }

    /**
     * Matches local date to week day
     * @param date Given local date
     * @param targetDayOfWeeks Day of week
     * @return Matching result
     */
    public static LocalDate matchToWeekDay(LocalDate date, DayOfWeek... targetDayOfWeeks) {
        LocalDate result = date;
        int difference = 8;
        for (DayOfWeek targetDayOfWeek : targetDayOfWeeks) {
            int tmp = targetDayOfWeek.getValue() - result.getDayOfWeek().getValue();
            if (tmp <= 0) {
                tmp = 7 + tmp;
            }
            difference = Math.min(difference, tmp);
        }
        if (difference > 0) {
            result = result.plusDays(difference);
        }
        return result;
    }

    /**
     * Maps given month recurrence type to day of week
     * @param monthRecurenceType Given month recurrence type
     * @return Matched day of week
     */
    @SuppressWarnings("incomplete-switch")
    public static DayOfWeek mapToDayOfWeek(MonthRecurenceType monthRecurenceType) {
        switch (monthRecurenceType) {
            case MONDAY:
                return DayOfWeek.MONDAY;
            case TUESDAY:
                return DayOfWeek.TUESDAY;
            case WEDNESDAY:
                return DayOfWeek.WEDNESDAY;
            case THURSDAY:
                return DayOfWeek.THURSDAY;
            case FRIDAY:
                return DayOfWeek.FRIDAY;
            case SATURDAY:
                return DayOfWeek.SATURDAY;
            case SUNDAY:
                return DayOfWeek.SUNDAY;
        }
        return null;
    }

    /**
     * Main function
     * @param args Array of main parameters
     */
    public static void main(String[] args) {
        // System.out.println(toHex(Color.BLUE));
    }

    /**
     * Converts given values to hex
     * @param r int value
     * @param g int value
     * @param b int vllue
     * @return Converted values
     */
    public static String toHex(int r, int g, int b) {
        return "#" + toBrowserHexValue(r) + toBrowserHexValue(g) + toBrowserHexValue(b);
    }

    /**
     * Converts given color to hex
     * @param color Given color
     * @return Converted color in hex
     */
    public static String toHex(Color color) {
        return "#" + toBrowserHexValue(color.getRed()) + toBrowserHexValue(color.getGreen()) + toBrowserHexValue(color.getBlue());
    }

    /**
     * Converts number to browser hex value
     * @param number Given number
     * @return Number in browser hex uppercase
     */
    private static String toBrowserHexValue(int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }

    /**
     * Gets string from source excluding given string
     * @param source Given source
     * @param exclude Given exlude string
     * @return String witout excluded value
     */
    public static String getStringExcluding(String source, String exclude) {
        if (StringUtils.isEmpty(source)) {
            source += "";
        }

        return source.replaceAll(exclude, "");
    }

    /**
     * Gets local date
     * @param dateTo Give date
     * @return Given date plus one day
     */
    public static LocalDate getDateTo(LocalDate dateTo) {
        if(dateTo == null) {
            return dateTo;
        }
        return dateTo.plusDays(1);
    }
}
