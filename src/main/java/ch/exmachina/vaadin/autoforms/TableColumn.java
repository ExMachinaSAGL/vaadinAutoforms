package ch.exmachina.vaadin.autoforms;

public class TableColumn {
	private final String columnName;

	private final Class<?> columnType;

	private final Object defaultValue;

	public TableColumn(String columnName, Class<?> columnType, Object defaultValue) {
		this.columnName = columnName;
		this.columnType = columnType;
		this.defaultValue = defaultValue;
	}

	public TableColumn(String columnName, Class<?> columnType) {
		this(columnName, columnType, columnType.isAssignableFrom(String.class) ? "" : null);
	}

	public String getColumnName() {
		return columnName;
	}

	public Class<?> getColumnType() {
		return columnType;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}
}
