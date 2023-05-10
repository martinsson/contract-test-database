import org.junit.jupiter.api.BeforeEach;

public class InMemoryNotesRepositoryTest extends NotesRepositoryContractTest {

    @BeforeEach
    void setUp() {
        repository = new InMemoryNotesRepository();
    }

}
