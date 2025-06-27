import {AppBar, Box, Container, Toolbar, Typography} from "@mui/material";
import SearchBar from "../features/search/SearchBar";
import { useNavigate } from "react-router-dom";


export default function NavBar() {
    const navigate = useNavigate();

    return (
        <Box sx={{flexGrow: 1}}>
            <AppBar position="static"
                    sx={{
                        backgroundImage: 'linear-gradient(135deg, #182a73 0%, #218aae 69%, #20a7ac 89%)',
                        position: "relative"
                    }}>
                <Container maxWidth="xl">
                    <Toolbar sx={{display: 'flex', justifyContent: 'space-between'}}>
                        <Box onClick={() => {
                            navigate(`/home`);
                        }}
                        sx={{'&:hover': {
                                cursor: 'pointer'
                            }}}
                        >
                            <Typography variant="h4" fontWeight="bold">CyWeather</Typography>
                        </Box>
                        <SearchBar/>
                    </Toolbar>
                </Container>

            </AppBar>
        </Box>
    )
}