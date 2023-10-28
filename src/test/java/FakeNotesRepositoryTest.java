import org.junit.jupiter.api.BeforeEach;

public class FakeNotesRepositoryTest extends NotesRepositoryContract {
    @BeforeEach
    void setUp() {
        repository = new InMemoryNotesRepository();
    }
}
