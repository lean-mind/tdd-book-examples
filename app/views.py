from django.shortcuts import render, redirect
from app import settings
from app.src.csv_filter import CsvFilter


def home_page(request):
    return render(request, 'home_page.html', {})


def login(request):
    if request.method == 'GET':
        return render(request, 'login.html', {})
    if request.method == 'POST':
        username = request.POST.get('username', '')
        password = request.POST.get('password', '')
        is_authenticated = (
            username == settings.USERNAME
            and password == settings.PASSWORD
        )
        if is_authenticated:
            return redirect('/csv_form')
    return redirect('/?error')


def csv_form(request):
    if request.method == 'GET':
        return render(request, 'csv_form.html', {})
    if request.method == 'POST':
        file = request.FILES.get('file', {})
        if file:
            lines = file.read().split(b'\n')
            lines = [line.decode("utf-8") for line in lines]
            filtered_lines = CsvFilter().apply(lines)
            header = lines[0]
            content = "".join([header] + filtered_lines)
            vars = {'file_content': content}
            return render(request, 'csv_form.html', context=vars)
    return redirect('/?error')
