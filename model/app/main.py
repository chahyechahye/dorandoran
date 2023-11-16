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
    task1 = asyncio.create_task(on_message(queue_name=model_sub))
    await asyncio.gather(task1)
    yield 
    # 종료 시 동작 부분
    LogInfo("Server Shutdown")

app = FastAPI(lifespan=lifespan)

model_sub = "model.req"

@app.get("/")
def 테스트():
    print("테스트")
    return "테스트"

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8080, log_level="info")