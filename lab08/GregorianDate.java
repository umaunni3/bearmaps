public class GregorianDate extends Date {

    private static final int[] MONTH_LENGTHS = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int precedingMonthDays = 0;
        for (int m = 1; m < month; m += 1) {
            precedingMonthDays += getMonthLength(m);
        }
        return precedingMonthDays + dayOfMonth;
    }

    public Date nextDate() {
        /** Return the next date, don't change this date **/
        if (dayOfMonth + 1 > getMonthLength(month)) {
            // must switch to the next month
            // check if it's also a new year
            if (month == MONTH_LENGTHS.length) {
                // new date must be in the new year
                return new GregorianDate((year + 1), 1, 1);
            } else {
                // still in the same year
                return new GregorianDate(year, (month + 1), 1);
            }
        } else {
            // still in the same month
            return new GregorianDate(year, month, (dayOfMonth + 1));
        }
    }

    private static int getMonthLength(int m) {
        return MONTH_LENGTHS[m - 1];
    }
}
