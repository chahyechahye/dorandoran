

__book_list = [
    {
        "title" : "토끼와 거북이",
        "male" : -12,
        "female" : -4
    },
    {
        "title" : "금도끼 은도끼",
        "male" : -12,
        "female" : -4
    },
    {
        "title" : "미운 오리 새끼",
        "male" : 0,
        "female" : 0
    },
    {
        "title" : "아기돼지 삼형제",
        "male" : 0,
        "female" : 0
    }
]

def check_book(title):
    for book in __book_list:
        if book['title'] == title:
            return book
    raise ValueError("해당 동화책에 대한 Transpose값이 존재하지 않습니다.")

def check_gender(book, gender):
    if gender == "MALE":
        return book['male']
    elif gender == "FEMALE":
        return book['female']
    raise ValueError("성별을 다시 확인해주세요.")
