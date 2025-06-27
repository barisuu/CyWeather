import {Box, Grid, Stack} from '@mui/material';
import CityTable from './CityTable';
import {useCityCurrentWeather} from './hooks/useCityCurrentWeather';
import CityCard from './CityCard';


export default function HomePage() {
    const {data, isLoading, error} = useCityCurrentWeather();
    const cardCities = [540341, 539407, 540131]

    if (isLoading) return <div>Loading...</div>;
    if (error || !data) return <div>Error loading cities.</div>;

    const cardData = data.filter(d => cardCities.includes(d.city.id));
    const tableData = data.filter(d => !cardCities.includes(d.city.id));

    return (
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
                    <Stack direction={"row"} spacing={8} justifyContent="center">
                        {cardData.map((d) => (
                                <CityCard city={d.city} current={d.current}/>
                            ))}
                    </Stack>
                </Grid>
                <Grid size={12}>
                    <CityTable cityCurrentWeather={tableData}></CityTable>
                </Grid>
            </Grid>
        </Box>
    );
}