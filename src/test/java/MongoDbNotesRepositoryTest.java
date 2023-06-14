import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class MongoDbNotesRepositoryTest extends NotesRepositoryContractTest {

    MongoClient mongoClient;
    @BeforeEach
    void setUp() {
        mongoClient = MongoClients.create("mongodb://localhost");
        MongoDatabase database = mongoClient.getDatabase("sample_mflix");

        repository = new MongoDbNotesRepository(database);
    }

    @AfterEach
    void tearDown() {
        mongoClient.close();
    }
}
