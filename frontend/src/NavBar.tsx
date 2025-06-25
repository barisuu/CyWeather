import {AppBar, Box, Container, MenuItem, Toolbar, Typography} from "@mui/material";


export default function NavBar() {
    return (
        <Box sx={{flexGrow: 1}}>
            <AppBar position="static"
                    sx={{
                        backgroundImage: 'linear-gradient(135deg, #182a73 0%, #218aae 69%, #20a7ac 89%)',
                        position: "relative"
                    }}>
                <Container maxWidth="xl">
                    <Toolbar sx={{display: 'flex', justifyContent: 'space-between'}}>
                        <Box>
                            <Typography variant="h4" fontWeight="bold">CyWeather</Typography>
                        </Box>
                        <MenuItem>
                            API Call History
                        </MenuItem>
                    </Toolbar>
                </Container>

            </AppBar>
        </Box>
    )
}