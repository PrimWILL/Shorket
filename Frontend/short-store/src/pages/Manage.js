import React from "react";
import "antd/dist/antd.css";
import "./Manage.module.css";
import {
    LaptopOutlined,
    NotificationOutlined,
    UserOutlined,
} from "@ant-design/icons";
import { Breadcrumb, Layout, Menu } from "antd";
import Divider from "@mui/material/Divider";
import { AudioOutlined } from "@ant-design/icons";
import { Input, Space } from "antd";
import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";

const { Search } = Input;

const suffix = (
    <AudioOutlined
        style={{
            fontSize: 16,
            color: "#1890ff",
        }}
    />
);

const onSearch = (value) => console.log(value);

const { Header, Content, Sider } = Layout;
const items1 = ["4", "2", "3"].map((key) => ({
    key,
    label: `nav ${key}`,
}));
const items2 = [UserOutlined, LaptopOutlined, NotificationOutlined].map(
    (icon, index) => {
        const key = String(index + 1);
        const key2 = ["마켓관리", "부스관리", "신청한 부스"];
        return {
            key: `sub${key}`,
            icon: React.createElement(icon),
            label: `${key2[key - 1]}`,
            // children: new Array(4).fill(null).map((_, j) => {
            //   const subKey = index * 4 + j + 1;
            //   return {
            //     key: subKey,
            //     label: `option${subKey}`
            //   };
            // })
        };
    }
);

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
    "&:nth-of-type(odd)": {
        backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    "&:last-child td, &:last-child th": {
        border: 0,
    },
}));

function createData(name, member_since, last_access, management) {
    return { name, member_since, last_access, management };
}

const rows = [
    createData("hangang(김한강)", "19 / 01 / 24 14:30", "19 / 01 / 25 17:30"),
    createData("Ice cream sandwich", 237, 9.0),
    createData("Eclair", 262, 16.0),
    createData("Cupcake", 305, 3.7),
    createData("Gingerbread", 356, 16.0),
];

const Manage = () => (
    <Layout>
        <Content
            style={{
                padding: "100px 50px",
            }}
        >
            <Breadcrumb
                style={{
                    margin: "16px 0",
                }}
            >
                <Breadcrumb.Item>Home</Breadcrumb.Item>
                <Breadcrumb.Item>List</Breadcrumb.Item>
                <Breadcrumb.Item>App</Breadcrumb.Item>
            </Breadcrumb>
            <Layout
                className="site-layout-background"
                style={{
                    padding: "24px 0",
                }}
            >
                <Sider className="site-layout-background" width={200}>
                    <Menu
                        mode="inline"
                        defaultSelectedKeys={["1"]}
                        defaultOpenKeys={["sub1"]}
                        style={{
                            height: "100%",
                        }}
                        items={items2}
                    />
                </Sider>
                <Content
                    style={{
                        padding: "0 24px",
                        minHeight: 280,
                        textAlign: "left",
                    }}
                >
                    <h1>전체 플리마켓</h1>
                    <div>
                        <Search
                            placeholder="input search text"
                            onSearch={onSearch}
                            style={{
                                width: 200,
                            }}
                        />
                    </div>
                    <Divider />
                    <Divider />
                    <TableContainer component={Paper}>
                        <Table
                            sx={{ minWidth: 700 }}
                            aria-label="customized table"
                        >
                            <TableHead>
                                <TableRow>
                                    <StyledTableCell>이름</StyledTableCell>
                                    <StyledTableCell align="center">
                                        가입일
                                    </StyledTableCell>
                                    <StyledTableCell align="center">
                                        최근 접속일
                                    </StyledTableCell>
                                    <StyledTableCell align="center">
                                        관리
                                    </StyledTableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {rows.map((row) => (
                                    <StyledTableRow key={row.name}>
                                        <StyledTableCell
                                            component="th"
                                            scope="row"
                                        >
                                            {row.name}
                                        </StyledTableCell>
                                        <StyledTableCell align="center">
                                            {row.member_since}
                                        </StyledTableCell>
                                        <StyledTableCell align="center">
                                            {row.last_access}
                                        </StyledTableCell>
                                        <StyledTableCell align="center">
                                            {row.management}
                                        </StyledTableCell>
                                    </StyledTableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Content>
            </Layout>
        </Content>
    </Layout>
);

export default Manage;
