package TODOList.models;


import java.sql.Timestamp;

public class Task {
    private int id;
    private int listId;
    private String name;
    private String comment;
    private int priority;
    private Timestamp date;
    private int repeatTime;
    private boolean done;

    public Task() {
    }

    public Task(int id, int listId, String name, String comment, int priority, Timestamp date, int repeatTime, boolean done) {
        this.id = id;
        this.listId = listId;
        this.name = name;
        this.comment = comment;
        this.priority = priority;
        this.date = date;
        this.repeatTime = repeatTime;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(int repeatTime) {
        this.repeatTime = repeatTime;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}

