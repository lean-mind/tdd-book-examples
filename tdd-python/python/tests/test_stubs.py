from dataclasses import dataclass
from unittest import TestCase
from assertpy import assert_that


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

    def find_users(self, name: str) -> list[User]:
        users_by_name = self._repository.find_users_by_name(name)
        if users_by_name != None and len(users_by_name) > 0:
            return users_by_name
        else:
            users_by_surname = self._repository.find_users_by_surname(name)
            if users_by_surname != None and len(users_by_surname) > 0:
                return users_by_surname
        return []


class RepositoryStub(Repository):
    stub_list_of_users_by_name = []
    stub_list_of_users_by_surname = []

    def find_users_by_name(self, name: str) -> list[User]:
        return self.stub_list_of_users_by_name

    def find_users_by_surname(self, surname: str) -> list[User]:
        return self.stub_list_of_users_by_surname


class ServiceShould(TestCase):
    repository: RepositoryStub
    service: Service
    user: User

    def setUp(self) -> None:
        self.repository = RepositoryStub()
        self.service = Service(self.repository)
        self.user = User()
        return super().setUp()

    def test_search_users_by_name_first(self):
        a_name = "irrelevant_name"
        self.repository.stub_list_of_users_by_name = [self.user]

        result = self.service.find_users(a_name)

        assert_that(result).is_length(1)
        assert_that(result).contains(self.user)

    def test_search_users_by_surname_when_nothing_is_found_by_name(self):
        a_surname = "irrelevant_surname"
        self.repository.stub_list_of_users_by_surname = [self.user]

        result = self.service.find_users(a_surname)

        assert_that(result).is_length(1)
        assert_that(result).contains(self.user)
