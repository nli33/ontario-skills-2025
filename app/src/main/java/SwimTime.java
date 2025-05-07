

public class SwimTime {
    private int minutes = 0;
    private int seconds = 0;
    private int centiseconds = 0;
    private boolean isDQ = false, isNS = false;

    public SwimTime(int minutes, int seconds, int centiseconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.centiseconds = centiseconds;
    }

    public SwimTime(boolean isDQ, boolean isNS) {
        this.isDQ = isDQ;
        this.isNS = isNS;
    }

    public SwimTime(String resultStr) {
        switch (resultStr) {
        case "isDQ":
            this.isDQ = true;
            break;
        case "isNS":
            this.isNS = true;
            break;
        default:
            int minutes = 0, seconds = 0, centiseconds = 0;
            String[] secondsSplit = new String[0];
            String[] minutesSplit = resultStr.split(":");

            if (minutesSplit.length > 1) {
                minutes = Integer.parseInt(minutesSplit[0]);
                secondsSplit = minutesSplit[1].split("\\.");
            } else if (minutesSplit.length == 1) {
                minutes = 0;
                secondsSplit = minutesSplit[0].split("\\.");
            }

            if (secondsSplit.length > 1) {
                seconds = Integer.parseInt(secondsSplit[0]);
                centiseconds = Integer.parseInt(secondsSplit[1]);
            }

            this.minutes = minutes;
            this.seconds = seconds;
            this.centiseconds = centiseconds;
        }
    }

    @Override
    public String toString() {
        if (this.minutes != 0) {
            return this.minutes + ":" + this.seconds + "." + Time.padZeroes(this.centiseconds);
        } else {
            return this.seconds + "." + Time.padZeroes(this.centiseconds);
        }
    }

    public int getTotal() {
        return 60 * minutes + seconds;
    }
}
