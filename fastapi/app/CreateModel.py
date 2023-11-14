from gradio_client import Client

from customLog import LogInfo

rvc_address = "http://172.19.0.2:7865"
# rvc_address = "http://173.199.124.118:7865/"

def trainStartAll(user, gender, trainPath):
    client = Client(rvc_address)
    result = client.predict(
                    f"{user}_{gender}",	                #1
                    "40k",	                            #2
                    "true",	                            #3
                    trainPath,	                        #4
                    0,	                                #5
                    4,	                                #6
                    "rmvpe_gpu",	                    #7
                    40,	# 저장 주기                      #8
                    200, # 에포크 수                     #9
                    12,	# gpu 배치 크기                  #10
                    "true", # 하드디스크 공간 절약        #11	
                    "assets/pretrained_v2/f0G40k.pth",	#12
                    "assets/pretrained_v2/f0D40k.pth",	#13
                    "0",	                            #14
                    "false",                            #15
                    "false",                            #16
                    "v2",                               #17
                    "0-0",                              #18
                    api_name="/train_start_all"
    )
    LogInfo(result)