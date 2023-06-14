import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;

public class MongoDbNotesRepository implements NotesRepository {
    private final MongoDatabase database;

    public MongoDbNotesRepository(MongoDatabase database) {

        this.database = database;
    }

    @Override
    public void saveEntity(NoteEntity note) {

        MongoCollection<NoteEntity> collection = database.getCollection("movies", NoteEntity.class);

    }

    @Override
    public NoteEntity findById(String noteId) {
        return null;
    }

    @Override
    public List<NoteEntity> findByAuthor(String author) {
        return null;
    }
}
