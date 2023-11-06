import "./App.css";
import { BrowserRouter as Router } from "react-router-dom";
import RenderRoutes from "@/router/routes";
import { QueryClient, QueryClientProvider } from "react-query";

function App() {
  const queryClient = new QueryClient();
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
