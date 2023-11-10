import useSound from "use-sound";
import clickSound from "@/assets/sound/클릭_버블.wav";

export function useSoundEffect() {
  const [play] = useSound(clickSound);

  return { playSound: play };
}
