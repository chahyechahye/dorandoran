interface ChildrenLoginProps {
  childId: number;
  profileId: number;
}

interface AnimalProps {
  id: number;
  name: string;
  imgUrl: string;
}

interface ChildrenProfileProps {
  id: number;
  childId: number;
  animal: AnimalProps;
  name: string;
}
export type { ChildrenLoginProps, ChildrenProfileProps };
