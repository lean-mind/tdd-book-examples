from typing import List
from unittest import TestCase
from assertpy import assert_that
from app.src.csv_filter import CsvFilter


class CsvFilterShould(TestCase):
    _header_line = (
        "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente"
    )
    _empty_data_file = [_header_line]
    _empty_field = ""
    _filter: CsvFilter

    def setUp(self) -> None:
        self._filter = CsvFilter()
        return super().setUp()

    def test_allow_for_correct_lines_only(self):
        lines = self._file_with_one_invoice_line_having(
            concept="a correct line with irrelevant data"
        )

        result = self._filter.apply(lines)

        assert_that(result).is_equal_to(lines)

    def test_exclude_lines_with_both_tax_fields_populated_as_they_are_exclusive(self):
        lines = self._file_with_one_invoice_line_having(iva_tax="19", igic_tax="8")

        result = self._filter.apply(lines)

        assert_that(result).is_equal_to(self._empty_data_file)

    def test_exclude_lines_with_both_tax_fields_empty_as_one_is_required(self):
        lines = self._file_with_one_invoice_line_having(
            iva_tax=self._empty_field, igic_tax=self._empty_field
        )

        result = self._filter.apply(lines)

        assert_that(result).is_equal_to(self._empty_data_file)

    def test_exclude_lines_with_non_decimal_tax_fields(self):
        lines = self._file_with_one_invoice_line_having(iva_tax="XYZ")

        result = self._filter.apply(lines)

        assert_that(result).is_equal_to(self._empty_data_file)

    def test_exclude_lines_with_both_tax_fields_populated_even_if_non_decimal(self):
        lines = self._file_with_one_invoice_line_having(iva_tax="XYZ", igic_tax="12")

        result = self._filter.apply(lines)

        assert_that(result).is_equal_to(self._empty_data_file)

    def _file_with_one_invoice_line_having(
        self,
        iva_tax: str = "19",
        igic_tax: str = _empty_field,
        concept: str = "irrelevant",
    ) -> List[str]:
        invoice_id = "1"
        invoice_date = "02/05/2019"
        gross_amount = "1000"
        net_amount = "810"
        cif = "B76430134"
        nif = self._empty_field
        formatted_line = ",".join(
            [
                invoice_id,
                invoice_date,
                gross_amount,
                net_amount,
                iva_tax,
                igic_tax,
                concept,
                cif,
                nif,
            ]
        )
        return [self._header_line, formatted_line]
