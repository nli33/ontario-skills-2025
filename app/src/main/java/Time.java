public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Time(Time t) {
        this.hour = t.hour;
        this.minute = t.minute;
        this.second = t.second;
    }

    public Time(String timeStr) {
        String[] timeStrSplit = timeStr.split(":");
        int hour = 8;
        int minute = 0;
        if (timeStrSplit.length == 2) {
            hour = Integer.parseInt(timeStrSplit[0]);
            minute = Integer.parseInt(timeStrSplit[1]);
        }
        this.hour = hour;
        this.minute = minute;
        this.second = 0;
    }

    public static String padZeroes(int num) {
        String numStr = Integer.toString(num);
        int zeroes = 2 - numStr.length();
        return "0".repeat(zeroes) + numStr;
    }

    public Time getTimeAfter(int seconds) {
        int newSecond = this.second + seconds;
        int newMinute = this.minute + newSecond / 60;
        int newHour = this.hour + newMinute / 60;
        newSecond = newSecond % 60;
        newMinute = newMinute % 60;
        newHour = newHour % 24;

        return new Time(newHour, newMinute, newSecond);
    }

    // Round up to the nearest minute
    public String toString() {
        return Time.padZeroes(hour) + ":" + Time.padZeroes(minute + 1);
    }
}
