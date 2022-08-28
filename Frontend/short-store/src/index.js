import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import { CookiesProvider } from "react-cookie";

import App from "./App";
import "./index.css";
import CssBaseline from "@mui/material/CssBaseline";

const rootElement = document.getElementById("root");
const root = createRoot(rootElement);

root.render(
  <CookiesProvider>
    <BrowserRouter>
      <CssBaseline />
      <App />
    </BrowserRouter>
  </CookiesProvider>
);
