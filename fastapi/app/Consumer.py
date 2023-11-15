import asyncio
import aio_pika

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

# async def on_message_callback(ch, method, properties, body):
#     # 메시지를 처리하는 비동기 코드 작성
#     try:
#         queueName = method.routing_key
#         LogInfo(f"RoutingKey : {queueName}")
#         LogInfo(f"Received message : {body}")
#         if queueName == model_sub:
#             res = Model(body)
#         elif queueName == voice_sub:
#             res = Voice(body)
#         LogInfo(f"RESULT BODY : {res}")
#         ch.basic_publish(exchange="", routing_key=selectQueue(queueName), body=res)
#         ch.basic_ack(delivery_tag=method.delivery_tag)
#     except Exception as e:
#         LogError(e)

async def on_message_callback(message: aio_pika.IncomingMessage):
     async with message.process():
        try:
            queue_name = message.routing_key
            body = message.body.decode()  # 메시지 바이트를 문자열로 디코딩

            LogInfo(f"RoutingKey : {queue_name}")
            LogInfo(f"Received message : {body}")

            if queue_name == model_sub:
                res = Model(body)
            elif queue_name == voice_sub:
                res = await Voice(body)

            LogInfo(f"RESULT BODY : {res}")

            # 응답을 보내는 부분
            LogInfo(f"메세지 : {message}")
            channel = await message.channel_id()
            # LogInfo(f"채널 정보 : {channel}")
            exchange_name = ""  # 적절한 익스체인지 이름으로 변경
            routing_key = selectQueue(queue_name)
            LogInfo(f"퍼블리셔 큐 이름 : {routing_key}")
            await channel.default_exchange.publish(
                aio_pika.Message(body=str(res).encode()),
                routing_key=routing_key,
                exchange_name=exchange_name
            )
            await message.ack()   

        except Exception as e:
            LogError(e)
            # await message.reject(requeue=True)


async def on_message(queue_name):
    # credentials = pika.PlainCredentials(username="username", password="password")
    # host = "k9b108.p.ssafy.io"
    # port = 5672
    connection = None
    try:
        # connection = BlockingConnection(
        #     pika.ConnectionParameters(host=host, credentials=credentials, port=port)
        # )
        connection = await aio_pika.connect_robust(
            host="k9b108.p.ssafy.io",
            port=5672,
            login="username",
            password="password",
            virtualhost="/"
        )
        channel = await connection.channel()

        # channel.queue_declare(queue=queue_name, durable=True)
        # channel.basic_qos(prefetch_count=1)
        # channel.basic_consume(queue=queue_name, on_message_callback=lambda *args: asyncio.run(on_message_callback(*args)), auto_ack=False)
        await channel.set_qos(prefetch_count=1)
        queue = await channel.declare_queue(queue_name, durable=True)
        LogInfo(f"Start consuming from queue: {queue_name}")
        queue.consume(on_message_callback)
        # async for data in queue.consume(on_message_callback):
        #     try:
        #         d = data['message']
        #         res = data['res']
        #         LogInfo(f"처리 완료 메세지 : {res}")
        #         await d.ack()
        #     except Exception as e:
        #         LogInfo(f"처리 실패 : {e}")
        #         await data['message'].nack(requeue=True)

        # await asyncio.to_thread(channel.start_consuming)
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

    finally:
        # 여기서 필요한 정리 작업을 수행합니다.
        if connection and not connection.is_closed:
            await connection.close()
        # pass
    # except Exception as e:
    #     LogError(f"{e}")
    #     channel.stop_consuming()
    #     connection.close()
    # finally:
    #     channel.stop_consuming()
    #     connection.close()