import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public abstract class NotesRepositorySimpleContractTest {
    NotesRepository repository;

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

        repository.saveEntity(new NoteEntity(
                UUID.randomUUID().toString(),
                author,
                "some content"
        ));

    }
}
