package constants;

import generator.Generator;
import utils.FileUtils;

/**
 * Static access to XML parts for generating final outcome of this application
 * using {@link Generator}.
 */
public class XmlParts {

	private XmlParts() {
		// only static methods and constants
	}

	// Replacements
	protected static final String REPLACE_CHAGESETS = "$CHANGESETS$";
	protected static final String REPLACE_AUTHOR = "$AUTHOR$";
	protected static final String REPLACE_ID = "$ID$";
	protected static final String REPLACE_TABLE_NAME = "$TABLE_NAME$";
	protected static final String REPLACE_TABLE_NAME_LOWER_CASE = "$TABLE_NAME_LOWER_CASE$";
	protected static final String REPLACE_COLUMN_PRIMARY_KEY_NAME = "$COLUMN_PK_NAME$";
	protected static final String REPLACE_CONSTRAIN_PRIMARY_KEY_NAME = "$CONSTRAIN_PK_NAME$";
	protected static final String REPLACE_SEQUENCE_NAME = "$SEQUENCE_NAME$";
	protected static final String REPLACE_COLUMN_NAME = "$COLUMN_NAME$";
	protected static final String REPLACE_COLUMN_NAME_UPPER_CASE = "$COLUMN_NAME_UPPER_CASE$";
	protected static final String REPLACE_COLUMN_DATA_TYPE = "$COLUMN_TYPE$";
	protected static final String REPLACE_COLUMN_FOREIGN_KEY_NAME = "$COLUMN_FK_NAME$";
	protected static final String REPLACE_COLUMN_FOREIGN_KEY_REFERECED_TABLE_NAME = "$REFERENCED_TABLE_NAME$";
	protected static final String REPLACE_COLUMN_FOREIGN_KEY_REFERECED_COLUMN_NAME = "$REFERENCED_COLUMN_NAME$";
	protected static final String REPLACE_COLUMN_CONSTRAINS = "$COLUMN_CONSTRAINS$";
	protected static final String REPLACE_COLUMN_INDEX_NAME = "$INDEX_NAME$";
	protected static final String REPLACE_COLUMN_FOREIGN_KEY = "$COLUMN_FOREIGN_KEY$";
	protected static final String REPLACE_COLUMN_NULLABLE = "$NULLABLE$";
	protected static final String REPLACE_COLUMN_NULLABLE_VALUE = "$NULLABLE_VALUE$";

	// Paths to templates
	private static final String CHANGELOG_START_TEMPLATE = "/generator/shared/ChangelogStart.txt";
	private static final String CHANGESETS_START_TEMPLATE = "/generator/shared/ChangesetsStart.txt";

	private static final String CREATE_TABLE_MSSQL_TEMPLATE = "/generator/createtable/CreateTableMssql.txt";
	private static final String CREATE_TABLE_ORACLE_POSTGRE_TEMPLATE = "/generator/createtable/CreateTableOraclePostgre.txt";
	private static final String CREATE_TABLE_SEQUENCE_POSTGRE_TEMPLATE = "/generator/createtable/CreateTableSequencePostgre.txt";
	private static final String CREATE_TABLE_SEQUENCE_ORACLE_TEMPLATE = "/generator/createtable/CreateTableSequenceOracle.txt";

	private static final String COLUMN_INDEX_MSSQL_POSTGRE_TEMPLATE = "/generator/shared/CreateIndexMssqlPostgre.txt";
	private static final String COLUMN_INDEX_ORACLE_TEMPLATE = "/generator/shared/CreateIndexOracle.txt";

	private static final String ADD_COLUMN_TEMPLATE = "/generator/addcolumn/AddColumn.txt";
	private static final String ADD_COLUMN_BOOLEAN_ORACLE_MSSQL_TEMPLATE = "/generator/addcolumn/AddColumnBooleanOracleMssql.txt";
	private static final String ADD_COLUMN_BOOLEAN_POSTGRE_TEMPLATE = "/generator/addcolumn/AddColumnBooleanPostgre.txt";
	private static final String ADD_COLUMN_CONSTRAINTS_TEMPLATE = "/generator/addcolumn/AddColumnConstraints.txt";
	private static final String ADD_COLUMN_CONSTRAINTS_FOREIGN_KEY_TEMPLATE = "/generator/addcolumn/AddColumnConstraintsForeignKey.txt";
	private static final String ADD_COLUMN_CONSTRAINTS_NULLABLE_TEMPLATE = "/generator/addcolumn/AddColumnConstraintsNullable.txt";

	private static final String REMOVE_NOT_NULL_CONSTRAINT_ORACLE_TEMPLATE = "/generator/removenotnullconstraint/RemoveNotNullConstraintOracle.txt";
	private static final String REMOVE_NOT_NULL_CONSTRAINT_MSSQL_TEMPLATE = "/generator/removenotnullconstraint/RemoveNotNullConstraintMssql.txt";
	private static final String REMOVE_NOT_NULL_CONSTRAINT_POSTGRE_TEMPLATE = "/generator/removenotnullconstraint/RemoveNotNullConstraintPostgre.txt";

	protected static String getChangelogStart() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CHANGELOG_START_TEMPLATE);
	}

	protected static String getChangesetsStart() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CHANGESETS_START_TEMPLATE);
	}

	protected static String getCreateTableMssql() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_MSSQL_TEMPLATE);
	}

	protected static String getCreateTableOraclePostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_ORACLE_POSTGRE_TEMPLATE);
	}

	protected static String getCreateTableSequenceOracle() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_SEQUENCE_ORACLE_TEMPLATE);
	}

	protected static String getCreateTableSequencePostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, CREATE_TABLE_SEQUENCE_POSTGRE_TEMPLATE);
	}

	protected static String getColumnIndexMssqlPostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, COLUMN_INDEX_MSSQL_POSTGRE_TEMPLATE);
	}

	protected static String getColumnIndexOracle() {
		return FileUtils.getStringFromFileResource(XmlParts.class, COLUMN_INDEX_ORACLE_TEMPLATE);
	}

	protected static String getGeneralColumnBase() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_TEMPLATE);
	}

	protected static String getBooleanCollumnOracleMssql() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_BOOLEAN_ORACLE_MSSQL_TEMPLATE);
	}

	protected static String getBooleanColumnPostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_BOOLEAN_POSTGRE_TEMPLATE);
	}

	protected static String getRemoveNotNullConstraintOracle() {
		return FileUtils.getStringFromFileResource(XmlParts.class, REMOVE_NOT_NULL_CONSTRAINT_ORACLE_TEMPLATE);
	}

	protected static String getRemoveNotNullConstraintMssql() {
		return FileUtils.getStringFromFileResource(XmlParts.class, REMOVE_NOT_NULL_CONSTRAINT_MSSQL_TEMPLATE);
	}

	protected static String getRemoveNotNullConstraintPostgre() {
		return FileUtils.getStringFromFileResource(XmlParts.class, REMOVE_NOT_NULL_CONSTRAINT_POSTGRE_TEMPLATE);
	}

	protected static String getAddColumnConstraints() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_CONSTRAINTS_TEMPLATE);
	}

	protected static String getAddColumnConstraintsForeignKey() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_CONSTRAINTS_FOREIGN_KEY_TEMPLATE);
	}

	protected static String getAddColumnConstraintsNullable() {
		return FileUtils.getStringFromFileResource(XmlParts.class, ADD_COLUMN_CONSTRAINTS_NULLABLE_TEMPLATE);
	}

}
