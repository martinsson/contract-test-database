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

import java.util.UUID;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class NotesRepositoryMongoDbTest {
    NotesRepository repository;

    MongoClient mongoClient;
    @BeforeEach
    void setUp() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        mongoClient = MongoClients.create("mongodb://localhost");
        MongoDatabase database = mongoClient.getDatabase("contract_testing").withCodecRegistry(pojoCodecRegistry);


        database.getCollection("notes", NoteEntity.class).drop();

        repository = new MongoDbNotesRepository(database);

    }

    @AfterEach
    void tearDown() {
        mongoClient.close();
    }

    @Test
    void can_create() {
        String noteId = "noteId";
        NoteEntity note = new NoteEntity(noteId, "barack@obama.org", "note content");

        repository.saveNote(note);

        NoteEntity result = repository.findById(noteId);
        NoteEntity expected = new NoteEntity(noteId, "barack@obama.org", "note content");

        Assertions.assertThat(result).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    void find_by_id_restitutes_the_right_note() {
        String noteId = "noteId";
        NoteEntity note = new NoteEntity(noteId, "barack@obama.org", "note content");
        NoteEntity note2 = new NoteEntity("otherid", "francois@hollande.fr", "note in french");

        repository.saveNote(note2);
        repository.saveNote(note);

        NoteEntity result = repository.findById("otherid");
        NoteEntity expected = new NoteEntity("otherid", "francois@hollande.fr", "note in french");

        Assertions.assertThat(result).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    void find_by_id_returns_no_notes_when_id_is_unkown() {
        repository.saveNote(new NoteEntity("existing note", "whatever", "whatever"));
        NoteEntity result = repository.findById("unknown id");
        Assertions.assertThat(result).isNull();
    }

}
