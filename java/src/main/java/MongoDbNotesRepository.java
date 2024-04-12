import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class MongoDbNotesRepository implements NotesRepository {
    private final MongoDatabase database;

    public MongoDbNotesRepository(MongoDatabase database) {

        this.database = database;
    }

    @Override
    public void saveNote(NoteEntity note) {
        getCollection().insertOne(note);
    }

    @Override
    public NoteEntity findById(String noteId) {
        var findResult = getCollection().find();
        return StreamSupport.stream(findResult.spliterator(), false)
                .filter(n -> n.noteId.equals(noteId)) // TODO optimise using mongodb engine. eq use eq()
                .findFirst().orElse(null);
    }

    private MongoCollection<NoteEntity> getCollection() {
        return database.getCollection("notes", NoteEntity.class);
    }
}
