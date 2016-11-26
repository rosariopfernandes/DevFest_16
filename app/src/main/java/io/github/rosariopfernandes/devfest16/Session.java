package io.github.rosariopfernandes.devfest16;

public class Session {
    private String startTime;
    private String sessionName;
    private String uid;

    public Session()
    {

    }

    public Session(String startTime, String sessionName, String uid)
    {
        this.startTime = startTime;
        this.sessionName = sessionName;
        this.uid = uid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Session{" +
                "startTime='" + startTime + '\'' +
                ", sessionName='" + sessionName + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
