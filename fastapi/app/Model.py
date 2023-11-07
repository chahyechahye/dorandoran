from typing import List
from pydantic import BaseModel
import json

from customLog import LogInfo
from customLog import LogError

class RawVoiceResDto(BaseModel):
    rvId: int
    voiceUrl: str
    gender: str

class ModelRes(BaseModel):
    userId: int
    rawVoiceList: List[RawVoiceResDto]

class ModelReq(BaseModel):
    userId: int

def Model(data):
    try:
        modelRes_dict = json.loads(data)
        userId = modelRes_dict['userId']
        LogInfo(f"USERID : {userId}")
        rawVoiceList = modelRes_dict['rawVoiceList']
        LogInfo(f"RAWVOICELIST : {rawVoiceList}")
        for rawVoice in rawVoiceList:
            rvId = rawVoice['rvId']
            LogInfo(f"rvId : {rvId}")
            voiceUrl = rawVoice['voiceUrl']
            LogInfo(f"voiceUrl : {voiceUrl}")
            gender = rawVoice['gender']
            LogInfo(f"gender : {gender}")
            # 학습
        res = ModelReq(
            userId=userId
        ).model_dump_json()
        return res

    except Exception as e:
        LogError(e)