from dataclasses import dataclass
from unittest import TestCase
from mockito import when, verify, mock


@dataclass
class Dependency:
    irrelevant_content = None


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


class SubjectUnderTest:
    _dependency: Dependency

    def __init__(self, dependency: Dependency) -> None:
        self._dependency = dependency


# Production code
class Service:
    _repository: Repository

    def __init__(self, repository: Repository) -> None:
        self._repository = repository

    def update_password(self, user: User, password: Password) -> None:
        user.update(password)
        self._repository.save(user)


# Tests
class ServiceShould(TestCase):
    def test_save_user_through_the_repository(self):
        repository = mock(Repository())
        service = Service(repository)
        user = User()

        when(repository).save(user).thenReturn(None)
        service.update_password(user, Password("1234"))

        verify(repository).save(user)
