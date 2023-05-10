import java.util.List;

public interface NotesRepository {
    void saveEntity(NoteEntity note);

    NoteEntity findById(String noteId);

    List<NoteEntity> findByAuthor(String author);
}
