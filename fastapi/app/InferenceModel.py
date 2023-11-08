from gradio_client import Client

from customLog import LogInfo

rvc_address = "http://172.17.0.5:7777/"
# rvc_address = "http://173.199.124.118:7777//"

def inferRefresh(user):
    data = {
        "pth" : "",
        "index" : ""
    }
    client = Client(rvc_address)
    result = client.predict(
                    api_name="/infer_refresh"
    )
    LogInfo(result)
    pths = result[0]['choices']
    for pth in pths:
        a = pth.split(".")[0]
        if user == a:
            data['pth'] = pth
    indexs = result[1]['choices']

    for index in indexs:
        b = index.split("/")[1]
        if user == b:
            data['index'] = index
    return data

def inferClean():
    client = Client(rvc_address)
    result = client.predict(
        api_name="/infer_clean"
    )
    LogInfo(result)

def inferChangeVoice(pth):
    client = Client(rvc_address)
    result = client.predict(
                    pth,
                    0,
                    0,	
                    api_name="/infer_change_voice"
    )
    LogInfo(result)

def inferConvertBatch(index, filePath, fileList, outputPath):
    client = Client(rvc_address)
    result = client.predict(                    
                    0,	
                    filePath,	
                    outputPath,	
                    fileList,
                    0,	
                    "rmvpe",	
                    "",	
                    index,	
                    0.75,	
                    3,	
                    0,	
                    0.25,	
                    0.33,	
                    "wav",
                    api_name="/infer_convert_batch"
    )
    LogInfo(result)