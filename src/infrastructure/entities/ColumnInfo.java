package infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColumnInfo extends BaseInfo {

    @JsonProperty("type")
    private String type;
    @JsonProperty("isPrimaryKey")
    private boolean isPrimaryKey;
    @JsonProperty("isAutoIncrement")
    private boolean isAutoIncrement;
    @JsonProperty("isForeignKey")
    private boolean isForeignKey;
    @JsonProperty("isNullable")
    private boolean isNullable;
    @JsonProperty("defaultValue")
    private String defaultValue;

    public ColumnInfo() {
    }

    public ColumnInfo(String columnName, String type, boolean isPrimaryKey, boolean isForeignKey, boolean isNullable, boolean isAutoIncrement, String defaultValue) {
        super(columnName);
        this.setType(type);
        this.setPrimaryKey(isPrimaryKey);
        this.setIsAutoIncrement(isAutoIncrement);
        this.setForeignKey(isForeignKey);
        this.setNullable(isNullable);
        this.setDefaultValue(defaultValue);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    public boolean isForeignKey() {
        return isForeignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        isForeignKey = foreignKey;
    }


    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
