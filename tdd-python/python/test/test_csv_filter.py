from unittest import TestCase
from assertpy import assert_that
from src.csv_filter import CsvFilter


class CsvFilterShould(TestCase):
    
    def __init__(self, methodName: str = ...) -> None:
        self.headerLine =  "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente"
        super().__init__(methodName)
    
    def test_allow_for_correct_lines_only(self):
        invoiceLine = "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,"

        result = CsvFilter().filter([self.headerLine, invoiceLine])

        assert_that(result).is_equal_to([self.headerLine, invoiceLine])

    def test_exclude_lines_with_both_tax_fields_populated_as_they_are_exclusive(self):
        invoiceLine = "1,02/05/2019,1000,810,19,8,ACER Laptop,B76430134,"

        result = CsvFilter().filter([self.headerLine, invoiceLine])

        assert_that(result).is_equal_to([self.headerLine])

    def test_exclude_lines_with_both_tax_fields_empty_as_one_is_required(self):
        invoiceLine = "1,02/05/2019,1000,810,,,ACER Laptop,B76430134,"

        result = CsvFilter().filter([self.headerLine, invoiceLine])

        assert_that(result).is_equal_to([self.headerLine])

    def test_exclude_lines_with_non_decimal_tax_fields(self):
        invoiceLine = "1,02/05/2019,1000,810,XYZ,,ACER Laptop,B76430134,"
        
        result = CsvFilter().filter([self.headerLine, invoiceLine])
        
        assert_that(result).is_equal_to([self.headerLine])