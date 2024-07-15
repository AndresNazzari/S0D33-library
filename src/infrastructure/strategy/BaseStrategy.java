package infrastructure.strategy;

import infrastructure.entities.ColumnInfo;
import infrastructure.entities.ForeignKey;
import infrastructure.entities.TableInfo;

import java.util.List;

public class BaseStrategy {
    public StringBuilder addFk(StringBuilder sql, TableInfo table) {
        List<ForeignKey> foreignKeys = table.getForeignKeys();
        if (!foreignKeys.isEmpty()) {
            sql.append(", ");
            for (int i = 0; i < foreignKeys.size(); i++) {
                ForeignKey fk = foreignKeys.get(i);
                sql.append("FOREIGN KEY (").append(fk.getColumnName()).append(") REFERENCES ")
                        .append(fk.getReferenceTable()).append("(").append(fk.getReferenceColumn()).append(")");

                if (i < foreignKeys.size() - 1) {
                    sql.append(", ");
                }
            }
        }

        return sql;
    }

    public StringBuilder addOptions(StringBuilder sql, ColumnInfo column) {
        if (column.isPrimaryKey()) {
            sql.append(" PRIMARY KEY");
        }

        if (!column.isNullable()) {
            sql.append(" NOT NULL");
        }

        if (column.getDefaultValue() != null) {
            sql.append(" DEFAULT ").append(column.getDefaultValue());
        }
        return sql;
    }
}
