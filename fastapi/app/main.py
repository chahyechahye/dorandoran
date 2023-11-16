import uvicorn
import asyncio
from fastapi import FastAPI
from contextlib import asynccontextmanager

from customLog import LogInfo

from Consumer import on_message

@asynccontextmanager
async def lifespan(app: FastAPI):
    # 시작 시 동작 부분
    LogInfo("Server Start")
    await asyncio.gather(
        asyncio.create_task(on_message(model_sub)), 
        asyncio.create_task(on_message(voice_sub))
        )
    # await asyncio.gather(on_message(voice_sub))
    yield 
    # 종료 시 동작 부분
    LogInfo("Server Shutdown")

app = FastAPI(lifespan=lifespan)

model_sub = "model.req"
voice_sub = "voice.req"

@app.get("/")
async def 테스트():
    await asyncio.gather(on_message(model_sub), on_message(voice_sub))
    return "테스트"

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8080, log_level="info")