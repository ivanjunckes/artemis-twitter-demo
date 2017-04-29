package databases.mongodb.repository;


import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.couchbase.document.CouchbaseDocumentConfiguration;
import org.jnosql.diana.couchbase.document.CouhbaseDocumentCollectionManagerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class CouchDBProducer {

    private static final String COLLECTION = "default";

    private CouchbaseDocumentConfiguration configuration;

    private CouhbaseDocumentCollectionManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        configuration = new CouchbaseDocumentConfiguration();
        managerFactory = configuration.get();
    }


    @Produces
    @Database(value = DatabaseType.DOCUMENT, provider = "couchDb")
    public DocumentCollectionManager getManager() {
        return managerFactory.get(COLLECTION);
    }

}
