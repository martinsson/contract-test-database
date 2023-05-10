import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotesRepositorySimpleTest {
    @Test
    void can_create() {
        String noteId = "noteId";
        NoteEntity note = new NoteEntity(noteId,"barack@obama.org", "note content");
        NotesRepository repository = new NotesRepository();

        repository.saveEntity(note);

        NoteEntity result = repository.findById(noteId);
        NoteEntity expected = new NoteEntity(noteId,"barack@obama.org", "note content");
        Assertions.assertThat(result).isEqualToComparingFieldByFieldRecursively(expected);
    }



    // find by id
    // list by author

}
