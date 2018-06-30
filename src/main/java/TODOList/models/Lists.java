package TODOList.models;

import java.util.List;

public class Lists {
    private int id;
    private int accountId;
    private String name;
    private String colour;
    private int numOrder;
    private boolean showed;
    private List<Task> tasks;

    public Lists() {
    }

    public Lists(int id, int accountId, String name, String colour, int numOrder, boolean showed) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.colour = colour;
        this.numOrder = numOrder;
        this.showed = showed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(int numOrder) {
        this.numOrder = numOrder;
    }

    public boolean getShowed() {
        return showed;
    }

    public void setShowed(boolean showed) {
        this.showed = showed;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
