from typing import List
from pydantic import BaseModel
import json

from customLog import LogInfo
from customLog import LogError
from GoogleBucket import DownloadRaw
from CreateModel import trainStartAll
from joinWav import JoinWav

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

        download_path = ""

        for rawVoice in rawVoiceList:
            rvId = rawVoice['rvId']
            LogInfo(f"rvId : {rvId}")
            voiceUrl = rawVoice['voiceUrl']
            LogInfo(f"voiceUrl : {voiceUrl}")
            gender = rawVoice['gender']
            LogInfo(f"gender : {gender}")
            download_path = DownloadRaw(userId=userId, gender=userGender, voiceUrl=voiceUrl)

        directory = JoinWav(directory=download_path)
        LogInfo(f"join 결과 : {directory}")
        # 학습
        trainStartAll(user=userId, gender=userGender, trainPath=directory)
        res = ModelReq(
            userId=userId,
            genders=userGender
        ).model_dump_json()
        return res

    except Exception as e:
        LogError(e)