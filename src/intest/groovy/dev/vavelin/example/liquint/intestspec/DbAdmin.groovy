package dev.vavelin.example.liquint.intestspec

import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
@Profile("INTEST")
class DbAdmin {
    private JdbcTemplate jdbcTemplate

    private static final String cleanDbTablesResourceName = "db/runtime-sql/clean-db-tables.sql"

    private static final String[] cleanDbSqlCache = loadFile(cleanDbTablesResourceName)

    DbAdmin(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }

    void deleteAllData() {
        runSqlFile(cleanDbTablesResourceName)
    }

    void runSqlFile(String resourceName) {
        def queries = getSqlQueries(resourceName)
        if (queries) {
            jdbcTemplate.batchUpdate(*queries)
        }
    }

    private static String[] getSqlQueries(String resourceName) {
        return resourceName == cleanDbTablesResourceName ?
                cleanDbSqlCache :
                loadFile(resourceName)
    }

    private static String[] loadFile(String resourceName) {
        def inputStream = DbAdmin.class.getResourceAsStream("/$resourceName")

        return inputStream.readLines().findAll { !it.isEmpty() && !it.startsWith("--") }
                .stream()
                .map { it.trim() }
                .map { it.replace(";", "") }
                .toArray()
    }
}