import React, { useState, useEffect } from "react";

import axios from "../api/axios";

import styles from "./Search.module.css";

import { Link } from "react-router-dom";

import {
    InputLabel,
    MenuItem,
    Accordion,
    AccordionSummary,
    AccordionDetails,
    Typography,
    FormControl,
} from "@mui/material/";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";

import MarketList from "../components/MarketList.js";
// import DropDown from "../components/DropDown";

const sortOptionList = [
    { value: "INTEREST", name: "인기순" },
    { value: "DICT", name: "가나다순" },
    { value: "LATESET", name: "최신순" },
    { value: "VIEW", name: "조회순" },
];

const dateFilterOptionList = [
    { value: "UPCOMING", name: "진행 예정" },
    { value: "CURRENT", name: "진행중" },
    { value: "COMPLETE", name: "종료" },
];

const localeFilterOptionList = [
    { value: "UPCOMING", name: "전부다" },
    { value: "CURRENT", name: "좋은 감정만" },
    { value: "COMPLETE", name: "안좋은 감정만" },
];

function Searchpage() {
    const [data, setData] = useState([]); // get으로 가져온 데이터

    const [marketList, setMarketList] = useState([]);
    const [sortType, setSortType] = useState("like");
    const [filter, setFilter] = useState("all");

    const [order, setOrder] = useState("");
    const [open, setOpen] = useState(false);

    useEffect(() => {
        axios
            .get(`/markets?sort=LATEST&date=CURRENT&page=0`)
            .then((res) => {
                // setData(res.data.markets);
                // console.log(res.data.markets);

                const getData = res.data.markets.map((it) => {
                    const startDate = new Date(it.startDate);
                    const endDate = new Date(it.endDate);

                    let period = `${startDate.getFullYear()}.${
                        startDate.getMonth() + 1
                    }.${startDate.getDate()}`;
                    period += ` ~ ${endDate.getFullYear()}.${
                        endDate.getMonth() + 1
                    }.${endDate.getDate()}`;

                    return {
                        id: it.marketIdx,
                        img: it.thumbnailImage,
                        url: "https://www.instagram.com/p/CgoeXAELGQl/",
                        name: it.name,
                        location: `${it.address.sido} ${it.address.sigungu}`,
                        detail: it.address.detailAddress,
                        time: `${it.openTime.slice(
                            0,
                            5
                        )} ~ ${it.closeTime.slice(0, 5)}`,
                        period: period,
                        like: it.interestCount,
                        view: it.viewCount,
                    };
                });
                setData(getData);
            })
            .catch(function (error) {
                console.log(error);
            });
    }, []);

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

    const getProcessedMarketList = () => {
        const compare = (a, b) => {
            if (sortType === "latest") {
                return parseInt(b.date) - parseInt(a.date);
            } else if (sortType === "like") {
                return parseInt(a.date) - parseInt(b.date);
            } else if (sortType === "dictionary") {
                return parseInt(a.date) - parseInt(b.date);
            } else if (sortType === "view") {
                return parseInt(a.date) - parseInt(b.date);
            }
        };

        const copyList = JSON.parse(JSON.stringify(diaryList));
        const filteredList =
            filter === "all"
                ? copyList
                : copyList.filter((it) => filterCallBack(it));

        const sortedList = filteredList.sort(compare);
        return sortedList;
    };

    return (
        <div className="area">
            <h2 style={{ height: "60px" }}>플리마켓</h2>
            <div className={styles.content}>
                <div className={styles.search_filter}>
                    <div className={styles.filter_status}>필터</div>
                    <Accordion className={styles.filter_list}>
                        <AccordionSummary
                            className={styles.filter_title}
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls="panel1a-content"
                            id="panel1a-header"
                        >
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
                            id="panel1a-header"
                        >
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
                                onChange={handleChange}
                            >
                                <MenuItem value={"인기순"}>인기순❤</MenuItem>
                                <MenuItem value={"가나다순"}>가나다순</MenuItem>
                                <MenuItem value={"최신순"}>최신순</MenuItem>
                                <MenuItem value={"조회수순"}>조회수순</MenuItem>
                            </Select>
                        </FormControl>
                    </div>
                    <MarketList list={data} />
                </div>
            </div>
        </div>
    );
}

export default Searchpage;
