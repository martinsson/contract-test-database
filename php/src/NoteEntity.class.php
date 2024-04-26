<?php
class NoteEntity {
    private $noteId;
    private $author;
    private $noteContent;

    public function __construct($noteId, $author, $noteContent) {
        $this->noteId = $noteId;
        $this->author = $author;
        $this->noteContent = $noteContent;
    }

    public function getNoteId() {
        return $this->noteId;
    }

    public function getAuthor() {
        return $this->author;
    }

    public function getNoteContent() {
        return $this->noteContent;
    }

    public function setNoteId($noteId) {
        $this->noteId = $noteId;
    }

    public function setAuthor($author) {
        $this->author = $author;
    }

    public function setNoteContent($noteContent) {
        $this->noteContent = $noteContent;
    }

    public function __toString() {
        return "NoteEntity{" .
                "noteId='" . $this->noteId . '\'' .
                ", author='" . $this->author . '\'' .
                ", noteContent='" . $this->noteContent . '\'' .
                '}';
    }
}
?>