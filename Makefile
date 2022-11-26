.DEFAULT_GOAL := help

.PHONY: help
help:  ## Show this help
	@grep -E '^\S+:.*?## .*$$' $(firstword $(MAKEFILE_LIST)) | \
		awk 'BEGIN {FS = ":.*?## "}; {printf "%-30s %s\n", $$1, $$2}'

.PHONY: setup
setup: ## Setup local environment
	@pipenv install --dev

.PHONY: lint
lint:   ## Lint the project files
	@echo "Formatting with autopep8"
	@PIPENV_VERBOSITY=-1 pipenv run autopep8 -i -r ./
	@echo "Check for errors with flake8"
	@PIPENV_VERBOSITY=-1 pipenv run flake8 ./

.PHONY: tests
tests: unit-tests django-tests bdd-tests  ## Locally run all tests

.PHONY: unit-tests
unit-tests:  ## Locally run unit tests
	@PYTHONPATH=app PIPENV_VERBOSITY=-1 pipenv run pytest -v tests/

.PHONY: django-tests
django-tests:  ## Locally run Selenium Django tests
	@PYTHONPATH=app PIPENV_VERBOSITY=-1 pipenv run ./manage.py test app.tests

.PHONY: bdd-tests
bdd-tests:  ## Locally run Behave (BDD) tests
	@PYTHONPATH=app PIPENV_VERBOSITY=-1 pipenv run behave

.PHONY: run-django
run-django: ## Locally run Django
	@PIPENV_VERBOSITY=-1 pipenv run python manage.py runserver