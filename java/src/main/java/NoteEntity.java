import java.util.Objects;

public class NoteEntity {
    String noteId;
    String author;


    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    String noteContent;


    public NoteEntity() {
    }

    public NoteEntity(String noteId, String author, String noteContent) {
        this.noteId = noteId;

        this.author = author;
        this.noteContent = noteContent;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getAuthor() {
        return author;
    }
    public String getNoteContent() {
        return noteContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteEntity that = (NoteEntity) o;
        return Objects.equals(noteId, that.noteId) && Objects.equals(author, that.author) && Objects.equals(noteContent, that.noteContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, author, noteContent);
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "noteId='" + noteId + '\'' +
                ", author='" + author + '\'' +
                ", noteContent='" + noteContent + '\'' +
                '}';
    }
}
