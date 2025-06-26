type City = {
    id: number
    name: string
    region: string
    country: string
    lat: number
    lon: number
}

type CurrentWeather = {
    temp_c: number
    condition: Condition
}


type ForecastWeather = {
    date: string
    day:{
        maxtemp_c: number
        mintemp_c: number
        avgtemp_c: number
        condition: Condition
    }
}

type CityWeatherDetails = {
    city: City
    current: CurrentWeather
    forecast: ForecastWeather[];
    history: ForecastWeather[];
}

type CityCurrentWeather = {
    city: City
    current: CurrentWeather
}

type Condition = {
    text: string;
    icon: string;
}