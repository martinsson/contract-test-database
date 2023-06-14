import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.assertj.core.api.Assertions;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDbNotesRepositoryTest {
    NotesRepository repository;

    MongoClient mongoClient;
    @BeforeEach
    void setUp() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        mongoClient = MongoClients.create("mongodb://localhost");
        MongoDatabase database = mongoClient.getDatabase("sample_mflix").withCodecRegistry(pojoCodecRegistry);


        database.getCollection("movies", NoteEntity.class).drop();

        repository = new MongoDbNotesRepository(database);

    }
    @Test
    void can_create() {
        String noteId = "noteId";
        NoteEntity note = new NoteEntity(noteId, "barack@obama.org", "note content");

        repository.saveEntity(note);

        NoteEntity result = repository.findById(noteId);
        NoteEntity expected = new NoteEntity(noteId, "barack@obama.org", "note content");
        Assertions.assertThat(result).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    void list_by_author_lists_only_notes_by_author() {
        var me = "me@me.fr";
        var other = "other@other.fr";
        createAndSaveSomeNoteBy(me);
        createAndSaveSomeNoteBy(me);
        createAndSaveSomeNoteBy(other);

        Assertions.assertThat(getNotesBy(me)).hasSize(2);
        Assertions.assertThat(getNotesBy(other)).hasSize(1);
    }

    private List<NoteEntity> getNotesBy(String author) {
        return repository.findByAuthor(author);
    }

    private void createAndSaveSomeNoteBy(String author) {

        NoteEntity entity = new NoteEntity(
                UUID.randomUUID().toString(),
                author,
                "some content"
        );
        repository.saveEntity(entity);

    }

    @AfterEach
    void tearDown() {
        mongoClient.close();
    }

}
