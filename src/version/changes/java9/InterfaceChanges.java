package version.changes.java9;

import java.time.LocalDateTime;

public interface InterfaceChanges {
    String systemStatus = "OK";

    String deviceDescription();
    void turnOn();
    void turnOff();
    void reset();
    void notifyUser();

    default String getStatus() {
        return buildStatus();
    }

    default String getDetailedStatus() {
        return buildStatus() +  "--" + systemStatus;
    }

    // interfaces now could contain private and private static methods
    private String buildStatus() {
        return deviceDescription() + "--" + LocalDateTime.now();
    }

    private static String getSystemStatus() {
        return systemStatus;
    }

}
