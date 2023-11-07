import pika
from pika import BlockingConnection, BasicProperties

def message(queue_name, message):
    credentials = pika.PlainCredentials(username="username", password="password")
    host = "k9b108.p.ssafy.io"
    port = 5672
    connection = BlockingConnection(
        pika.ConnectionParameters(host=host, credentials=credentials, port=port)
    )
    try:
        channel = connection.channel()
        channel.queue_declare(queue_name, durable=True)
        channel.basic_publish(exchange='', routing_key=queue_name, body=message,)
    finally:
        connection.close()