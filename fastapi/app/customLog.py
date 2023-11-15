import logging
from aio_pika import logger

logging.basicConfig(
    format='%(levelname)s: %(asctime)s Message : %(message)s',
    level=logging.INFO
    )

logger.setLevel(logging.INFO)

def LogInfo(message):
    logging.info(f"{message}")

def LogError(message):
    logging.error(f"{message}")

