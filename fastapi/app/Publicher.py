import aio_pika

async def send_message(queue_name, message):
    try:
        connection = await aio_pika.connect_robust(
                host="", # ip 지웟음 체크할것
                port=5672,
                login="username",
                password="password",
                virtualhost="/"
        )
        channel = await connection.channel()

        await channel.default_exchange.publish(
            aio_pika.Message(body=message),
            routing_key=queue_name
        )

        # channel.queue_declare(queue_name, durable=True)
        # channel.basic_publish(exchange='', routing_key=queue_name, body=message,)
    finally:
        connection.close()