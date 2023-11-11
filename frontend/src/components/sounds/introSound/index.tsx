import useSound from "use-sound";
import introSound from "@/assets/sound/도란도란.mp3";

export function useSoundIntro() {
  const [play, { sound }] = useSound(introSound);

  return { playIntroSound: play, introSound: sound };
}
