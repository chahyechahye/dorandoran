from gradio_client import Client

from customLog import LogInfo

rvc_address = "http://172.17.0.5:7777/"
# rvc_address = "http://173.199.124.118:7777/"

def inferRefresh(user, gender):
    data = {
        "pth" : "",
        "index" : ""
    }
    client = Client(rvc_address)
    result = client.predict(
                    api_name="/infer_refresh"
    )
    LogInfo(result)
    name = f"{user}_{gender}"
    pths = result[0]['choices']
    for pth in pths:
        a = pth.split(".")[0]
        if name == a:
            data['pth'] = pth
    indexs = result[1]['choices']

    for index in indexs:
        b = index.split("/")[1]
        if name == b:
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
                    0.33,
                    0.33,	
                    api_name="/infer_change_voice"
    )
    LogInfo(result)

def inferConvertBatch(index, filePath, fileList, outputPath, transpose):
    client = Client(rvc_address)
    result = client.predict(                    
                    0,	
                    filePath,	
                    outputPath,	
                    fileList,
                    transpose,	# 음조 변경
                    "rmvpe",	
                    "",	
                    index,	
                    0.1,	# 5
                    3,	    # 4
                    0,	    # 1
                    0.7,	# 2
                    0.33,	# 3
                    "wav",
                    api_name="/infer_convert_batch"
    )
    LogInfo(result)