import React, { useState } from "react";
import styles from "./SearchPage.module.css";
import { Link } from "react-router-dom";
import Button from "@mui/material/Button";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import AccordionDetails from "@mui/material/AccordionDetails";
import Typography from "@mui/material/Typography";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import MarketList from "../../components/MarketList.js";


function Searchpage() {
  const [order, setOrder] = useState("");
  const [open, setOpen] = useState(false);

  const handleChange = (event) => {
    setOrder(event.target.value);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleOpen = () => {
    setOpen(true);
  };
  const items = [0, 1, 2, 3, 4, 5, 6, 7, 8];
  return (
    <div className={styles.area}>
      <h2 style={{ height: "60px" }}>플리마켓</h2>
      <div className={styles.content}>
        <div className={styles.search_filter}>
          <div className={styles.filter_status}>필터</div>
          <Accordion className={styles.filter_list}>
            <AccordionSummary
              className={styles.filter_title}
              expandIcon={<ExpandMoreIcon />}
              aria-controls="panel1a-content"
              id="panel1a-header">
              <Typography>지역</Typography>
            </AccordionSummary>
            <AccordionDetails className={styles.filter_menu}>
              <ul>
                <li>1</li>
                <li>1</li>
                <li>1</li>
              </ul>
            </AccordionDetails>
          </Accordion>
          <Accordion className={styles.filter_list}>
            <AccordionSummary
              className={styles.filter_title}
              expandIcon={<ExpandMoreIcon />}
              aria-controls="panel1a-content"
              id="panel1a-header">
              <Typography>기간</Typography>
            </AccordionSummary>
            <AccordionDetails className={styles.filter_menu}>
              <ul>
                <li>1</li>
                <li>1</li>
                <li>1</li>
              </ul>
            </AccordionDetails>
          </Accordion>
        </div>
        <div className={styles.search_content}>
          <div className={styles.filter}>
            <div className={styles.filter_tag}>tag1</div>
            <FormControl sx={{ m: 1, minWidth: 120 }} size="small">
              <InputLabel id="demo-controlled-open-select-label">
                정렬
              </InputLabel>
              <Select
                labelId="demo-controlled-open-select-label"
                id="demo-controlled-open-select"
                open={open}
                onClose={handleClose}
                onOpen={handleOpen}
                value={order}
                label="Order"
                onChange={handleChange}>
                <MenuItem value={"인기순"}>인기순❤</MenuItem>
                <MenuItem value={"가나다순"}>가나다순</MenuItem>
                <MenuItem value={"최신순"}>최신순</MenuItem>
                <MenuItem value={"조회수순"}>조회수순</MenuItem>
              </Select>
            </FormControl>
          </div>

          <MarketList />
        </div>
      </div>
    </div>
  );
}

export default Searchpage;
