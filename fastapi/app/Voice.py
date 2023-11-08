from typing import List
from pydantic import BaseModel
import json

from customLog import LogInfo
from customLog import LogError
from InferenceModel import inferRefresh
from InferenceModel import inferChangeVoice
from InferenceModel import inferConvertBatch
from InferenceModel import inferClean

class AdminVoiceResDto(BaseModel):
    contentId: int
    voiceUrl: str

class AdminFindResDto(BaseModel):
    bookId: int
    adminVoiceList: List[AdminVoiceResDto]

class VoiceRes(BaseModel):
    userId: int
    list: List[AdminFindResDto]

class PVQueResDto(BaseModel):
    contentId: int
    voiceUrl: str

class VoiceReq(BaseModel):
    userId: int
    pbList: List[PVQueResDto]

def Voice(data):
    try:
        results = []

        voiceRes_dict = json.loads(data)
        userId = voiceRes_dict['userId']
        LogInfo(f"USERID : {userId}")
        lists = voiceRes_dict['list']
        LogInfo(f"LIST : {lists}")
        for list in lists:
            bookId = list['bookId']
            LogInfo(f"BOOKID : {bookId}")
            adminVoiceList = list['adminVoiceList']
            for adminVoice in adminVoiceList:
                contentId = adminVoice['contentId']
                LogInfo(f"CONTENTID : {contentId}")
                voiceUrl = adminVoice['voiceUrl']
                LogInfo(f"VOICEURL : {voiceUrl}")
                # 관리자 목소리 다운로드
                # 이미 다운로드 되어있다면 다운로드 건너뛰기
                # voiceURL 변경
                # 변경된 voiceURL 전달
                PVQueRes = PVQueResDto(contentId=contentId, voiceUrl=voiceUrl)
                results.append(PVQueRes.model_dump())
        data = inferRefresh("con1")
        LogInfo(data)
        inferClean()
        LogInfo("1. Model Cleaning Success")
        inferChangeVoice(data['pth'])
        LogInfo("2. Model Select Success")
        inferConvertBatch(data['index'], "/app/data/test", ["/app/data/test/test11.wav"], f"/app/opt/{str(userId)}")
        LogInfo("3. Inference Success")
        inferClean()
        LogInfo("4. Model Cleaning Success")
        res = VoiceReq(
            userId=userId,
            pbList=results
        ).model_dump_json()
        return res
    except Exception as e:
        LogError(e)