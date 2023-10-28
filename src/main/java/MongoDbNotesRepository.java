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
    public void saveEntity(NoteEntity note) {
        getCollection().insertOne(note);
    }

    @Override
    public NoteEntity findById(String noteId) {
        return getCollection().find().first();
    }

    @Override
    public List<NoteEntity> findByAuthor(String author) {
        var findResult = getCollection().find(eq("author", author));
        return StreamSupport.stream(findResult.spliterator(), false)

                .collect(Collectors.toList());
    }

    private MongoCollection<NoteEntity> getCollection() {
        return database.getCollection("movies", NoteEntity.class);
    }
}
