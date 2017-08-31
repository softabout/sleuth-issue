package de.softabout.sleuthissue;

import com.mongodb.ConnectionString;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.connection.*;
import com.mongodb.connection.netty.NettyStreamFactoryFactory;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PreDestroy;

@Configuration
@ConditionalOnClass(MongoClient.class)
@EnableConfigurationProperties(MongoProperties.class)
public class MongoConfig {

    private final MongoProperties properties;
    private final Environment environment;
    private MongoClient mongo;

    public MongoConfig(MongoProperties properties, Environment environment) {
        this.properties = properties;
        this.environment = environment;
    }

    @PreDestroy
    @ConditionalOnMissingBean
    public void close() {
        if (this.mongo != null) {
            this.mongo.close();
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public MongoClient reactiveStreamingMongoClient() {
        ConnectionString connectionString = new ConnectionString(this.properties.determineUri());
        MongoClientSettings.Builder builder = MongoClientSettings.builder()
                .clusterSettings(ClusterSettings.builder().applyConnectionString(connectionString).build())
                .connectionPoolSettings((ConnectionPoolSettings.builder()
                .applyConnectionString(connectionString).build()))
                .serverSettings(ServerSettings.builder()
                .applyConnectionString(connectionString).build())
                .credentialList(connectionString.getCredentialList())
                .sslSettings(SslSettings.builder().applyConnectionString(connectionString).build())
                .streamFactoryFactory(NettyStreamFactoryFactory.builder().build())
                .socketSettings(SocketSettings.builder().applyConnectionString(connectionString).build());
        this.mongo = MongoClients.create(builder.build());
        return this.mongo;
    }
}
