package pl.codecity.perun.file.helper;

import java.time.DayOfWeek;

public enum MonthRecurenceType {
    /**
     * every day
     */
    DAY(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),

    /**
     * monday to friday
     */
    WEEKDAY(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY),

    /**
     * saturday, sunday
     */
    WEEKEND_DAY(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),

    /**
     * tuesday, thursday, saturday
     */
    EVEN_DAY(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SATURDAY),

    /**
     * monday, wednesday, friday, sunday
     */
    ODD_DAY(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY, DayOfWeek.SUNDAY),

    MONDAY(DayOfWeek.MONDAY),

    TUESDAY(DayOfWeek.TUESDAY),

    WEDNESDAY(DayOfWeek.WEDNESDAY),

    THURSDAY(DayOfWeek.THURSDAY),

    FRIDAY(DayOfWeek.FRIDAY),

    SATURDAY(DayOfWeek.SATURDAY),

    SUNDAY(DayOfWeek.SUNDAY);

    /**
     * Table containing week days
     */
    private DayOfWeek[] dayOfWeeks;

    /**
     * Function assigning given week days table to the local one
     *
     * @param dayOfWeeks given table of week days
     */
    private MonthRecurenceType(DayOfWeek... dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }

    /**
     * Getter for week days table
     *
     * @return week days
     */
    public DayOfWeek[] getDayOfWeeks() {
        return dayOfWeeks;
    }
}
