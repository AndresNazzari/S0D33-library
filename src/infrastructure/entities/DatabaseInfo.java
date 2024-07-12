package infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInfo extends BaseInfo {
    @JsonProperty("tables")
    private List<TableInfo> tables;

    public DatabaseInfo() {
        this.setTables(new ArrayList<>());
    }

    public DatabaseInfo(String databaseName) {
        super(databaseName);
    }

    public List<TableInfo> getTables() {
        return tables;
    }

    public void setTables(List<TableInfo> tables) {
        this.tables = tables;
    }

    public void addTable(TableInfo tables) {
        getTables().add(tables);
    }
}
