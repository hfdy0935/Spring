from abc import ABC, abstractmethod
from typing import List, TypeVar, Generic

from regex import D

T = TypeVar('T')


class p1(Generic[T]):
    @ staticmethod
    def returnSameType(args: T) -> T:
        return args

    @staticmethod
    def main() -> None:
        animalArr: List[Animal[int]] = []
        # 不谢类型会显示unknown...
        dog: Dog[int] = Dog(0)
        cat: Cat[int] = Cat(1)
        animal: Animal[int] = Animal(2)
        animalArr.append(dog)
        animalArr.append(cat)
        animalArr.append(animal)

        args_: int = cat.getArgs()
        print(args_)  # 1

        res1: str = p1.returnSameType("abc")  # abc
        print(res1)
        res2: List[Animal[int]] = p1.returnSameType(animalArr)
        print(res2)  # [Dog, Cat, Animal]


class IAnimal(Generic[T], ABC):
    @abstractmethod
    def getArgs() -> T: ...


class Animal(Generic[T], IAnimal[T]):
    __args: T

    def __init__(self: 'Animal', args: T) -> None:
        self.__args = args

    def getArgs(self) -> T:
        return self.__args

    def __str__(self) -> str:
        return str(self.__class__.__name__)

    def __repr__(self) -> str:
        return str(self.__class__.__name__)


class Cat(Animal, Generic[T]):
    def __init__(self: 'Cat', args: T) -> None:
        super().__init__(args)


class Dog(Animal, Generic[T]):
    def __init__(self: 'Dog', args: T) -> None:
        super().__init__(args)


p1.main()
