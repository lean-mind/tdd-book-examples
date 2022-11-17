from dataclasses import dataclass
from unittest import TestCase
from assertpy import assert_that


@dataclass
class Password:
    password: str


@dataclass
class User:
    irrelevant_content = None

    def update(self, password: Password) -> None:
        pass


class Repository:

    def save(self, user: User) -> None:
        pass


class Service:
    _repository: Repository

    def __init__(self, repository: Repository) -> None:
        self._repository = repository

    def update_password(self, user: User, password: Password) -> None:
        user.update(password)
        self._repository.save(user)


class RepositorySpy(Repository):
    saved_user: User

    def save(self, user: User) -> None:
        self.saved_user = user


class ServiceShould(TestCase):

    def test_save_user_through_the_repository(self):
        repository = RepositorySpy()
        service = Service(repository)
        user = User()

        service.update_password(user, Password("1234"))

        assert_that(repository.saved_user).is_equal_to(user)
