import java.util.*;
import java.util.stream.Collectors;

public class InMemoryNotesRepository implements NotesRepository {
    private Map<String, NoteEntity> notes = new HashMap<>();

    @Override
    public void saveEntity(NoteEntity note) {
        this.notes.put(note.getId(), note);
    }

    @Override
    public NoteEntity findById(String noteId) {
        return notes.get(noteId);
    }

    @Override
    public List<NoteEntity> findByAuthor(String author) {
        return notes.values().stream().filter(n -> n.getAuthor().equals(author))
                .collect(Collectors.toList());
    }
}
