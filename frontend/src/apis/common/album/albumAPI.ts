import { instance } from "@/apis/instance";
import { AlbumProps } from "@/types/parent/albumType";

const getAlbumList = async () => {
  try {
    const response = await instance.get(`/album`);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - getAlbumList"));
  }
};

const deleteAlbum = async (albumId: AlbumProps) => {
  try {
    const response = await instance.get(`/album/${albumId}`);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - deleteAlbum"));
  }
};

const postAlbum = async (formData: FormData) => {
  try {
    const config = {
      headers: { "Content-Type": "multipart/form-data" },
    };

    const response = await instance.post(`/album`, formData, config);
    return response.data;
  } catch {
    console.log(new Error("api 연동 오류 - postAlbum"));
  }
};

export { getAlbumList, deleteAlbum, postAlbum };
