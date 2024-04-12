import {Db, DbOptions, MongoClient} from "mongodb";
import {NoteEntity} from "./NoteEntity";

export class NoteRepositoryMongoDb {
    constructor(private readonly database : Db) {

    }

    async saveNote(note: NoteEntity) {
        await this.getCollection().insertOne(note)
    }


    async findById(noteId: string) : Promise<NoteEntity>{
        return (await this.getCollection().find().toArray())
            .map((doc:any) => new NoteEntity(doc.noteId, doc.org, doc.noteContent))
            .filter(n  => n.noteId === noteId)[0]

    }

    private getCollection() {
        return this.database.collection('notes');
    }

}