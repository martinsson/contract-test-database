import {Db, DbOptions, MongoClient} from "mongodb";
import {NoteEntity} from "./NoteEntity";

export class NoteRepositoryMongoDb {
    constructor(private readonly database : Db) {

    }

    async saveNote(note: NoteEntity) {
        throw 'Not implemented'
    }

    async findById(noteId: string) : Promise<NoteEntity>{
        throw 'Not implemented'
    }
}