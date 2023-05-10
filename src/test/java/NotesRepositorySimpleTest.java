import org.junit.jupiter.api.BeforeEach;

public class NotesRepositorySimpleTest extends NotesRepositorySimpleContractTest {

    @BeforeEach
    void setUp() {
        repository = new InMemoryNotesRepository();
    }

}
