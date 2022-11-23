from dataclasses import dataclass
from assertpy import assert_that
from unittest import TestCase


@dataclass
class User:
    irrelevant = None


class Repository:

    def find_users_by_name(self, name: str) -> list[User]:
        pass

    def find_users_by_surname(self, surname: str) -> list[User]:
        pass


class Service:
    _repository: Repository

    def __init__(self, repository: Repository) -> None:
        self._repository = repository

    def find_users_by(self, name: str) -> list[User]:
        users_by_name = self._repository.find_users_by_name(name)
        if users_by_name != None and len(users_by_name) > 0:
            return users_by_name
        else:
            users_by_surname = self._repository.find_users_by_surname(name)
            if users_by_surname != None and len(users_by_surname) > 0:
                return users_by_surname
        return []


def createService(repository: Repository) -> Service:
    return Service(repository)


class TheService(TestCase):
    def test_searches_users_by_name_2(self):
        name = "irrelevant"
        user = User()

        def find_users_by_name(name: str) -> list[User]:
            return [user]

        def find_users_by_surname(surname: str) -> list[User]:
            return []
        repository = type('obj', (object,), {
            'find_users_by_name': find_users_by_name,
            'find_users_by_surname': find_users_by_surname
        })

        service = createService(repository)

        assert_that(service.find_users_by(name)).contains(user)

    def test_searches_users_by_name(self):
        name = "irrelevant"
        user = User()
        repository = Repository()

        def find_users_by_name(name: str) -> list[User]:
            return [user]

        def find_users_by_surname(surname: str) -> list[User]:
            return []
        repository.find_users_by_name = find_users_by_name
        repository.find_users_by_surname = find_users_by_surname

        service = createService(repository)

        assert_that(service.find_users_by(name)).contains(user)
