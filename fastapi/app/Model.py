from typing import List
from pydantic import BaseModel
import json

from customLog import LogInfo
from customLog import LogError
from GoogleBucket import DownloadRaw
from CreateModel import trainStartAll

class RawVoiceResDto(BaseModel):
    rvId: int
    voiceUrl: str
    gender: str

class ModelRes(BaseModel):
    userId: int
    gender: str
    rawVoiceList: List[RawVoiceResDto]

class ModelReq(BaseModel):
    userId: int
    genders: str

def Model(data):
    try:
        modelRes_dict = json.loads(data)
        userId = modelRes_dict['userId']
        LogInfo(f"USERID : {userId}")
        userGender = modelRes_dict['gender']
        LogInfo(f"USERGENDER : {userGender}")
        rawVoiceList = modelRes_dict['rawVoiceList']
        LogInfo(f"RAWVOICELIST : {rawVoiceList}")

        directory = ""

        for rawVoice in rawVoiceList:
            rvId = rawVoice['rvId']
            LogInfo(f"rvId : {rvId}")
            voiceUrl = rawVoice['voiceUrl']
            LogInfo(f"voiceUrl : {voiceUrl}")
            gender = rawVoice['gender']
            LogInfo(f"gender : {gender}")
            directory = DownloadRaw(userId=userId, gender=gender, voiceUrl=voiceUrl)
            # 학습
        trainStartAll(user=userId, gender=gender, trainPath=directory)
        res = ModelReq(
            userId=userId,
            genders=userGender
        ).model_dump_json()
        return res

    except Exception as e:
        LogError(e)