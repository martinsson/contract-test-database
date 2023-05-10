import java.util.HashMap;
import java.util.Map;

public class NotesRepository {
    private Map<String, NoteEntity> notes = new HashMap<>();

    public void saveEntity(NoteEntity note) {
        this.notes.put(note.getId(), note);
    }

    public NoteEntity findById(String noteId) {
        return notes.get(noteId);
    }
}
