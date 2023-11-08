import uuid
import os
os.environ ["GOOGLE_APPLICATION_CREDENTIALS"] = "rd-ssafy-project-ebd0eea46d3e.json"

from google.cloud import storage

from customLog import LogInfo
from customLog import LogError

client = storage.Client()

bucket_name = 'ssafy-last-project'

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
            return

        bucket = client.bucket(bucket_name)
        blob = bucket.blob(fileName)
        blob.download_to_filename(save_location)
        data['directory'] = directory
        data['file_name'] = fileName
        data['save_location'] = save_location
        return data
    except Exception as e:
        LogError(e)
        LogError("Download Fail")

def DownloadRaw(userId, gender, voiceUrl):
    fileName = voiceUrl.split("/")[-1]
    LogInfo(fileName)
    
    directory = os.path.join("/", "app", "data", f"user_{str(userId)}_{gender}")
    os.makedirs(directory, exist_ok=True)
    save_location = os.path.join(directory, fileName+".wav")

    if os.path.exists(save_location):
        LogInfo("aleady download")
        return

    bucket = client.bucket(bucket_name)
    blob = bucket.blob(fileName)
    blob.download_to_filename(save_location)
    return directory

def Upload(userId, fileName):
    try:
        directory = os.path.join("/", "app", "opt", str(userId), fileName)

        destination_file_name = str(uuid.uuid3(uuid.NAMESPACE_URL, directory))

        bucket = client.bucket(bucket_name)
        blob = bucket.blob(destination_file_name)
        generation_match_precondition = 0
        blob.upload_from_filename(directory, if_generation_match=generation_match_precondition)
        LogInfo(destination_file_name)
        return destination_file_name
    except Exception as e:
        LogError(e)
        LogError("Upload Fail")