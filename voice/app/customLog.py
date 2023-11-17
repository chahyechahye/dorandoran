import logging

logging.basicConfig(
    format='%(levelname)s: %(asctime)s Message : %(message)s',
    level=logging.INFO
    )

def LogInfo(message):
    logging.info(f"{message}")

def LogError(message):
    logging.error(f"{message}")