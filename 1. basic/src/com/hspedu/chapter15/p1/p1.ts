class p1 {
    public static returnSameType<T>(args: T): T {
        return args;
    }
    public static main(): void {
        const animalArr: Animal<number>[] = [];
        // 简单类型可以不传，能推断出来
        const dog = new Dog(0);
        const cat = new Cat(1);
        const animal = new Animal(2);
        animalArr.push(dog, cat, animal);

        const args_: number = cat.getArgs();
        console.log(args_); // 1

        const res1: string = p1.returnSameType('abc');
        console.log(res1); // abc
        const res2: Animal<number>[] = p1.returnSameType(animalArr);
        console.log(res2); // [ Dog { args: 0 }, Cat { args: 1 }, Animal { args: 2 } ]
    }
}

interface IAnimal<T> {
    getArgs(): T;
}
// type也能写成这样，但无法让类实现
type AnimalType<T> = { getArgs: () => T };

class Animal<T> implements IAnimal<T> {
    private args: T;
    public constructor(args: T) {
        this.args = args;
    }
    public getArgs(): T {
        return this.args;
    }

    public toString(): string {
        return this.constructor.name;
    }
}

class Cat<T> extends Animal<T> {
    public constructor(args: T) {
        super(args);
    }
}

class Dog<T> extends Animal<T> {
    public constructor(args: T) {
        super(args);
    }
}

p1.main();
