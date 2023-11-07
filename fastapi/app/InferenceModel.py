from gradio_client import Client

def inferRefresh(user):
    
    data = {
        "pth" : "",
        "index" : ""
    }
    
    client = Client("http://http://173.199.124.118:7865/")
    result = client.predict(
                    api_name="/infer_refresh"
    )
    pths = result[0]['choices']
    for pth in pths:
        a = pth.split(".")[0]
        if user == a:
            # print("pht : ", pth)
            data['pth'] = pth
    indexs = result[1]['choices']
    for index in indexs:
        a = index.split("\\")[1]
        b = a.split("/")[0]
        # print(b)
        if user == b:
            # print("index : ", index)
            data['index'] = index
    return data