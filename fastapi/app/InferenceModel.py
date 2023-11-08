from gradio_client import Client

from customLog import LogInfo

# rvc_address = "http://172.17.0.4:7777/"
rvc_address = "http://173.199.124.118:7777//"

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

def inferConvertBatch(index, filePath, fileList, outputPath):
    client = Client(rvc_address)
    result = client.predict(                    
                    0,	# int | float (numeric value between 0 and 2333) in '화자/가수 ID를 선택해주세요.' Slider component
                    filePath,	# str  in '처리할 오디오 폴더의 경로를 입력하세요 (파일 매니저의 주소 표시줄에서 복사하세요):' Textbox component
                    outputPath,	# str  in '출력 폴더 지정:' Textbox component
                    # {"name" : "test123.wav", "data" : f"data:@file/octet-stream;base64,{base64_audio_data}"},	# List[str] (List of filepath(s) or URL(s) to files) in '오디오 파일을 일괄적으로 입력할 수도 있습니다. 두 가지 옵션 중 하나를 선택하십시오. 폴더에서 읽기가 우선합니다.' File component
                    fileList,
                    0,	# int | float  in '음조 변경 (정수, 반음 수, 옥타브 상승: 12, 옥타브 내림: -12)' Number component
                    "rmvpe",	# str  in '选择音高提取算法,输入歌声可用pm提速,harvest低音好但巨慢无比,crepe效果好但吃GPU,rmvpe效果最好且微吃GPU' Radio component
                    "",	# str  in '특징 검색 라이브러리 파일 경로입니다. 공란으로 둘 경우 드롭다운에서 선택한 결과를 사용합니다.' Textbox component
                    index,	# str (Option from: ['logs\\joonhyug/added_IVF986_Flat_nprobe_1_joonhyug_v2.index', 'logs\\joonhyug2/added_IVF1429_Flat_nprobe_1_joonhyug2_v2.index', 'logs\\joonhyug_rmvpe/added_IVF1429_Flat_nprobe_1_joonhyug_rmvpe_v2.index', 'logs\\joonhyug_rmvpe_10min/added_IVF579_Flat_nprobe_1_joonhyug_rmvpe_10min_v2.index', 'logs\\joonhyug_rmvpe_story/added_IVF1427_Flat_nprobe_1_joonhyug_rmvpe_story_v2.index', 'logs\\joonhyug_rmvpe_story2/added_IVF1427_Flat_nprobe_1_joonhyug_rmvpe_story_v2.index', 'logs\\test1/added_IVF579_Flat_nprobe_1_test1_v2.index', 'logs\\tngud2/added_IVF1129_Flat_nprobe_1_tngud2_v2.index']) in '인덱스 경로를 자동으로 감지하고 드롭다운에서 선택하세요:' Dropdown component
                    0.75,	# int | float (numeric value between 0 and 1) in '검색 특성 비율:' Slider component
                    3,	# int | float (numeric value between 0 and 7) in '만약 3 이상이면, 수확된 음높이 결과에 중앙값 필터링을 적용합니다. 값은 필터 반경을 나타내며 숨소리를 줄일 수 있습니다.' Slider component
                    0,	# int | float (numeric value between 0 and 48000) in '후 처리에서 출력 오디오를 최종 샘플 속도로 재샘플링합니다. 재샘플링하지 않으려면 0으로 설정하세요.' Slider component
                    0.25,	# int | float (numeric value between 0 and 1) in '입력 음량 엔벨로프를 출력 음량 엔벨로프로 대체하거나 혼합하는 데 사용하세요. 비율이 1에 가까울수록 출력 엔벨로프가 더 많이 사용됩니다.' Slider component
                    0.33,	# int | float (numeric value between 0 and 0.5) in '전자 음악에서 찢겨지는 등의 아티팩트를 방지하기 위해 무성음자음과 숨소리를 보호하세요. 비활성화하려면 0.5로 설정하세요. 보호 강도를 높이기 위해 값을 낮추면 색인 정확도가 감소할 수 있습니다.' Slider component
                    "wav",	# str  in '내보내는 파일 형식' Radio component
                    api_name="/infer_convert_batch"
    )
    LogInfo(result)