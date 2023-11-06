import uvicorn
from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def 테스트():
    print("테스트")
    return "테스트"

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8080)