from django.contrib.staticfiles.testing import StaticLiveServerTestCase
from webdriver_manager.chrome import ChromeDriverManager
from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService
from selenium.webdriver.common.by import By
from app import settings
from assertpy import assert_that


class CsvFilterAppShould(StaticLiveServerTestCase):
    @classmethod
    def setUpClass(self):
        super().setUpClass()
        self.driver = webdriver.Chrome(
            service=ChromeService(ChromeDriverManager().install())
        )

    @classmethod
    def tearDownClass(self):
        self.driver.quit()
        super().tearDownClass()

    def test_display_lines_after_filtering_csv_file(self):
        self.driver.get(self.live_server_url)
        lines = [
            "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente",
            "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,",
            "2,03/12/2019,1000,2000,19,8,Lenovo Laptop,,78544372A"
        ]
        self._create_csv(lines)
        self._login()

        assert_that(self.driver.title).contains("CsvFilterApp")

    def _create_csv(self, lines: list[str]) -> None:
        with open('sample.csv', 'w') as file:
            [file.write(line) for line in lines]

    def _login(
        self,
        username: str = settings.USERNAME,
        password: str = settings.PASSWORD,
    ) -> None:
        self.driver.get(f"{self.live_server_url}/{settings.LOGIN_URL}")
        self.driver.find_element(By.NAME, "username").send_keys(username)
        self.driver.find_element(By.NAME, "password").send_keys(password)
        self.driver.find_element(By.ID, "login").click()

        assert_that(self.driver.current_url).does_not_contain("?error")
