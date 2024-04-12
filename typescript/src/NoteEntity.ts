export class NoteEntity {
    constructor(
        public readonly noteId: string,
        public readonly org: string,
        public readonly noteContent: string) {
    }
}