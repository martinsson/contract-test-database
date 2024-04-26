<?php

interface NotesRepository
{
    public function saveNote(NoteEntity $note);

    public function findById(string $noteId);
}