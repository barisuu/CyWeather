import { Box, CssBaseline } from '@mui/material';
import '../layout/App.css'
import NavBar from "../layout/NavBar.tsx";
import {Route, Routes } from 'react-router-dom';
import HomePage from '../HomePage.tsx';
import InitPage from '../InitPage.tsx';
import CityDetailPage from "../features/cities/details/CityDetailPage"



function App() {
    return (
        <Box sx={{bgcolor: "rgb(230,237,241)", minHeight: '100vh'}}>
            <CssBaseline/>
            <NavBar/>

            <Routes>
                <Route path={"/"} element={<InitPage/>}/>
                <Route path={"/home"} element={<HomePage/>}/>
                <Route path={"/city/:id"} element={<CityDetailPage/>}/>
            </Routes>
        </Box>
    );
}

export default App
