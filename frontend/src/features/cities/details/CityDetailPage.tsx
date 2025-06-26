import { Box, Grid, Typography } from "@mui/material";
import CityDetailCard from "./CityDetailCard";
import { useParams } from "react-router-dom";
import { useCityWeatherDetails } from "../../../hooks/useCityWeatherDetails";
import CityDetailTable from "./CityDetailTable";


export default function CityDetailPage(){
    const {id} = useParams();
    const {data, isLoading, error} = useCityWeatherDetails(id);

    if(isLoading) return <Typography>Loading...</Typography>
    if(error || !data) return <Typography>City not found</Typography>

    return(
        <Box
            sx={{
                display:"inline-grid",
                alignSelf:"center",
                justifySelf:"center",
                justifyContent:"center",
                alignItems:"center",
                width:"100%",
                minHeight:"100vh"
            }}
        >
            <Grid container spacing={25} justifyContent={"space-between"}>
                <Grid size={4}>
                    <CityDetailCard city={data.city} current={data.current}/>
                </Grid>
                <Grid size={8}>
                    <CityDetailTable city={data.city} history={data.history} forecast={data.forecast}/>
                </Grid>
            </Grid>


        </Box>
    )
}