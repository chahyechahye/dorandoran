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
  const [play, { stop }] = useSound(mainSound, {
    volume: 0.2,
    onend: () => {
      // 재생이 끝나면 다시 재생
      if (isPlaying) {
        play();
      }
    },
  });

  useEffect(() => {
    // Subsequent playback when isPlaying changes
    if (isPlaying) {
      play();
    } else {
      stop();
    }
  }, [isPlaying, play, stop]);

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
