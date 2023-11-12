import React, { useState, useRef, useEffect } from "react";
import styled from "styled-components";

import { usePostAlbum } from "@/apis/common/album/Mutations/usePostAlbum";
import { useGetAlbumList } from "@/apis/common/album/Queries/useGetAlbumList";

import bookLeft from "@/assets/img/bookLeft.png";
import bookRight from "@/assets/img/bookRight.png";
import registBtn from "@/assets/img/registBtn.png";
import deleteBtn from "@/assets/img/deleteBtn.png";
import exitBtn from "@/assets/img/exitBtn.png";
import { useDeleteAlbum } from "@/apis/common/album/Mutations/useDeleteAlbum";
import { ButtonEffect } from "@/styles/buttonEffect";

import { useSoundEffect } from "@/components/sounds/soundEffect";
import { background } from "@/assets/img/background/backgroundRecord.jpg";

// Define the type for your AlbumList items
interface AlbumItem {
  albumId: number;
  imgUrl: string;
}

const Container = styled.div`
  min-height: 100vh;
  color: #fff;
  perspective: 70vh;
  overflow: hidden;
`;

const Book = styled.div`
  position: absolute;
  left: 50%;
  top: 55vh;
  transform: rotateX(4deg) translateY(-3%) translateX(-50%) translateY(-50%);
  width: 145vh;
  height: 90vh;
  max-height: 120vh;

  &::before {
    border-top-right-radius: 16px 6px;
    border-bottom-right-radius: 16px 6px;
  }

  &::after {
    left: calc(50% + 0.5rem);
    border-top-left-radius: 16px 6px;
    border-bottom-left-radius: 16px 6px;
  }

  div {
    background: url(${bookLeft});
    background-size: contain;
    background-repeat: no-repeat;
    width: 50%;
    height: 80%;
    position: absolute;
    top: 0;
    right: calc(48% + 0.5rem);
    overflow: auto;

    & + div {
      background: url(${bookRight});
      background-size: contain;
      background-repeat: no-repeat;
      right: auto;
      left: calc(50% + 0.5rem);
    }
  }
`;

const PageLeft = styled.div`
  text-align: center;
  padding: 5%;
  padding-top: 10%;
  padding-left: 15%;
  height: 15vh; /* Adjust the height of the container */
  overflow: hidden; /* Hide the overflow outside the container */

  .image-container {
    background: #00ff0000;
    position: absolute;
    width: 45vh;
    top: 50%;
    left: 60%;
    transform: translate(-50%, -50%);
    overflow: auto; /* Apply overflow inside the padding */
    height: 70%; /* Take 100% of the container height */
  }

  img {
    max-width: 30%; /* Adjust the max-width of the images */
    min-width: 4.8vh;
    max-height: 15%; /* Adjust the max-height of the images */
    width: auto;
    height: auto;
    background-color: #fff;
    padding: 0.5rem;
    margin: 0.5rem;
    display: inline-block;
    transition: 1.5s;

    &:hover,
    &.active {
      transform: scale(1.04);
      box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
    }
  }
`;

const PageRight = styled.div`
  img {
    position: absolute;
    left: 30vh;
    top: 36vh;
    transform: translate(-50%, -50%);
    max-width: 50vh;
    max-height: 50vh;
    opacity: 0;
    transition:
      0.8s,
      opacity 0.45s 0.15s;
    filter: blur(32px);
    background-color: #fff;
    padding: 1rem;

    &.active {
      filter: blur(0px);
      opacity: 1;
    }
  }
`;

const Bottom = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  bottom: 0;
  left: 50%;
  transform: translate(-50%, -50%);
`;

const Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
  right: 0;
  z-index: 1;
`;

const ExitBtn = styled.img`
  width: 15vh;
  ${ButtonEffect}
`;

const ImageBtn = styled.img`
  width: 30vh;
  margin: 0vh 7vh;
  ${ButtonEffect}
`;

const CloseButton = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex;
  margin: 4vh;
  cursor: pointer;
  ${ButtonEffect}
`;

const Album = ({ onClose, type }: { onClose: () => void; type?: string }) => {
  const { playSound } = useSoundEffect();
  const [activeAlbumId, setActiveAlbumId] = useState(0);
  const [selectedImage, setSelectedImage] = useState<File | null>(null);
  const AlbumList = useGetAlbumList().data;

  const fileInputRef = useRef<HTMLInputElement | null>(null);

  const loadAlbum = usePostAlbum();
  const deleteAlbum = useDeleteAlbum();

  const handleImageClick = (albumId: number) => {
    setActiveAlbumId(albumId);
  };

  const handleImageUpload = () => {
    playSound();
    if (fileInputRef.current) {
      fileInputRef.current.click(); // Trigger the file input
    }
  };

  const handleFileInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      setSelectedImage(file);

      const formData = new FormData();
      formData.append("multipartFile", file);

      console.log(formData);

      loadAlbum.mutateAsync(formData);
    }
  };

  const handleImageDelete = () => {
    playSound();
    deleteAlbum.mutateAsync(activeAlbumId);
  };

  return (
    <Container>
      <Header>
        <CloseButton
          onClick={() => {
            playSound(); // 사운드 재생
            onClose();
          }}
        >
          <ExitBtn src={exitBtn}></ExitBtn>
          <p style={{ fontSize: "7vh" }}>나가기</p>
        </CloseButton>
      </Header>
      <Book>
        <PageLeft>
          <div className="image-container">
            {AlbumList.map((item: AlbumItem) => (
              <img
                key={item.albumId}
                src={item.imgUrl}
                alt={`Image ${item.albumId}`}
                className={item.albumId === activeAlbumId ? "active" : ""}
                onClick={() => handleImageClick(item.albumId)}
              />
            ))}
          </div>
        </PageLeft>
        <PageRight>
          {AlbumList.map((item: AlbumItem) => (
            <img
              key={item.albumId}
              src={item.imgUrl}
              alt={`Image ${item.albumId}`}
              className={item.albumId === activeAlbumId ? "active" : ""}
            />
          ))}
        </PageRight>
      </Book>
      <Bottom>
        <ImageBtn src={registBtn} alt="regist" onClick={handleImageUpload} />
        {!type && (
          <ImageBtn src={deleteBtn} alt="delete" onClick={handleImageDelete} />
        )}
      </Bottom>
      <input
        type="file"
        accept="image/*"
        style={{ display: "none" }}
        onChange={handleFileInputChange}
        ref={fileInputRef}
      />
    </Container>
  );
};

export default Album;
