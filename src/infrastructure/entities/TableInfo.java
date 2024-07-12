package infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysql.cj.xdevapi.Table;

import java.util.ArrayList;
import java.util.List;

public class TableInfo extends BaseInfo {
    @JsonProperty("columns")
    private List<ColumnInfo> columns;
    @JsonProperty("foreignKeys")
    private List<ForeignKey> foreignKeys;

    public TableInfo() {
        this.setColumns(new ArrayList<>());
        this.foreignKeys = new ArrayList<>();
    }

    public TableInfo(String tableName) {
        super(tableName);
        this.setColumns(new ArrayList<>());
        this.foreignKeys = new ArrayList<>();
    }

    public void setColumns(List<ColumnInfo> columns) {
        this.columns = columns;
    }

    public void addColumn(ColumnInfo column) {
        getColumns().add(column);
    }

    public List<ColumnInfo> getColumns() {
        return columns;
    }

    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<ForeignKey> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    public void addForeignKey(ForeignKey foreignKey) {
        this.foreignKeys.add(foreignKey);
    }
}
