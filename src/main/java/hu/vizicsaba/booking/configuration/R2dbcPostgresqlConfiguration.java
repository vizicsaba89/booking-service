package hu.vizicsaba.booking.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.time.Duration;

@Configuration
@EnableR2dbcRepositories
public class R2dbcPostgresqlConfiguration extends AbstractR2dbcConfiguration {

    @Value("${configuration.datasource.host}")
    private String host;
    @Value("${configuration.datasource.port}")
    private int port;
    @Value("${configuration.datasource.database}")
    private String database;
    @Value("${configuration.datasource.username}")
    private String username;
    @Value("${configuration.datasource.password}")
    private String password;

    @Bean
    @Override
    public PostgresqlConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
            .builder()
            .host(host)
            .database(database)
            .username(username)
            .password(password)
            .port(port)
            .connectTimeout(Duration.ofMinutes(10))
            .build());
    }

}

