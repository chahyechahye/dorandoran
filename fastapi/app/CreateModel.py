from gradio_client import Client

from customLog import LogInfo

rvc_address = "http://172.17.0.4:7865"
# rvc_address = "http://173.199.124.118:7865/"

def trainStartAll(user, gender, trainPath):
    client = Client(rvc_address)
    result = client.predict(
                    f"{user}_{gender}",	
                    "40k",	
                    "true",	
                    trainPath,	
                    0,	
                    4,	
                    "rmvpe_gpu",	
                    10,	
                    40,	
                    12,	
                    "false",	
                    "assets/pretrained_v2/f0G40k.pth",	
                    "assets/pretrained_v2/f0D40k.pth",	
                    "0",	
                    "false",	
                    "false",
                    "v2",
                    "0-0",
                    api_name="/train_start_all"
    )
    LogInfo(result)