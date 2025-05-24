export class User {
    private _id: number | undefined;
    private _password: string;
    private _email: string;
    private _role: string;
    private _firstname: string;
    private _lastname: string;
    private _telephone: string | undefined;
    private _address: string | undefined;

    constructor(_firstname: string, _lastname: string, email: string, password: string, role: string) {
        this._email = email;
        this._password = password;
        this._role = role;
        this._lastname = _lastname;
        this._firstname = _firstname;
    }
    get id(): number {
        return this._id as number;
    }
    set id(value: number) {
        this._id = value;
    }
    get email(): string {
        return this._email;
    }
    set email(value: string) {
        this._email = value;
    }
    get password(): string {
        return this._password;
    }
    set password(value: string) {
        this._password = value;
    }
    get role(): string {
        return this._role;
    }
    set role(value: string) {
        this._role = value;
    }
    get firstname(): string {
        return this._firstname;
    }
    set firstname(value: string) {
        this._firstname = value;
    }
    get lastname(): string {
        return this._lastname;
    }
    set lastname(value: string) {
        this._lastname = value;
    }
    get telephone(): string | undefined {
        return this._telephone;
    }
    set telephone(value: string | undefined) {
        this._telephone = value;
    }

    get address(): string | undefined {
        return this._address;
    }
    set address(value: string | undefined) {
        this._address = value;
    }

    toJson() {
        return {
            id: this._id,
            email: this._email,
            password: this._password,
            firstname: this._firstname,
            lastname: this._lastname,
            role: this._role,
            telephone: this._telephone,
            address: this._address
        };
    }
}