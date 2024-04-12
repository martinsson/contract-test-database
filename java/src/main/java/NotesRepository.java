public interface NotesRepository {
    void saveNote(NoteEntity note);

    NoteEntity findById(String noteId);

//    List<NoteEntity> findByAuthor(String author);
}
