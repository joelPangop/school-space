import {Teacher} from "./Teacher";

export class Course {
    private _id: number | undefined;
    private _name: string;
    private _sigle: string;
    private _students: number[] = [];
    private _teachers: number[] = [];

    constructor(name: string, sigle: string) {
        this._name = name;
        this._sigle = sigle;
    }

    get id() {
        return this._id as number;
    }

    set id(id: number) {
        this._id = id;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get sigle(): string {
        return this._sigle;
    }

    set sigle(value: string) {
        this._sigle = value;
    }


    get students(): number[] {
        return this._students;
    }

    set students(value: number[]) {
        this._students = value;
    }

    get teachers(): number[] {
        return this._teachers;
    }

    set teachers(value: number[]) {
        this._teachers = value;
    }

    // toJson() {
    //     return {
    //         _name: this._name,
    //         _sigle: this._sigle,
    //         _students: Array.from(this._students).map(s => ({ id: s.id })), // envoie juste les IDs
    //         _teachers: Array.from(this._teachers).map(t => ({ id: t.id }))
    //     };
    // }
}