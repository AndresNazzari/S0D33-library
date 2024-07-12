package infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BaseInfo {
    @JsonProperty("name")
    private String name;

    public BaseInfo() {
    }

    public BaseInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
