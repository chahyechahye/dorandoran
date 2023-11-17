interface ChildrenLoginProps {
  childId: number;
  profileId: number;
}

interface AnimalProps {
  id: number;
  name: string;
  imgUrl: string;
  color: string;
}

interface ChildrenProfileProps {
  id: number;
  childId: number;
  animal: AnimalProps;
  name: string;
}

interface AnimalIdProps {
  animalId: number;
}
export type {
  ChildrenLoginProps,
  ChildrenProfileProps,
  AnimalIdProps,
  AnimalProps,
};
