from pydub import AudioSegment
import os
from customLog import LogInfo

def concatenate_wav_files(file_paths, output_path):
    # 빈 오디오 세그먼트 생성
    combined = AudioSegment.silent(duration=0)

    for file_path in file_paths:
        if file_path != None:
            # WAV 파일 로드
            audio = AudioSegment.from_wav(file_path)

            # 빈 오디오 세그먼트에 현재 파일을 이어 붙임
            combined += audio

    # 이어 붙인 오디오를 파일로 내보냄
    combined.export(output_path, format="wav")
    LogInfo(f"{output_path} 음성 합성 완료")

def list_files(directory):
    files = []
    for filename in os.listdir(directory):
        path = os.path.join(directory, filename)
        if os.path.isfile(path):
            files.append(path)
    return files

def split_files(files, chunk_size):
    # 파일 리스트를 chunk_size 크기로 자르기
    result = [files[i:i + chunk_size] for i in range(0, len(files), chunk_size)]
    return result

def JoinWav(directory):
    LogInfo(f"음성 파일 조인 시작 : {directory}")
    # 디렉토리 아래의 파일 목록 가져오기
    files = list_files(directory)
    chunk_size = 50

    # 파일 리스트를 50개씩 자르기
    file_chunks = split_files(files, chunk_size)

    # 마지막 chunk가 chunk_size보다 작으면 새로운 리스트로 만들기
    last_chunk_size = len(file_chunks[-1])
    if last_chunk_size < chunk_size:
        last_chunk = file_chunks.pop()  # 마지막 chunk 제거
        new_last_chunk = last_chunk + [None] * (chunk_size - last_chunk_size)  # None으로 채워서 크기 맞추기
        file_chunks.append(new_last_chunk)  # 새로운 마지막 chunk 추가

    # 해당 경로에 디렉토리가 존재하지 않으면 디렉토리 생성
    new_directory = os.path.join("/", "app", "data_join")
    if not os.path.exists(new_directory):
        os.makedirs(new_directory)

    # 결과 출력
    for i, chunk in enumerate(file_chunks, start=1):
        concatenate_wav_files(chunk, f"{new_directory}/join_{i}.wav")

    return new_directory