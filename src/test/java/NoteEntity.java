public class NoteEntity {
    private final String noteId;
    private final String author;
    private final String noteContent;

    public NoteEntity(String noteId, String author, String noteContent) {
        this.noteId = noteId;

        this.author = author;
        this.noteContent = noteContent;
    }

    public String getId() {
        return noteId;
    }
}
