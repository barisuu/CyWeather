import {Box, Grid, Stack} from '@mui/material';
import CityTable from './CityTable';
import CityDetailCard from './features/cities/details/CityDetailCard';
import {useCityCurrentWeather} from './hooks/useCityCurrentWeather';


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
                    <Stack direction={"row"} spacing={8}>
                        {cardData.map((d) => (
                                <CityDetailCard city={d.city} current={d.current}/>
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