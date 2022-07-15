import { useState, useEffect } from "react";
import styles from "./Home.module.css";
import Markets from "../Components/Market-list.js";

function Home() {
//   const [loading, setLoading] = useState(true);
//   const [feeds, setFeeds] = useState([]);

  useEffect(() => {
    console.log("!");
  }, []);

  return (
    <div>
      <Markets />
    </div>
  );
}

export default Home;
