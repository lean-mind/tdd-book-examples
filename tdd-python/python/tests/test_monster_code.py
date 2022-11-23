from dataclasses import dataclass
import mysql.connector


@dataclass
class Data:
    pass


@dataclass
class Customer:
    name: str = ""
    title: str = ""
    location: str = ""
    sing_up_year: int = 0


data = Data()
customer = Customer()


class MonsterClass:
    def execute_action(
        self,
        arg1: str,
        arg2: str,
        arg3: str,
        arg4: str,
        panic_mode: bool
    ) -> None:
        # ... 1000 lines of code ...
        self._save_customer(customer)
        # ... 1000 lines of code ...

    def _save_customer(self, customer: Customer) -> None:
        db = mysql.connector.connect(
            host="localhost",
            user="<your_username>",
            password="<your_password>"
        )
        cursor = db.cursor()
        cursor.execute(
            "INSERT INTO Customers VALUES"
            + f" (1001, {customer.name}, {customer.title},"
            + f"{customer.location}, {customer.sing_up_year})"
        )
        cursor.close()


class MonsterClassForTests(MonsterClass):
    saved_data: Data

    def execute_action(
        self,
        arg1: str,
        arg2: str,
        arg3: str,
        arg4: str,
        panic_mode: bool
    ) -> None:
        # ... 1000 lines of code ...
        self._save_data_and_many_more_things(data)
        # ... 1000 lines of code ...

    def _save_data_and_many_more_things(self, data: Data) -> None:
        self.saved_data = data
