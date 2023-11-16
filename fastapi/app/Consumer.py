import asyncio
import aio_pika
import json

from customLog import LogInfo
from customLog import LogError
from Voice import Voice
from Model import Model
from Publicher import send_message

model_pub = "model.res"
model_sub = "model.req"
voice_pub = "voice.res"
voice_sub = "voice.req"

def selectQueue(queueName):
    if queueName == model_sub:
        return model_pub
    elif queueName == voice_sub:
        return voice_pub

async def on_message_callback(message: aio_pika.IncomingMessage):
     async with message.process():
        try:
            queue_name = message.routing_key
            body = message.body.decode()  # 메시지 바이트를 문자열로 디코딩

            LogInfo(f"RoutingKey : {queue_name}")
            LogInfo(f"Received message : {body}")

            if queue_name == model_sub:
                res = await Model(body)
            elif queue_name == voice_sub:
                res = await Voice(body)

            LogInfo(f"RESULT BODY : {res}")

            # 응답을 보내는 부분
            LogInfo(f"메세지 : {message}")
            channel = message.channel
            LogInfo(f"채널 정보 : {channel}")
            routing_key = selectQueue(queue_name)
            LogInfo(f"퍼블리셔 큐 이름 : {routing_key}")
            await channel.basic_publish(
                body=res.encode('utf-8'),
                routing_key=routing_key
            )


        except Exception as e:
            LogError(e)


async def on_message(queue_name):
    connection = None
    try:
        connection = await aio_pika.connect_robust(
            host="k9b108.p.ssafy.io",
            port=5672,
            login="username",
            password="password",
            virtualhost="/"
        )
        channel = await connection.channel()

        await channel.set_qos(prefetch_count=1)
        queue = await channel.declare_queue(queue_name, durable=True)
        LogInfo(f"Start consuming from queue: {queue_name}")
        await queue.consume(on_message_callback)
    except aio_pika.exceptions.AMQPError as e:
        # AMQP 예외 처리
        LogInfo(f"AMQP Error: {e}")
        # 여기서 연결을 다시 시도하거나, 로깅하거나 다른 적절한 조치를 취할 수 있습니다.
        LogInfo("Reconnecting...")
        await asyncio.sleep(5)

    except asyncio.CancelledError:
        LogInfo("Task cancelled. Exiting...")

    except Exception as e:
        # 기타 예외 처리
        LogInfo(f"Unexpected error: {e}")