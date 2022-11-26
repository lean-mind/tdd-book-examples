import os
import csv
from pathlib import Path
from django.contrib.staticfiles.testing import StaticLiveServerTestCase
from django.core.files.uploadedfile import SimpleUploadedFile
from django.test.client import encode_multipart, RequestFactory
from views import csv_form
from assertpy import assert_that


class CsvFilterAppShould(StaticLiveServerTestCase):
    @classmethod
    def setUpClass(self):
        super().setUpClass()
        self.file_path = str(Path('sample_mvc.csv').resolve())

    @classmethod
    def tearDownClass(self):
        if os.path.exists(self.file_path):
            os.remove(self.file_path)
        super().tearDownClass()

    def test_display_lines_after_filtering_csv_file(self):
        lines = [
            "Num_factura,Fecha,Bruto,Neto,IVA,IGIC,Concepto,CIF_cliente,NIF_cliente",
            "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,",
            "2,03/12/2019,1000,2000,19,8,Lenovo Laptop,,78544372A"
        ]
        self._create_csv(lines)
        form_data = {
            'file': SimpleUploadedFile(
                "file",
                open(self.file_path, 'rb').read(),
                content_type='text/plain'
            )
        }
        boundary = "----WebKitFormBoundaryucMox1afkfmW2cdo"
        content = encode_multipart(boundary, form_data)

        request = RequestFactory().post(
            f"{self.live_server_url}/csv_form",
            content_type=f"multipart/form-data; boundary={boundary}",
            data=content
        )
        page_source = csv_form(request).content.decode("utf-8")

        assert_that(page_source).contains(lines[0])
        assert_that(page_source).contains(lines[1])
        assert_that(page_source).does_not_contain(lines[2])

    def _create_csv(self, lines: list[str]) -> None:
        with open(self.file_path, 'w', encoding='UTF8') as file:
            writer = csv.writer(file, doublequote=False)
            [writer.writerow([line]) for line in lines]
