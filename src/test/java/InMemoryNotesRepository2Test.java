import org.junit.jupiter.api.BeforeEach;

public class InMemoryNotesRepository2Test extends NotesRepositoryContractTest {

    @BeforeEach
    void setUp() {
        repository = new InMemoryNotesRepository();
    }

}
