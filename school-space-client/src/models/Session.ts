import {Student} from "./Student";
import {Course} from "./Course";

export class Session {
    private _id: string = '';
    private _name: string = '';
    private _startDate: Date = new Date();
    private _endDate: Date = new Date();
    private _students: Array<Student> = [];
    private _courses: Array<Course> = [];

    get id(): string {
        return this._id;
    }

    set id(value: string) {
        this._id = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get startDate(): Date {
        return this._startDate;
    }

    set startDate(value: Date) {
        this._startDate = value;
    }

    get endDate(): Date {
        return this._endDate;
    }

    set endDate(value: Date) {
        this._endDate = value;
    }

    get students(): Array<Student> {
        return this._students;
    }

    set students(value: Array<Student>) {
        this._students = value;
    }

    get courses(): Array<Course> {
        return this._courses;
    }

    set courses(value: Array<Course>) {
        this._courses = value;
    }
}