import uuid
import os
import aiohttp
os.environ ["GOOGLE_APPLICATION_CREDENTIALS"] = "rd-ssafy-project-ebd0eea46d3e.json"

from google.cloud import storage

from customLog import LogInfo
from customLog import LogError

client = storage.Client()

bucket_name = 'dorandoran'

def Download(bookId, gender, voiceUrl):
    try:
        data = {
            "directory" : "",
            "file_name" : "",
            "save_location" : "",
            "content_id" : ""
        }

        fileName = voiceUrl.split("/")[-1]
        LogInfo(fileName)
        
        directory = os.path.join("/", "app", "data", f"book_{str(bookId)}_{gender}")
        os.makedirs(directory, exist_ok=True)
        save_location = os.path.join(directory, fileName+".wav")

        if os.path.exists(save_location):
            LogInfo("aleady download")
        else:
            bucket = client.bucket(bucket_name)
            blob = bucket.blob(fileName)
            blob.download_to_filename(save_location)
        data['directory'] = directory
        data['file_name'] = fileName
        data['save_location'] = save_location
        LogInfo(data)
        return data
    except Exception as e:
        LogError(e)
        LogError("Download Fail")
        raise

def DownloadRaw(userId, gender, voiceUrl):
    fileName = voiceUrl.split("/")[-1]
    LogInfo(fileName)
    
    directory = os.path.join("/", "app", "data", f"user_{str(userId)}_{gender}")
    os.makedirs(directory, exist_ok=True)
    save_location = os.path.join(directory, fileName+".wav")

    if os.path.exists(save_location):
        LogInfo("aleady download")
    else:
        bucket = client.bucket(bucket_name)
        blob = bucket.blob(fileName)
        blob.download_to_filename(save_location)
    return directory

# def Upload(userId, fileName):
#     try:
#         directory = os.path.join("/", "app", "opt", str(userId), fileName)
#         LogInfo(directory)
#         destination_file_name = str(uuid.uuid4())
#         LogInfo(destination_file_name)

#         bucket = client.bucket(bucket_name)
#         blob = bucket.blob(destination_file_name)
#         generation_match_precondition = 0
#         blob.upload_from_filename(directory, if_generation_match=generation_match_precondition)
#         return destination_file_name
#     except Exception as e:
#         LogError(e)
#         LogError("Upload Fail")
#         raise

async def Upload(userId, fileName):
    try:
        directory = os.path.join("/", "app", "opt", str(userId), fileName)
        LogInfo(directory)
        destination_file_name = str(uuid.uuid1())

        bucket = client.bucket(bucket_name)
        blob = bucket.blob(destination_file_name)
        generation_match_precondition = 0   
        blob.upload_from_filename(directory, if_generation_match=generation_match_precondition)
        # async with aiohttp.ClientSession() as session:
        #     headers = {
        #         "Authorization" : "Bearer" + 
        #     }
        #     async with session.put(
        #         f"https://storage.googleapis.com/upload/storage/v1/b/{bucket_name}/o?uploadType=multipart&name={destination_file_name}",
        #         data=open(directory, "rb"),
        #         headers = headers
        #     ) as response:
        #         if response.status == 200:
        #             LogInfo("Upload successful")
        #         else:
        #             LogInfo(f"Upload failed with status code {response.status}")
        #             content = await response.text()
        #             LogInfo(f"Response content: {content}")
        LogInfo(f"구글 버킷 저장 이름 : {destination_file_name}")

        return destination_file_name

    except Exception as e:
        LogError(e)
        LogError("Upload Fail")
        raise