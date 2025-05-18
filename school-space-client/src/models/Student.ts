export class Student {

    private _id: number | undefined;
    private _name: string;
    private _email: string;
    private _age: number;

    constructor(name: string, email: string, age: number) {
        this._name = name;
        this._email = email;
        this._age = age;
    }

    get id(): number {
        return this._id as number;
    }

    set id(value: number) {
        this._id = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get age(): number {
        return this._age;
    }

    set age(value: number) {
        this._age = value;
    }

    get email(): string {
        return this._email;
    }

    set email(value: string) {
        this._email = value;
    }
}