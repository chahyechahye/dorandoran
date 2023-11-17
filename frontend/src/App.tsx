import "./App.css";
import { BrowserRouter as Router } from "react-router-dom";
import RenderRoutes from "@/router/routes";
import { QueryClient, QueryClientProvider } from "react-query";
import useSound from "use-sound";
import mainSound from "@/assets/sound/메인브금.mp3";
import { useState, useEffect } from "react";
import { MainSoundState, SoundState } from "@/states/common/voice";
import { useRecoilState } from "recoil";
import { ToastContainer } from "react-toastify";
import styled from "styled-components";
import "react-toastify/dist/ReactToastify.css";

function App() {
  const queryClient = new QueryClient();
  const [isPlaying, setIsPlaying] = useRecoilState(MainSoundState);
  const [mainPlaying, setMainPlaying] = useRecoilState(SoundState);
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
    // Start or stop the sound based on mainPlaying state
    if (mainPlaying) {
      if (isPlaying) {
        play();
      } else {
        stop();
      }
    } else {
      stop(); // Stop the sound if mainPlaying is false
    }
  }, [isPlaying, mainPlaying, play, stop]);

  const CustomToastContainer = styled(ToastContainer)`
    .Toastify__toast {
      background-color: #eb9f4a;
      color: white;
      font-family: Katuri;
      font-size: 3vh;
    }
  `;

  return (
    <div className="App h-screen">
      <QueryClientProvider client={queryClient}>
        <Router>
          <RenderRoutes />
        </Router>
        <CustomToastContainer
          position="top-center"
          autoClose={2000}
          limit={5}
          hideProgressBar
          newestOnTop
          closeOnClick
          rtl={false}
          pauseOnFocusLoss={false}
          draggable={false}
          pauseOnHover
        />
      </QueryClientProvider>
    </div>
  );
}

export default App;
