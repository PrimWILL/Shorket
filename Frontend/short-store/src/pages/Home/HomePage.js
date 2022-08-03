import { useState, useEffect } from "react";
import styles from "./Home.module.css";
import { PhotoCamera } from "@material-ui/icons";
import Button from "@mui/material/Button";
import { Link } from "react-router-dom";

import MarketList from "../../components/MarketList.js";
import Banner from "../../components/Banner.js";

function Home() {
  const [loading, setLoading] = useState(true);
  const [feeds, setFeeds] = useState([]);

  useEffect(() => {
    console.log("!");
  }, []);

  return (
    <div>
      <Banner />
      <MarketList />
      <Button className={styles.button} variant="outlined">
        <Link to="/search">더보기</Link>
      </Button>
    </div>
  );
}

export default Home;
