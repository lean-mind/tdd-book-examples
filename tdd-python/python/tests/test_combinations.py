from dataclasses import dataclass
from unittest import TestCase
from mockito import mock, when, verify


@dataclass
class File:
    content: str = ""


@dataclass
class User:
    _premium: bool
    _files = []

    def files(self):
        return self._files

    def is_premium(self):
        return self._premium

    @staticmethod
    def premium():
        return User(_premium=True)

    @staticmethod
    def freemium():
        return User(_premium=False)


@dataclass
class Repository:
    def find_all(self) -> list[User]:
        pass


@dataclass
class Backup:
    def create(self, user_files: list[File]) -> None:
        pass


class Service:
    _repository: Repository
    _backup: Backup

    def __init__(self, repository: Repository, backup_service: Backup) -> None:
        self._repository = repository
        self._backup = backup_service

    def backup_premium_users(self) -> None:
        for user in self._repository.find_all():
            if user.is_premium():
                self._backup.create(user.files())


class ServiceShould(TestCase):
    def test_backup_premium_users_files(self):
        repository = mock(Repository())
        backup = mock(Backup())
        service = Service(repository, backup)
        premium = User.premium()
        when(repository).find_all().thenReturn([premium, User.freemium()])
        when(backup).create(premium.files()).thenReturn(None)

        service.backup_premium_users()

        verify(backup).create(premium.files())
