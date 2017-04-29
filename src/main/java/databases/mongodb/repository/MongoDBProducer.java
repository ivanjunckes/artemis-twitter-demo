package databases.mongodb.repository;


import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.mongodb.document.MongoDBDocumentCollectionManagerFactory;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MongoDBProducer {

    private static final String COLLECTION = "developers";

    private MongoDBDocumentConfiguration configuration;

    private MongoDBDocumentCollectionManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        configuration = new MongoDBDocumentConfiguration();
        managerFactory = configuration.get();
    }


    @Produces
    @Database(value = DatabaseType.DOCUMENT, provider = "mongoDb")
    public DocumentCollectionManager getManager() {
        return managerFactory.get(COLLECTION);

    }

}
