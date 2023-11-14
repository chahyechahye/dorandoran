from typing import List
from pydantic import BaseModel
import json

from customLog import LogInfo
from customLog import LogError
from InferenceModel import inferRefresh
from InferenceModel import inferChangeVoice
from InferenceModel import inferConvertBatch
from InferenceModel import inferClean
from GoogleBucket import Download
from GoogleBucket import Upload
from transpose import check_book
from transpose import check_gender

class AdminVoiceResDto(BaseModel):
    contentId: int
    voiceUrl: str

class AdminFindResDto(BaseModel):
    bookId: int
    title: str
    adminVoiceList: List[AdminVoiceResDto]

class VoiceRes(BaseModel):
    userId: int
    genders: str
    list: List[AdminFindResDto]

class PVQueResDto(BaseModel):
    contentId: int
    voiceUrl: str

class VoiceReq(BaseModel):
    userId: int
    genders: str
    pvList: List[PVQueResDto]

def Voice(data):
    try:
        results = []

        voiceRes_dict = json.loads(data)
        userId = voiceRes_dict['userId']
        LogInfo(f"USERID : {userId}")
        userGender = voiceRes_dict['genders']
        LogInfo(f"GENDER : {userGender}")
        lists = voiceRes_dict['list']
        LogInfo(f"LIST : {lists}")
        for list in lists:
            bookId = list['bookId']
            LogInfo(f"BOOKID : {bookId}")
            bookTitle = list['title']
            book = check_book(bookTitle)
            adminVoiceList = list['adminVoiceList']

            download_data_list = []

            for adminVoice in adminVoiceList:
                contentId = adminVoice['contentId']
                LogInfo(f"CONTENTID : {contentId}")
                voiceUrl = adminVoice['voiceUrl']
                LogInfo(f"VOICEURL : {voiceUrl}")
                # 관리자 목소리 다운로드
                # 이미 다운로드 되어있다면 다운로드 건너뛰기
                download_data = Download(bookId=bookId, gender=userGender, voiceUrl=voiceUrl)
                download_data['content_id'] = contentId
                download_data_list.append(download_data)
            
            directory = ""
            save_location_list = []
            file_list = []
            

            for download_data in download_data_list:
                directory = download_data['directory']
                fileName = download_data['file_name']
                saveLocation = download_data['save_location']
                save_location_list.append(saveLocation)
                contentId = download_data['content_id']
                file = {
                "file_name" : "",
                "content_id" : ""
                }
                file['file_name'] = fileName
                file['content_id'] = contentId
                file_list.append(file)

            data = inferRefresh(user=userId, gender=userGender)
            transpose = check_gender(book, userGender)
            LogInfo(data)
            inferClean()
            LogInfo("1. Model Cleaning Success")
            inferChangeVoice(data['pth'])
            LogInfo("2. Model Select Success")
            inferConvertBatch(data['index'], directory, save_location_list, f"/app/opt/{str(userId)}", transpose)
            LogInfo("3. Inference Success")
            inferClean()
            LogInfo("4. Model Cleaning Success")

            for temp in file_list:
                upload_file_name = Upload(userId=userId, fileName=temp['file_name']+".wav.wav")
                # voiceURL 변경
                # 변경된 voiceURL 전달
                PVQueRes = PVQueResDto(contentId=temp['content_id'], voiceUrl=f"https://storage.googleapis.com/ssafy-last-project/{upload_file_name}")
                results.append(PVQueRes.model_dump())

            
        res = VoiceReq(
            userId=userId,
            genders=userGender,
            pvList=results
        ).model_dump_json()
        return res
    except Exception as e:
        LogError(e)