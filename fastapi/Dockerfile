FROM python:3.9

WORKDIR /app

COPY /app /app

RUN pip install --no-chache-dir -r requirements.txt

EXPOSE 8080

CMD ["python", "main.py"]