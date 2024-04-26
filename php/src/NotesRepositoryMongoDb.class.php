<?php
use MongoDB\Client as MongoClient;
use MongoDB\Collection as MongoCollection;

class MongoDbNotesRepository implements NotesRepository
{
    private $database;

    public function __construct(MongoClient $database) {
        $this->database = $database;
    }

    public function saveNote(NoteEntity $note) {
        $this->getCollection()->insertOne($note);
    }

    public function findById(string $noteId) {
        $findResult = $this->getCollection()->find();
        foreach ($findResult as $note) {
            if ($note->noteId === $noteId) {
                return $note;
            }
        }
        return null;
    }

    private function getCollection(): MongoCollection {
        return $this->database->selectCollection('notes');
    }
}
?>