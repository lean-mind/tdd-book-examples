from unittest import TestCase
from assertpy import assert_that
from src.csv_filter import CsvFilter


class CsvFilterShould(TestCase):
    def test_allow_for_correct_lines_only(self):
        headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente"
        invoiceLine = "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,"

        result = CsvFilter().filter([headerLine, invoiceLine])

        assert_that(result).is_equal_to([headerLine, invoiceLine])

    def test_exclude_lines_with_both_tax_fields_populated_as_they_are_exclusive(self):
        headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente"
        invoiceLine = "1,02/05/2019,1000,810,19,8,ACER Laptop,B76430134,"

        result = CsvFilter().filter([headerLine, invoiceLine])

        assert_that(result).is_equal_to([headerLine])
