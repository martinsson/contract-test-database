import java.util.*;
import java.util.stream.Collectors;

public class NotesRepository {
    private Map<String, NoteEntity> notes = new HashMap<>();

    public void saveEntity(NoteEntity note) {
        this.notes.put(note.getId(), note);
    }

    public NoteEntity findById(String noteId) {
        return notes.get(noteId);
    }

    public List<NoteEntity> findByAuthor(String author) {
        return notes.values().stream().filter(n -> n.getAuthor().equals(author))
                .collect(Collectors.toList());
    }
}
