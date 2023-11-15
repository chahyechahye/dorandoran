import aio_pika

async def message(queue_name, message):
    try:
        connection = await aio_pika.connect_robust(
                host="k9b108.p.ssafy.io",
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