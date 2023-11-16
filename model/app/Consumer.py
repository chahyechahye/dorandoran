import pika
import asyncio
import time
from pika import BlockingConnection

from customLog import LogInfo
from customLog import LogError
from Model import Model

model_pub = "model.res"
model_sub = "model.req"

def selectQueue(queueName):
    if queueName == model_sub:
        return model_pub


async def on_message_callback(ch, method, properties, body):
    # 메시지를 처리하는 비동기 코드 작성
    try:
        queueName = method.routing_key
        LogInfo(f"RoutingKey : {queueName}")
        LogInfo(f"Received message : {body}")
        if queueName == model_sub:
            res = Model(body)

        LogInfo(f"RESULT BODY : {res}")
        ch.basic_publish(exchange="", routing_key=selectQueue(queueName), body=res)
        ch.basic_ack(delivery_tag=method.delivery_tag)
    except Exception as e:
        LogError(e)

async def on_message(queue_name):
    credentials = pika.PlainCredentials(username="username", password="password")
    host = "k9b108.p.ssafy.io"
    port = 5672
    try:
        connection = BlockingConnection(
            pika.ConnectionParameters(host=host, credentials=credentials, port=port)
        )
        channel = connection.channel()

        channel.queue_declare(queue=queue_name, durable=True)
        channel.basic_qos(prefetch_count=1)
        channel.basic_consume(queue=queue_name, on_message_callback=lambda *args: asyncio.run(on_message_callback(*args)), auto_ack=False)
        
        await asyncio.to_thread(channel.start_consuming)
        LogInfo(f"Start consuming from queue: {queue_name}")
    except Exception as e:
        LogError(f"{e}")
        channel.stop_consuming()
        connection.close()
    finally:
        channel.stop_consuming()
        connection.close()