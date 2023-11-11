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
    // Subsequent playback when isPlaying changes
    if (isPlaying) {
      play();
    } else {
      stop();
    }
  }, [isPlaying, play, stop]);

  useEffect(() => {
    // Request microphone permission when the component mounts
    const requestMicrophonePermission = async () => {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          audio: true,
        });
        console.log("Microphone permission granted");
        // Do something with the stream if needed
      } catch (error) {
        console.error("Error requesting microphone permission:", error);
        // Handle the error, show a message to the user, etc.
      }
    };

    requestMicrophonePermission();

    // Cleanup function (optional)
    return () => {
      // Cleanup logic, if needed
    };
  }, []); // Empty dependency array ensures this effect runs only once when the component mounts

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
