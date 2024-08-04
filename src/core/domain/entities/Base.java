package core.domain.entities;

public abstract class Base {
    private int id;

    public Base() {
    }

    public Base(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
