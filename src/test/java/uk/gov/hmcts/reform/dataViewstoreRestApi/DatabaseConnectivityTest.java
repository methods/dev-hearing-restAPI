package uk.gov.hmcts.reform.dataViewstoreRestApi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class DatabaseConnectivityTest {

    @Autowired
    private DataSource datasource;

    @Test
    public void testDatabaseConnection() throws SQLException {
        // WHEN a connection is made to the database
        Connection connection = datasource.getConnection();

        // THEN the connection should be valid and not NULL
        assertThat(connection).isNotNull();
        assertThat(connection.isValid(1)).isTrue();
        connection.close();
    }
}
