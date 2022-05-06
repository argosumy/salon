package spdu2022.java.project.beutysalon.entities;

public class EMail {
    private User toUser;
    private String from;
    private String subject;
    private String content;

    public EMail() {
    }

    public EMail(User toUser, String from, String subject, String content) {
        this.toUser = toUser;
        this.from = from;
        this.subject = subject;
        this.content = content;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
