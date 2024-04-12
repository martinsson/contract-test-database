import {MongoClient} from "mongodb";
import {NoteRepositoryMongoDb} from "./NoteRepositoryMongoDb";
import {NoteEntity} from "./NoteEntity";

describe('NoteRepositoryMongoDb', () => {
    let mongoClient: MongoClient;
    let repository: NoteRepositoryMongoDb;

    beforeEach(async () => {
        mongoClient = new MongoClient('mongodb://localhost:27017');
        await mongoClient.connect()
        const db = mongoClient.db('contract_testing');
        await db.collection("notes").drop();

        repository = new NoteRepositoryMongoDb(db);
    })

    afterEach(() => {
        mongoClient.close()
    })

    test('can create', async () => {
        const noteId = "noteId";
        const note = new NoteEntity(noteId, "barack@obama.org", "note content");

        await repository.saveNote(note);

        const result = repository.findById(noteId);
        const expected = new NoteEntity(noteId, "barack@obama.org", "note content");
        expect(result).toEqual(expected)
    })
})