<?php
use MongoDB\Client as MongoClient;
use PHPUnit\Framework\TestCase;

class NotesRepositoryMongoDbTest extends TestCase {
    private $repository;
    private $mongoClient;

    protected function setUp(): void {
        $this->mongoClient = new MongoClient('mongodb://localhost');
        $database = $this->mongoClient->selectDatabase('contract_testing');
        $database->dropCollection('notes');

        $this->repository = new MongoDbNotesRepository($this->mongoClient);
    }

    protected function tearDown(): void {
        $this->mongoClient = null;
    }

    public function testCanCreate() {
        $noteId = "noteId";
        $note = new NoteEntity($noteId, "barack@obama.org", "note content");

        $this->repository->saveNote($note);

        $result = $this->repository->findById($noteId);
        $expected = new NoteEntity($noteId, "barack@obama.org", "note content");

        $this->assertEquals($expected, $result);
    }

    public function testFindByIdRestitutesTheRightNote() {
        $noteId = "noteId";
        $note = new NoteEntity($noteId, "barack@obama.org", "note content");
        $note2 = new NoteEntity("otherid", "francois@hollande.fr", "note in french");

        $this->repository->saveNote($note2);
        $this->repository->saveNote($note);

        $result = $this->repository->findById("otherid");
        $expected = new NoteEntity("otherid", "francois@hollande.fr", "note in french");

        $this->assertEquals($expected, $result);
    }

    public function testFindByIdReturnsNoNotesWhenIdIsUnknown() {
        $this->repository->saveNote(new NoteEntity("existing note", "whatever", "whatever"));
        $result = $this->repository->findById("unknown id");
        $this->assertNull($result);
    }
}
?>