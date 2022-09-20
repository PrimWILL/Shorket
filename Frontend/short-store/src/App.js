import React, { useReducer, useEffect } from "react";
import "./App.css";

import { BrowserRouter, Routes, Route } from "react-router-dom";

import { CookiesProvider } from "react-cookie";
import { AuthProvider } from "./context/AuthProvider";

import MainLayout from "./components/MainLayout";

import Home from "./pages/Home";
import Login from "./pages/Login";
import Join from "./pages/Join";
import My from "./pages/My";
import Search from "./pages/Search";
import Market from "./pages/Market";
import Manage from "./pages/Manage";

import EnrollBooth from "./pages/EnrollBooth";
import { marketsDummyData } from "./constants/data";

const reducer = (state, action) => {
    let newState = [];
    switch (action.type) {
        case "INIT": {
            return action.data;
        }
        case "CREATE": {
            newState = [action.data, ...state];
            break;
        }
        case "REMOVE": {
            newState = state.filter((it) => it.id !== action.targetId);
            break;
        }
        case "EDIT": {
            newState = state.map((it) =>
                it.id === action.data.id ? { ...action.data } : it
            );
            break;
        }
        default:
            return state;
    }

    localStorage.setItem("diary", JSON.stringify(newState));
    return newState;
};
export const MarketStateContext = React.createContext();
export const MarketDispatchContext = React.createContext();

export default function App() {
    const marketList = marketsDummyData;
    const [data, dispatch] = useReducer(reducer, []);

    useEffect(() => {
        dispatch({ type: "INIT", data: marketsDummyData });
    }, []);

    // CREATE
    const onCreate = (date, content, emotion) => {
        dispatch({
            type: "CREATE",
            data: {
                id: dataId.current,
                date: new Date(date).getTime(),
                content,
                emotion,
            },
        });
        dataId.current += 1;
    };
    // REMOVE
    const onRemove = (targetId) => {
        dispatch({ type: "REMOVE", targetId });
    };
    // EDIT
    const onEdit = (targetId, date, content, emotion) => {
        dispatch({
            type: "EDIT",
            data: {
                id: targetId,
                date: new Date(date).getTime(),
                content,
                emotion,
            },
        });
    };

    return (
        <AuthProvider>
            <MarketStateContext.Provider value={data}>
                <MarketDispatchContext.Provider
                    value={{
                        onCreate,
                        onEdit,
                        onRemove,
                    }}
                >
                    <BrowserRouter>
                        {/* <CssBaseline /> */}
                        <div className="App">
                            <MainLayout>
                                <Routes>
                                    {/* public routes */}
                                    <Route
                                        path="/"
                                        element={<Home />}
                                        exact={true}
                                    ></Route>
                                    <Route
                                        path="/login"
                                        element={<Login />}
                                    ></Route>
                                    <Route
                                        path="/join"
                                        element={<Join />}
                                    ></Route>
                                    <Route
                                        path="/search"
                                        element={<Search />}
                                    ></Route>
                                    <Route
                                        path="/market/:id"
                                        element={<Market />}
                                    ></Route>
                                    <Route
                                        path="/market/:id/booth/:id"
                                        element={<div>부스상세 페이지</div>}
                                    ></Route>
                                    {/* protected routes */}
                                    <Route
                                        path="/my/*"
                                        element={<My />}
                                    ></Route>

                                    <Route
                                        path="/enrollBooth"
                                        element={<EnrollBooth />}
                                    ></Route>
                                    <Route
                                        path="/market/:id/manage"
                                        element={<Manage />}
                                    ></Route>
                                    <Route
                                        path="*"
                                        element={
                                            <div className="area">
                                                없는페이지요
                                            </div>
                                        }
                                    />
                                </Routes>
                            </MainLayout>
                        </div>
                    </BrowserRouter>
                </MarketDispatchContext.Provider>
            </MarketStateContext.Provider>
        </AuthProvider>
    );
}
