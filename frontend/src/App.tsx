import './App.css'
import {Box, Grid} from "@mui/material";
import CityCard from "./CityCard.tsx";
import CityTable from "./CityTable.tsx";
import NavBar from "./NavBar.tsx";

const cities = ["Nicosia", "Kyrenia", "Famagusta"];
const weatherCondition = ["Sunny", "Cloudy", "Partly Cloudy"];
const temperature = [30, 25, 28.5]

function App() {
    return (
        <>
            <NavBar/>
            <Box
                minHeight="100vh"
                display="flex"
                alignItems="center"
                justifyContent="center"
                width={"100%"}
                bgcolor={'rgb(230,237,241)'}
            >
                <Grid container spacing={4} paddingBottom={"10vh"}>
                    <Grid size={12}>
                        <Grid container spacing={8}>
                            {cities.map((city, index) => (
                                <Grid size={{xs: 4, sm: 4, md: 4}} key={city}>
                                    <CityCard cityName={city}
                                              condition={weatherCondition[index]}
                                              temperature={temperature[index]}
                                    />
                                </Grid>
                            ))}
                        </Grid>
                    </Grid>
                    <Grid size={12}>
                        <CityTable></CityTable>
                    </Grid>
                </Grid>
            </Box>
        </>
    );
}

export default App
