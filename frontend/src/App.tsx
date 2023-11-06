import "./App.css";
import { BrowserRouter as Router } from "react-router-dom";
import RenderRoutes from "@/router/routes";
import { QueryClient, QueryClientProvider } from "react-query";
import { useEffect } from "react";

function App() {
  const queryClient = new QueryClient();
  useEffect(() => {
    function changeOrientationToLandscape() {
      if (window.screen.orientation) {
        (window.screen.orientation as any)
          .lock("landscape")
          .then(() => {
            console.log("가로 모드로 변경되었습니다.");
          })
          .catch(() => {
            console.error("가로 모드 변경에 실패했습니다:");
          });
      } else {
        console.warn("브라우저가 screen.orientation API를 지원하지 않습니다.");
      }
    }

    // 이 코드는 컴포넌트가 처음 마운트될 때 가로 모드로 변경합니다.
    changeOrientationToLandscape();
  }, []);

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
