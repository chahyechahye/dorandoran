import "./App.css";
import { BrowserRouter as Router } from "react-router-dom";
import RenderRoutes from "@/router/routes";
import { QueryClient, QueryClientProvider } from "react-query";
import useSound from "use-sound";
import mainSound from "@/assets/sound/메인브금.mp3";
import { useState, useEffect } from "react";
import { MainSoundState } from "@/states/common/voice";
import { useRecoilState } from "recoil";

function App() {
  const queryClient = new QueryClient();
  const [isPlaying, setIsPlaying] = useRecoilState(MainSoundState);
  const [volume, setVolume] = useState(0.5); // 초기 볼륨 설정
  const [play, { stop }] = useSound(mainSound, { volume });

  useEffect(() => {
    if (isPlaying) {
      play();
    } else {
      stop();
    }
  }, [isPlaying, play, stop]);

  useEffect(() => {
    // 페이지가 로드될 때 메인 브금을 재생
    setIsPlaying(true);
  }, [setIsPlaying]);

  return (
    <div className="App h-screen">
      <QueryClientProvider client={queryClient}>
        <Router>
          <RenderRoutes />
        </Router>
      </QueryClientProvider>
    </div>
  );
}

export default App;
