package features.java14;

// https://openjdk.java.net/jeps/361
// https://openjdk.java.net/jeps/325
// https://openjdk.java.net/jeps/354
public class SwitchExpressions {

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    // no need for default
    public void printDayNumber(Day day) {
        switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
            case TUESDAY                -> System.out.println(7);
            case THURSDAY, SATURDAY     -> System.out.println(8);
            case WEDNESDAY              -> System.out.println(9);
        }
    }

    // Most switch expressions will have a single expression to the right of the "case L ->" switch label.
    // In the event that a full block is needed, we introduce a new yield statement to yield a value, which becomes the value
    // of the enclosing switch expression.
    public static int dayToNumber(Day day) {
        return switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            default -> {
                String s = day.toString();
                yield s.length();
            }
        };
    }

}
