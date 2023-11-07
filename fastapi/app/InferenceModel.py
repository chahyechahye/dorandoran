from gradio_client import Client

def inferRefresh(user):
    
    data = {
        "pth" : "",
        "index" : ""
    }
    
    client = Client("http://172.17.0.4:7865/")
    result = client.predict(
                    api_name="/infer_refresh"
    )
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
    return result