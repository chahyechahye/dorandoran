import useSound from "use-sound";
import clickSound from "@/assets/sound/책넘기기.mp3";

export function useSoundBookEffect() {
  const [play] = useSound(clickSound);

  return { playBookSound: play };
}
