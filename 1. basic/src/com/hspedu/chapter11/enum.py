from enum import Enum


class _Season:
    def __init__(self, name: str, desc: str):
        self.__name = name
        self.__desc = desc

    def __str__(self) -> str:
        return "Season{" + \
            "name='" + self.__name + '\'' + \
            ", desc='" + self.__desc + '\'' + \
            '}'


class Season(Enum):
    SPRING: _Season = _Season('春天', '温暖')
    SUMMER: _Season = _Season('夏天', '炎热')
    AUTUMN: _Season = _Season('秋天', '凉爽')
    WINTER: _Season = _Season('冬天', '寒冷')


print(Season.SPRING.value, Season.SUMMER.value,
      Season.AUTUMN.value, Season.WINTER.value)
